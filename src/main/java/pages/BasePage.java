package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.ReadLocatorsFromExcel;
import java.io.IOException;
import static util.DriverSetup.androidDriver;
import static util.DriverSetup.iosDriver;


/**
 * Created by Osanda on 5/14/2017.
 */


public class BasePage {

    private String PLATFORM = System.getenv("platform");
    private final String ANDROID = "android";
    private final String IOS = "ios";
    private WebElement element;

    public WebElement getElement(String elementName) throws IOException {
        if (PLATFORM.toLowerCase().equals(IOS)) {
            switch (ReadLocatorsFromExcel.getHow(elementName).toLowerCase()) {
                case "id":
                    element = iosDriver.findElement(By.id(ReadLocatorsFromExcel.getIosLocator(elementName)));
                    System.out.println((ReadLocatorsFromExcel.getIosLocator(elementName)));
                    return element;
                case "xpath":
                    element = iosDriver.findElement(By.xpath(ReadLocatorsFromExcel.getIosLocator(elementName)));
                    return element;
                case "classname":
                    element = iosDriver.findElement(By.className(ReadLocatorsFromExcel.getIosLocator(elementName)));
                    return element;
                case "cssselector":
                    element = iosDriver.findElement(By.cssSelector(ReadLocatorsFromExcel.getIosLocator(elementName)));
                    return element;
                case "linktext":
                    element = iosDriver.findElement(By.linkText(ReadLocatorsFromExcel.getIosLocator(elementName)));
                    return element;
                case "partiallinktext":
                    element = iosDriver.findElement(By.partialLinkText(ReadLocatorsFromExcel.getIosLocator(elementName)));
                    return element;
                case "name":
                    element = iosDriver.findElement(By.name(ReadLocatorsFromExcel.getIosLocator(elementName)));
                    return element;
                case "tagname":
                    element = iosDriver.findElement(By.tagName(ReadLocatorsFromExcel.getIosLocator(elementName)));
                    return element;
            }
        } else {
            switch (ReadLocatorsFromExcel.getHow(elementName).toLowerCase()) {
                case "id":
                    element = androidDriver.findElement(By.id(ReadLocatorsFromExcel.getAndroidLocator(elementName)));
                    System.out.println((ReadLocatorsFromExcel.getAndroidLocator(elementName)));
                    return element;
                case "xpath":
                    element = androidDriver.findElement(By.xpath(ReadLocatorsFromExcel.getAndroidLocator(elementName)));
                    return element;
                case "classname":
                    element = androidDriver.findElement(By.className(ReadLocatorsFromExcel.getAndroidLocator(elementName)));
                    return element;
                case "cssselector":
                    element = androidDriver.findElement(By.cssSelector(ReadLocatorsFromExcel.getAndroidLocator(elementName)));
                    return element;
                case "linktext":
                    element = androidDriver.findElement(By.linkText(ReadLocatorsFromExcel.getAndroidLocator(elementName)));
                    return element;
                case "partiallinktext":
                    element = androidDriver.findElement(By.partialLinkText(ReadLocatorsFromExcel.getAndroidLocator(elementName)));
                    return element;
                case "name":
                    element = androidDriver.findElement(By.name(ReadLocatorsFromExcel.getAndroidLocator(elementName)));
                    return element;
                case "tagname":
                    element = androidDriver.findElement(By.tagName(ReadLocatorsFromExcel.getAndroidLocator(elementName)));
                    return element;
            }
        }
        return null;
    }

    public void waitForElementClickable(String elementName) throws IOException {
        WebDriverWait wait = new WebDriverWait(androidDriver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(getElement(elementName)));
    }

    public void waitForElementVisible(String elementName) throws IOException {
        WebDriverWait wait = new WebDriverWait(androidDriver, 15);
        wait.until(ExpectedConditions.visibilityOf(getElement(elementName)));
    }

    public void setTextAs(String elementName, String text) throws IOException {
        waitForElementClickable(elementName);
        element.sendKeys(text);
    }

    public void clickElement(String elementName) throws IOException {
        waitForElementClickable(elementName);
        element.click();
    }

    public void validatePageTitle(String elementName, String pageTitle) throws IOException {
        Assert.assertTrue(getElement(elementName).isDisplayed(), "Page title locator is invalid.");
        Assert.assertEquals(getElement(elementName).getText(), pageTitle, "Page title mismatched.");
    }


}
