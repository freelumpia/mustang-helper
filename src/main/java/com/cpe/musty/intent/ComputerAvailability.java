package com.cpe.musty.intent;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.cpe.musty.intent.helper.AskResponseWrapper;

public class ComputerAvailability implements IntentHandler {
  
  private static final String FL_SLOT = "fl_number";

  @Override
  public SpeechletResponse handle(Intent intent) {
    int fl_number = intent.getSlot.getValue(FL_SLOT);

    //http://lib.calpoly.edu/api/availability/1st
    String output = "You got here";
                String reprompt = "What else can I do?";

                            return AskResponseWrapper.newAskResponse(output, reprompt);
    
  

  }



}