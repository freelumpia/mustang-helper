package com.cpe.musty;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

/**
 * This class is created by the Lambda environment when a request comes in. All
 * calls will be dispatched to the Speechlet passed into the super constructor.
 */
public final class MustangHelperSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {

    private static final Set<String> supportedApplicationIds;
    static {
        supportedApplicationIds = new HashSet<String>();
        supportedApplicationIds.add("amzn1.ask.skill.92223192-d2e2-4a84-89a7-a852805f9899");
    }

    public MustangHelperSpeechletRequestStreamHandler() {
        super(new MustangHelperSpeechlet(), supportedApplicationIds);
    }

}
