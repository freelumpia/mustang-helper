package com.cpe.musty.intent;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SimpleCard;
import com.cpe.musty.Recipes;
import com.cpe.musty.intent.helper.AskResponseWrapper;

public class GetRecipeIntentHandler implements IntentHandler {

    /**
     * The key to get the item from the intent.
     */
    private static final String ITEM_SLOT = "Item";

    private static final HelpIntentHandler HELP = HelpIntentHandler.getInstance();

	/**
	 * Creates a {@code SpeechletResponse} for the RecipeIntent.
	 *
	 * @param intent
	 *            intent for the request
	 * @return SpeechletResponse spoken and visual response for the given intent
	 */
	public SpeechletResponse handle(Intent intent) {
		Slot itemSlot = intent.getSlot(ITEM_SLOT);
		if (itemSlot != null && itemSlot.getValue() != null) {
			String itemName = itemSlot.getValue();

			// Get the recipe for the item
			String recipe = Recipes.get(itemName);

			if (recipe != null) {
				// If we have the recipe, return it to the user.
				PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
				outputSpeech.setText(recipe);

				SimpleCard card = new SimpleCard();
				card.setTitle("Recipe for " + itemName);
				card.setContent(recipe);

				return SpeechletResponse.newTellResponse(outputSpeech, card);
			} else {
				// We don't have a recipe, so keep the session open and ask the
				// user for another
				// item.
				String speechOutput = "I'm sorry, I currently do not know the recipe for " + itemName
						+ ". What else can I help with?";
				String repromptSpeech = "What else can I help with?";
				return AskResponseWrapper.newAskResponse(speechOutput, repromptSpeech);
			}
		} else {
			// There was no item in the intent so return the help prompt.
			return HELP.handle(intent);
		}
	}
}
