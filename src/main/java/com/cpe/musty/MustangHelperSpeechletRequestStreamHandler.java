package com.cpe.musty;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

/**
 * This class is created by the Lambda environment when a request comes in. All
 * calls will be dispatched to the Speechlet passed into the super constructor.
 */
public final class MustangHelperSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {

    private static final Set<String> supportedApplicationIds = new HashSet<String>();

    public MustangHelperSpeechletRequestStreamHandler() {
        super(new MustangHelperSpeechlet(), supportedApplicationIds);
    }

}
