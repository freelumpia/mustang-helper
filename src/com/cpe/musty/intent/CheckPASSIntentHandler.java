package com.cpe.musty.intent;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.cpe.musty.intent.helper.AskResponseWrapper;

public class CheckPASSIntentHandler implements IntentHandler {

    // Key to get the department name from the intent
    private static final String DEPARTMENT_SLOT = "Department";
    // Key to get the course number from the intent
    private static final String COURSE_SLOT = "Course";

    @Override
    public SpeechletResponse handle(Intent intent) {
        Slot departmentSlot = intent.getSlot(DEPARTMENT_SLOT);
        Slot courseSlot = intent.getSlot(COURSE_SLOT);

        if (isValid(departmentSlot) && isValid(courseSlot)) {
            String departmentName = departmentSlot.getValue();
            String courseName = courseSlot.getValue();

            PlainTextOutputSpeech output = new PlainTextOutputSpeech();
            output.setText(String.format("Finding information about %s %s", departmentName, courseName));

            return SpeechletResponse.newTellResponse(output);
        } else {
            String output = "I'm sorry, I didn't understand what course you're asking about. "
                    + "Please specify the department name and course number.";
            String reprompt = "What else can I do?";

            return AskResponseWrapper.newAskResponse(output, reprompt);
        }
    }

    private boolean isValid(Slot slot) {
        return slot != null && slot.getValue() != null;
    }

}
