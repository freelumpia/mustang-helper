package com.cpe.musty.intent;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.cpe.musty.intent.helper.AskResponseWrapper;

public class HelpIntentHandler implements IntentHandler {

    private HelpIntentHandler() {
    }

    private static final HelpIntentHandler INSTANCE = new HelpIntentHandler();

    public static HelpIntentHandler getInstance() {
        return INSTANCE;
    }

    @Override
    public SpeechletResponse handle(Intent intent) {
        String speechOutput = "You can ask questions about PASS such as, tell me information about CSC 101, "
                + "or, you can say exit... Now, what can I help you with?";
        String repromptText = "You can ask questions about PASS such as, tell me information about CSC 101, "
                + "or, you can say exit... Now, what can I help you with?";
        return AskResponseWrapper.newAskResponse(speechOutput, repromptText);
    }

}
