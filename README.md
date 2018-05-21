# Hardware
DELL Latitude E7270

# Software
* Intellij IDEA 2018 Community
* Android Studio
* JDK
```jshelllanguage
PS C:\Users\Nick_Nie> java -version
java version "1.8.0_102"
Java(TM) SE Runtime Environment (build 1.8.0_102-b14)
Java HotSpot(TM) 64-Bit Server VM (build 25.102-b14, mixed mode)
```
* NodeJS
```jshelllanguage
PS C:\Users\Nick_Nie> node -v
v8.10.0
```
* Appium 1.6.1
* Android Version: Andriod 8.1
* Virtual Devices
```jshelllanguage
PS C:\Users\Nick_Nie> emulator -list-avds
Nexus_5X_API_27_x86
```
* UIAutomatorViewer
* Appium Inspector

# How to run the test
1. Download target application from https://www.apkmonk.com/download-app/hko.MyObservatory_v1_0/4_hko.MyObservatory_v1_0_2018-01-31.apk/
2. Move the downloaded apk into specific folder, e.g. D:/app
3. Start Appium, set the _host_ as '127.0.0.1', keep default port
4. Start virtual android device
```jshelllanguage
emulator -avd Nexus_5X_API_27_x86
``` 
5. Go to the project root folder, run command
```jshelllanguage
D:\work\HKObservatory>mvn clean test
[INFO] Scanning for projects...
[WARNING]
[WARNING] Some problems were encountered while building the effective model for HKObservatory:HKObservatory:jar:1.0-SNAPSHOT
[WARNING] 'build.plugins.plugin.version' for org.apache.maven.plugins:maven-compiler-plugin is missing. @ line 12, column 21
[WARNING]
[WARNING] It is highly recommended to fix these problems because they threaten the stability of your build.
[WARNING]
[WARNING] For this reason, future Maven versions might no longer support building such malformed projects.
[WARNING]
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building HKObservatory 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ HKObservatory ---
[INFO] Deleting D:\work\HKObservatory\target
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ HKObservatory ---
[WARNING] Using platform encoding (Cp1251 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] Copying 0 resource
[INFO]
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ HKObservatory ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ HKObservatory ---
[WARNING] Using platform encoding (Cp1251 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] Copying 2 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ HKObservatory ---
[INFO] Changes detected - recompiling the module!
[WARNING] File encoding has not been set, using platform encoding Cp1251, i.e. build is platform dependent!
[INFO] Compiling 3 source files to D:\work\HKObservatory\target\test-classes
[INFO]
[INFO] --- maven-surefire-plugin:2.21.0:test (default-test) @ HKObservatory ---
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running TestSuite
2018-05-20 23:21:16 INFO  NineDayForecastTest - Initializing...
May 20, 2018 11:21:26 PM org.openqa.selenium.remote.ProtocolHandshake createSession
INFO: Detected dialect: OSS
2018-05-20 23:21:26 INFO  NineDayForecastTest - Launch APP
2018-05-20 23:21:52 INFO  NineDayForecastTest - Open side menu
2018-05-20 23:21:55 INFO  NineDayForecastTest - Select item: 9-Day Weather Forecast
2018-05-20 23:22:00 INFO  NineDayForecastTest - Verfy Forecast of the next 9 days are displayed
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 51.757 s - in TestSuite
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 57.152 s
[INFO] Finished at: 2018-05-20T23:22:08+08:00
[INFO] Final Memory: 19M/212M
[INFO] ------------------------------------------------------------------------
``` 
6. View the test result at _/target/surefire-report/html/index.html_

# Reference 
* http://toolsqa.com
* http://appium.io
* http://appium.github.io/java-client
* https://discuss.appium.io/t/swipe-scroll-best-practice-with-java-client-5/18589
* https://stackoverflow.com/questions/25201743/error-in-using-uiautomatorviewer-for-testing-android-app-in-appium