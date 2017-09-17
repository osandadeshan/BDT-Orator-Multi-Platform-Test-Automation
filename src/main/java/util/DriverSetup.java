package util;

import com.thoughtworks.gauge.BeforeSuite;
import com.thoughtworks.gauge.Gauge;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 * Created by Osanda on 4/26/2017.
 */


public class DriverSetup {

    public static final String APPIUM_HOST = System.getenv("appium_host");
    public static final String APPIUM_PORT = System.getenv("appium_port");
    private static final String APPIUM_SERVER_URL = "http://" + APPIUM_HOST + ":" + APPIUM_PORT + "/wd/hub";
    public static final String PLATFORM = System.getenv("platform");
    private static final String PROJECT_ROOT = System.getProperty("user.dir");
    private static final String APP_NAME = System.getenv("app_name");
    // Android properties
    public static AndroidDriver androidDriver;
    private static final String ANDROID = "Android";
    private static final String ANDROID_APP_PACKAGE = System.getenv("android_app_package");
    private static final String ANDROID_APK_PATH = System.getenv("android_app_path");
    public static final String ANDROID_VERSION = System.getenv("android_version");
    public static final String ANDROID_DEVICE_NAME = System.getenv("android_device_name");
    // iOS properties
    public static IOSDriver iosDriver;
    private static final String IOS = "iOS";
    private static final String IOS_APP_PACKAGE = System.getenv("ios_app_package");
    private static final String IOS_IPA_PATH = System.getenv("ios_app_path");
    private static final String IOS_VERSION = System.getenv("ios_version");
    public static final String IOS_DEVICE_NAME = System.getenv("ios_device_name");

        @BeforeSuite
        public void setupDriver() throws IOException {
            if (PLATFORM.toLowerCase().equals(IOS.toLowerCase())){
                prepareIOSForAppium();
            } else {
                prepareAndroidForAppium();
            }
        }

        public AndroidDriver prepareAndroidForAppium() throws IOException {
                // Set APK file path
                File app = new File(PROJECT_ROOT + ANDROID_APK_PATH);
                System.out.println("Installing APK file from the path " + PROJECT_ROOT + ANDROID_APK_PATH);
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                        // Mandatory capabilities
                        capabilities.setCapability("deviceName", ANDROID_DEVICE_NAME);
                        capabilities.setCapability("platformName", ANDROID);
                        // Install APK file from the above path
                        try {
                            capabilities.setCapability("app", app.getAbsolutePath());
                            System.out.println("Successfully installed " + APP_NAME + " in the device!");
                        } catch (Exception ex) {
                            System.out.println("Installing " + APP_NAME + " failed!\n" + ex.getMessage());
                        }
                        // Set Android version desired capability. Set your mobile device's OS version.
                        capabilities.setCapability(CapabilityType.VERSION, ANDROID_VERSION);
                        // Set Android appPackage desired capability.
                        capabilities.setCapability("appPackage", ANDROID_APP_PACKAGE);
                        // Other caps
                        androidDriver =  new AndroidDriver(new URL(APPIUM_SERVER_URL), capabilities);
            return androidDriver;
        }

        public IOSDriver prepareIOSForAppium() throws IOException {
                // Set APK file path
                File app=new File(PROJECT_ROOT + IOS_IPA_PATH);
                System.out.println("Installing IPA file from the path " + PROJECT_ROOT + IOS_IPA_PATH);
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                        // Mandatory capabilities
                        capabilities.setCapability("deviceName", IOS_DEVICE_NAME);
                        capabilities.setCapability("platformName", IOS);
                        // Install IPA file from the above path
                        try {
                            capabilities.setCapability("app", app.getAbsolutePath());
                            System.out.println("Successfully installed " + APP_NAME + " in the device!");
                        } catch (Exception ex) {
                            System.out.println("Installing " + APP_NAME + " failed!\n" + ex.getMessage());
                        }
                        // Set iOS version desired capability. Set your mobile device's OS version.
                        capabilities.setCapability(CapabilityType.VERSION, IOS_VERSION);
                        // Set iOS appPackage desired capability.
                        capabilities.setCapability("appPackage", IOS_APP_PACKAGE);
                        // Other caps
                        iosDriver =  new IOSDriver(new URL(APPIUM_SERVER_URL), capabilities);
            return iosDriver;
        }

//        @AfterSuite
        public static void tearDown() {
            Boolean hasQuit;
            if (PLATFORM.toLowerCase().equals(IOS.toLowerCase())) {
                iosDriver.quit();
                hasQuit = iosDriver.toString().contains("(null)");
                if (hasQuit == Boolean.TRUE){
                    System.out.println("iOS Driver has stopped successfully!");
                    Gauge.writeMessage("iOS Driver has stopped successfully!");
                } else {
                    System.out.println(hasQuit);
                    System.out.println("An error occurred while stopping the iOS Driver!");
                    Gauge.writeMessage("An error occurred while stopping the iOS Driver!");
                }
            } else {
                androidDriver.quit();
                hasQuit = androidDriver.toString().contains("(null)");
                if (hasQuit == Boolean.TRUE){
                    System.out.println("Android Driver has stopped successfully!");
                    Gauge.writeMessage("Android Driver has stopped successfully!");
                } else {
                    System.out.println(hasQuit);
                    System.out.println("An error occurred while stopping the Android Driver!");
                    Gauge.writeMessage("An error occurred while stopping the Android Driver!");
                }
            }
        }


}
