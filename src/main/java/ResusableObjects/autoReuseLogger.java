package ResusableObjects;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class autoReuseLogger {
    //method ro navigating to a page
    public static void navigate(ExtentTest logger, WebDriver driver, String url) throws IOException {
        try{
            //System.out.print("Navigating to " + url);
            logger.log(LogStatus.INFO,"Navigating to " + url);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.navigate().to(url);
        }catch (Exception err) {
            //System.out.println("Unable to navigate to the url... " + err);
            logger.log(LogStatus.FAIL,"Unable to navigate to the url... " + err);
            getScreenshot(driver,logger,"URL Error");
        }
    }//end of navigate method

    //method for clicking on an element
    public static void clickMethod(ExtentTest logger,WebDriver driver, String locator, String elementName) throws IOException {

        try{
            System.out.println("Clicking on element " + elementName);
            logger.log(LogStatus.INFO,"Clicking on element " + elementName);
            //store the locator into WebElement variable
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement clickbtn = driver.findElement(By.xpath(locator));
            clickbtn.click();
            //logger.log(LogStatus.PASS,"Succesfully clicked on the element " + elementName);
        }catch (Exception err){
            //System.out.println("Unable to click on element " + elementName);
            logger.log(LogStatus.FAIL,"Unable to click on element " + elementName);
            getScreenshot(driver,logger,elementName);
        }//end of try catch
    }//end of click method

    //method for clearing on an element
    public static void clearMethod(ExtentTest logger, WebDriver driver, String locator, String elementName) throws IOException {
        try{
            logger.log(LogStatus.INFO,"Clearing on element " + elementName);
            //store the locator into WebElement variable
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement clrBtn = driver.findElement(By.xpath(locator));
            clrBtn.clear();
        }catch (Exception err){
            logger.log(LogStatus.FAIL,"Unable to clear on element " + elementName + " " + err);
            getScreenshot(driver,logger,elementName);
        }//end of try catch
    }//end of clear method

    //method for clicking on an element by index
    public static void clickMethodByIndex(ExtentTest logger,WebDriver driver, String locator, int indexNumber, String elementName){

        try{
            logger.log(LogStatus.INFO,"Clicking on element " + elementName);
            //store the locator into WebElement variable
            WebElement clickBtn = driver.findElements(By.xpath(locator)).get(indexNumber);
            clickBtn.click();
        }catch (Exception err){
            logger.log(LogStatus.FAIL,"Unable to click on element " + elementName);
        }//end of try catch
    }//end of click by index method

    //method for submitting on an element
    public static void submitMethod(WebDriver driver, String locator, String elementName){
        try{
            System.out.println("Submitting  on element " + elementName);
            //store the locator into WebElement variable
            WebElement submitBtn = driver.findElement(By.xpath(locator));
            submitBtn.submit();
        }catch (Exception err){
            System.out.println("Unable to Submit on element " + elementName + " " + err);
        }//end of try catch
    }//end of submit method

    //method for entering on an element
    public static void sendKeysMethod(ExtentTest logger,WebDriver driver, String locator, String userInput, String elementName) throws IOException {
        try{
            //System.out.println("Entering " + userInput + " in element " + elementName);
            logger.log(LogStatus.INFO,"Entering " + userInput + " in element " + elementName);
            //store the locator into WebElement variable
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement input = driver.findElement(By.xpath(locator));
            input.sendKeys(userInput);
        }catch (Exception err){
            //System.out.println("Unable to send info on element " + elementName);
            logger.log(LogStatus.FAIL,"Unable to send info on element " + elementName);
            getScreenshot(driver,logger,elementName);
        }//end of try catch
    }//end of Send Keys method

    //dropdown method by visible text
    public static void selectByText(ExtentTest logger, WebDriver driver,String locator,String value,String elementName) throws IOException {
        try{
            logger.log(LogStatus.INFO,"Selecting " + value + " from dropdown " + elementName);
            //define the Web Element
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement element = driver.findElement(By.xpath(locator));
            //define the select command
            Select select = new Select(element);
            // select by visible text
            select.selectByVisibleText(value);
        }catch (Exception err){
            logger.log(LogStatus.FAIL,"Unable to select a value from dropdown " + elementName + " " + err);
            getScreenshot(driver,logger,elementName);
        }//end of try catch
    }//end of select by text method

    //method for getText
    public static String captureText(ExtentTest logger, WebDriver driver,String locator,String elementName) throws IOException {
        String textValue = null;
        try{
            logger.log(LogStatus.INFO,"Capturing text " + elementName);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            textValue = driver.findElement(By.tagName(locator)).getText();
        }catch (Exception err){
            logger.log(LogStatus.FAIL,"Unable to capture text " + elementName + " " + err);
            getScreenshot(driver,logger,elementName);
        }//end of try catch
        return textValue;
    }//end of capture text method

    public static void mouseHover(ExtentTest logger, WebDriver wDriver, String locator, String elementName) throws IOException {
        try {
            logger.log(LogStatus.INFO,"hovering over on " + elementName);
            Actions mouseAction = new Actions(wDriver);
            wDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement webObject = wDriver.findElement(By.xpath(locator));
            mouseAction.moveToElement(webObject).build().perform();

        }catch (Exception err1){
            logger.log(LogStatus.FAIL,"unable to hover on "+elementName);
            getScreenshot(wDriver,logger,elementName);
        }

    }//end of mouseHover

    public static void mouseHoverIndex(ExtentTest logger, WebDriver wDriver, String locator, int index,String elementName) throws IOException {
        try {
            logger.log(LogStatus.INFO,"hovering over on " + elementName);
            Actions mouseAction = new Actions(wDriver);
            wDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement webObject = wDriver.findElements(By.xpath(locator)).get(index);
            mouseAction.moveToElement(webObject).build().perform();

        }catch (Exception err1){
            logger.log(LogStatus.FAIL,"unable to hover on "+elementName);
            getScreenshot(wDriver,logger,elementName);
        }

    }//end of mouseHoverClick

    public static void getScreenshot(WebDriver driver, ExtentTest logger, String screenshotName) throws IOException {
        // String path = "C:\\Users\\sumon.kashem\\Desktop\\Screenshots\\";
        String path = "src\\main\\java\\Report_Folder\\ScreenShots\\";
        String fileName = screenshotName + ".png";
        File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //Now you can do whatever you need to do with, for example copy somewhere
        FileUtils.copyFile(sourceFile, new File(path + fileName));
        //String imgPath = directory + fileName;
        String image = logger.addScreenCapture("ScreenShots\\" + fileName);
        logger.log(LogStatus.FAIL, "", image);
    }
}
