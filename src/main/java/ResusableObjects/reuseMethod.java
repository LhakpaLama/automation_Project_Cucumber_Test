package ResusableObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class reuseMethod {

    public static void navigate(WebDriver driver, String url){

        try{
            System.out.println("Navigating to url "+url);
            driver.navigate().to(url);
        }catch(Exception err){
            System.out.println("unable to navigate to url "+err);
        }
    }
    public static void sendKey(WebDriver driver, String locator, String userInput, String element) {
        try {
            System.out.println("Entering " + userInput + " in element " + element);
            WebElement input = driver.findElement(By.xpath(locator));
            input.clear();
            input.sendKeys(userInput);
        } catch (Exception e1) {
            System.out.println("undable to send info on element = " + element);

        }
    }//end of sendKeys method

    public static void selectByVisibleText(WebDriver wDriver, String locator,String visibleText, String elementName)  {
        try {
            System.out.println("Selecting from the element " + elementName);
            WebElement roomSelection = wDriver.findElement(By.xpath(locator));
            Select num_room = new Select(roomSelection);
            num_room.selectByVisibleText(visibleText);
        }catch (Exception err0){
            System.out.println("unable to make selection from element "+ elementName);
        }
    }//end of selectByVisibleText method

    public static void mouseHoverClick(WebDriver wDriver, String locator, String elementName){
        try {
            System.out.println("hovering over and clicking in " + elementName);
            Actions mouseAction = new Actions(wDriver);
            WebElement signIn = wDriver.findElement(By.xpath(locator));
            mouseAction.moveToElement(signIn).click().build().perform();
        }catch (Exception err1){
            System.out.println("unable to hover and click on element "+elementName);
        }

    }//end of mouseHoverClick

    public static String getWebText(WebDriver wDrive, String locator,int index, String elementName){
        String textCaptured=null;
        try {
            System.out.println("Getting the text from element " + elementName);

                WebElement webText = wDrive.findElements(By.xpath(locator)).get(index);
                textCaptured = webText.getText();

            //System.out.println("Header of the page is " + textCaptured);
        }catch(Exception err){
            System.out.println("unable to get the text from element "+elementName);
        }

        return textCaptured;

    }//end of getWebText method

    public static String getPayOffDate(WebDriver wDrive, String locator,int index, String elementName){

        String textCaptured=null;

        try {
            System.out.println("Getting the text from element " + elementName);
            WebElement webText;
            WebElement myPayOff=wDrive.findElements(By.xpath("//*[@nowrap='nowrap']")).get(index);

            if(myPayOff.getText().equals("Mortgage payoff date")){
                    webText=wDrive.findElements(By.xpath(locator)).get(index);
                    textCaptured = webText.getText(); }
            else
            { webText = wDrive.findElements(By.xpath(locator)).get(index+1);
              textCaptured = webText.getText(); }

        }catch(Exception err){
            System.out.println("unable to get the text from element "+elementName);
        }
        return textCaptured;
    }
}
