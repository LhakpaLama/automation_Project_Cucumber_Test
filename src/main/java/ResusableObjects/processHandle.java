package ResusableObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class processHandle {

    public static void getWebText(WebDriver wDrive, String locator, String elementName){
        try {
            System.out.println("Getting the text from element  " + elementName);
            WebElement webText = wDrive.findElement(By.xpath(locator));
            String textCaptured = webText.getText();
            System.out.println("Header of the page is " + textCaptured);
        }catch(Exception err){
            System.out.println("unable to get the text from element "+elementName);
        }

    }//end of getWebText method

    public static void roomSelect(WebDriver wDriver, String locator, String elementName)  {
        try {
            System.out.println("Selecting from the element " + elementName);
            WebElement roomSelection = wDriver.findElement(By.xpath(locator));
            Select num_room = new Select(roomSelection);
            num_room.selectByValue("3");
        }catch (Exception err0){
            System.out.println("unable to make selection from element "+ elementName);
        }
    }//end of roomSelect method

    public static void mouserHoverClick(WebDriver wDriver, String locator, String elementName){
        try {
            System.out.println("hovering over and clicking in " + elementName);
            Actions mouseAction = new Actions(wDriver);
            WebElement signIn = wDriver.findElement(By.xpath(locator));
            mouseAction.moveToElement(signIn).click().build().perform();
        }catch (Exception err1){
            System.out.println("unable to hover and click on element "+elementName);
        }

    }
}
