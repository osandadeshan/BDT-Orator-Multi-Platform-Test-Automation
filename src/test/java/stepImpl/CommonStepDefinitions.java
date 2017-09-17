package stepImpl;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import util.DriverSetup;


/**
 * Created by Osanda on 4/29/2017.
 */


public class CommonStepDefinitions {

    private String PLATFORM = DriverSetup.PLATFORM.toLowerCase();

    @Step("Testing platform information")
    public void platformInfo(){
        if (PLATFORM.equals("android")) {
            Gauge.writeMessage("Targeted Platform: Android");
            Gauge.writeMessage("Targeted Android Version: v" + DriverSetup.ANDROID_VERSION);
            Gauge.writeMessage("Targeted Appium Host: " + DriverSetup.APPIUM_HOST);
            Gauge.writeMessage("Targeted Appium Port: " + DriverSetup.APPIUM_PORT);
        } else if (PLATFORM.equals("ios")) {
            Gauge.writeMessage("Targeted platform is: iOS");
        } else {
            Gauge.writeMessage("Targeted platform is not supported");
        }
    }

    @Step("Quit from the application")
    public void quit() {
        DriverSetup.tearDown();
    }


}
