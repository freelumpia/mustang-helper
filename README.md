#Mustang Helper
To upload to Lambda:
1. Install Maven via "sudo apt-get install maven"
2. Run "mvn package shade:shade"
3. Upload target/mustang-helper.jar to Lambda, with handler="com.cpe.musty.MustangHelperSpeechlet"
