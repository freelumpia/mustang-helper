package com.cpe.musty.intent;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.SpeechletResponse;

public interface IntentHandler {

    SpeechletResponse handle(Intent intent);

}
