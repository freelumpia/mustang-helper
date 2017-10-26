package com.cpe.musty.intent;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.cpe.musty.intent.helper.AskResponseWrapper;

public class ItemAvailabilityHander implements IntentHandler {

    // Key to get the item name from the intent
    private static final String ITEM_SLOT = "Item";

    @Override
    public SpeechletResponse handle(Intent intent) {
        Slot itemSlot = intent.getSlot(ITEM_SLOT);

        if (isValid(itemSlot)) {
            String itemName = itemSlot.getValue();

            PlainTextOutputSpeech output = new PlainTextOutputSpeech();
            output.setText(String.format("Finding information about %s", itemName));

            return SpeechletResponse.newTellResponse(output);
        } else {
            String output = "I'm sorry, I didn't understand what item you're asking about. "
                    + "Please specify the item name.";
            String reprompt = "What else can I do?";

            return AskResponseWrapper.newAskResponse(output, reprompt);
        }
    }

    private boolean isValid(Slot slot) {
        return slot != null && slot.getValue() != null;
    }

}
