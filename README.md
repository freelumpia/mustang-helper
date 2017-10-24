# Mustang Helper

## Dev Requirements
1. Maven is downloaded on dev machine. If you don't have maven, run either `sudo apt-get install maven` or `brew install maven`.
2. Some sort of IDE (I guess).
3. Access to Lambda function and Alexa skill.

## Uploading to Lambda
To upload to Lambda:
1. Run `mvn package shade:shade`
2. Navigate to Lambda function and upload `target/mustang-helper.jar`.
3. Ensure that handler is set to `com.cpe.musty.MustangHelperSpeechletRequestStreamHandler`
4. Save the LATEST version of the Lambda function and publish as new version.
5. Under the new version, add "Alexa Skills Kit" as a Trigger and save again.

## Updating Alexa Skill
To update the Alexa skill with the new Lambda function version:
1. Copy the Lambda ARN, listed at the top right of the page, from the above Lambda function.
2. Navigate to the Cal Poly Mustang Assistant skill.
3. Under Configuration, paste the new Lambda ARN.
4. At the bottom of the page, press Save.

## Adding Intent Handlers
First off, a single intent should correspond to one action that the skill can make. This means that "Reserve a room in the library." and "Reserve a book from the library." should be *separate* intents.

* Create a new implementation of the IntentHandler interface.
* The `handle()` method takes in an Intent object, grabs any relevant Slots, and creates a SpeechletResponse object to return to the device.
* If an intent handler finds an invalid Intent, they should call out to `HelpIntentHandler` to have the device use the default help message.


