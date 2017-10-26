package com.cpe.musty.intent;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.cpe.musty.intent.helper.AskResponseWrapper;
import com.cpe.musty.intent.JsonReader;
import org.json.JSONException;
import java.io.IOException;
import org.json.JSONObject;
public class ComputerAvailability implements IntentHandler {
  
  private static final String FL_SLOT = "fl_number";

  @Override
  public SpeechletResponse handle(Intent intent) {
    Slot fl_number = intent.getSlot(FL_SLOT);

    //http://lib.calpoly.edu/api/availability/1st
    getJson();
    String output = "You got here";
    String reprompt = "What else can I do?";

    

    return AskResponseWrapper.newAskResponse(output, reprompt);
    
  

  }

  private static void getJson()  {

    JsonReader rd = new JsonReader();
    JSONObject json = rd.readJsonFromUrl("https://graph.facebook.com/19292868552");
  }


}
