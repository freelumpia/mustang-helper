package com.cpe.musty.intent.availability;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.cpe.musty.intent.IntentHandler;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class ComputerAvailabilityIntentHandler implements IntentHandler {

    private static final String FL_SLOT = "fl_number";

    @NonNull
    private final FloorChecker floorChecker;

    @Override
    public SpeechletResponse handle(Intent intent) {
        Slot fl_number = intent.getSlot(FL_SLOT);

        // For now, just ignore the floor number slot and check the first floor
        try {
            long availableComputers = floorChecker.findAvailableComputers(fl_number.getValue());

            PlainTextOutputSpeech output = new PlainTextOutputSpeech();
            output.setText(String.format("There are %s available computers on that floor.",
                    Long.toString(availableComputers)));

            return SpeechletResponse.newTellResponse(output);
        } catch (Exception e) {
            throw new RuntimeException("There was an error checking floors.");
        }
    }

}
