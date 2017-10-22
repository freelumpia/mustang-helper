/**
    Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.

    Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. A copy of the License is located at

        http://aws.amazon.com/apache2.0/

    or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.cpe.musty;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.cpe.musty.intent.CancelIntentHandler;
import com.cpe.musty.intent.GetRecipeIntentHandler;
import com.cpe.musty.intent.HelpIntentHandler;
import com.cpe.musty.intent.IntentHandler;
import com.cpe.musty.intent.StopIntentHandler;
import com.cpe.musty.intent.helper.AskResponseWrapper;
import com.google.common.collect.ImmutableMap;

/**
 * This sample shows how to create a Lambda function for handling Alexa Skill
 * requests that:
 *
 * <ul>
 * <li><b>Custom slot type</b>: demonstrates using custom slot types to handle a
 * finite set of known values</li>
 * </ul>
 * <p>
 * <h2>Examples</h2>
 * <p>
 * <b>One-shot model</b>
 * <p>
 * User: "Alexa, ask Minecraft Helper how to make paper."
 * <p>
 * Alexa:"(reads back recipe for paper)."
 */
public class MinecraftSpeechlet implements Speechlet {

	// This should be the map of Intent Names to Intent Classes. See the
	// RecipeIntent class for a good example of how to handle an intent, grab a
	// value from it, etc.
	private static final Map<String, IntentHandler> INTENT_HANDLERS = ImmutableMap.of("RecipeIntent",
			new GetRecipeIntentHandler(), "AMAZON.HelpIntent", HelpIntentHandler.getInstance(), "AMAZON.StopIntent",
			new StopIntentHandler(), "AMAZON.CancelIntent", new CancelIntentHandler());

	private static final Logger log = LoggerFactory.getLogger(MinecraftSpeechlet.class);

	@Override
	public void onSessionStarted(final SessionStartedRequest request, final Session session) throws SpeechletException {
		log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

		// any initialization logic goes here
	}

	@Override
	public SpeechletResponse onLaunch(final LaunchRequest request, final Session session) throws SpeechletException {
		log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

		String speechOutput = "Welcome to the Minecraft Helper. You can ask a question like, "
				+ "what's the recipe for a chest? ... Now, what can I help you with?";
		// If the user either does not reply to the welcome message or says
		// something that is not understood, they will be prompted again with
		// this text.
		String repromptText = "For instructions on what you can say, please say help me.";

		// Here we are prompting the user for input
		return AskResponseWrapper.newAskResponse(speechOutput, repromptText);
	}

	@Override
	public SpeechletResponse onIntent(final IntentRequest request, final Session session) throws SpeechletException {
		log.info("onIntent requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

		Intent intent = request.getIntent();
		String intentName = (intent != null) ? intent.getName() : null;

		if (INTENT_HANDLERS.containsKey(intentName)) {
			return INTENT_HANDLERS.get(intentName).handle(intent);
		}

		throw new SpeechletException("Invalid Intent");
	}

	@Override
	public void onSessionEnded(final SessionEndedRequest request, final Session session) throws SpeechletException {
		log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

		// any cleanup logic goes here
	}

}
