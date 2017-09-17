package pages;

import org.openqa.selenium.support.PageFactory;
import java.io.IOException;
import static util.DriverSetup.androidDriver;
import static util.DriverSetup.iosDriver;


/**
 * Created by Osanda on 5/14/2017.
 */


public class OratorPage extends BasePage {

    public static final String TEXT_TEXTVIEW = "text_textfield";
    private static final String SPEAK_BUTTON = "speak_button";

    public OratorPage() {
        PageFactory.initElements(androidDriver, this);
        PageFactory.initElements(iosDriver, this);
    }

    public void speak(String text) throws IOException {
        setTextAs(TEXT_TEXTVIEW, text);
        clickElement(SPEAK_BUTTON);
    }


}
