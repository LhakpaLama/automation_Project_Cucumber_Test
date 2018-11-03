package autoPractiseDotCom;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class autoPractiseMain {

    //declare all the global variables before annotation methods
    WebDriver driver;
    ExtentReports report;
    ExtentTest logger1;
    ExtentTest logger2;
    @BeforeSuite
    public void openBrowser(){
        //define the driver path
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--start-maximized", "--incognito");
        driver = new ChromeDriver(options);
        //timeout using implicit wait
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //define the report path
        //report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport" + UUID.randomUUID()+".html",true);
        report = new ExtentReports("src/main/java/reportFolder/ExtentReport"+ UUID.randomUUID()+".html",true);

    }//end of before suite


    @AfterSuite
    public void closeBrowser(){
        //end the test of the report
        report.endTest(logger1);
        report.endTest(logger2);
        //flush the report
        report.flush();
        //close the report
        report.close();

        driver.quit();
    }//end of after suite

    @Test
    public void testCase1() throws IOException, InterruptedException {
        //start the test
        logger1 = report.startTest("Proceed to check for T-shirt");
        //navigate to  homepage
        ResusableObjects.autoReuseLogger.navigate(logger1, driver, "http://www.automationpractice.com/index.php");

        //verify the home page title
        String pageTitle = driver.getTitle();
        if (pageTitle.equalsIgnoreCase("My Store")) {
            logger1.log(LogStatus.PASS, "The page title matches");
        } else {
            logger1.log(LogStatus.FAIL, "The page title doesn't match " + pageTitle);
        }
        //hovering over the woman tab
        ResusableObjects.autoReuseLogger.mouseHover(logger1, driver, "//*[@title='Women']", "woman tab");
        //clicking on t-shirt link
        Thread.sleep(3000);
        ResusableObjects.autoReuseLogger.clickMethodByIndex(logger1, driver, "//*[@title='T-shirts']", 0, "t-shirt link");
        //for scroll down
        Thread.sleep(3000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0,550)");
        Thread.sleep(3000);
        //hovering over the woman picture
        ResusableObjects.autoReuseLogger.mouseHover(logger1, driver, "//*[@class='product-image-container']", "woman picture");
        //click on add cart button
        Thread.sleep(3000);
        ResusableObjects.autoReuseLogger.clickMethod(logger1, driver, "//*[@title='Add to cart']", "Add cart button");
        //verify pop off message
        Thread.sleep(3000);
        String message = ResusableObjects.autoReuseLogger.captureText(logger1, driver, "h2", "message ");
        if (message.equalsIgnoreCase("Product successfully added to your shopping cart")) {
            logger1.log(LogStatus.PASS, "user expected message displayed as " + message);
        } else {
            logger1.log(LogStatus.FAIL, "expected message not matched."+message);
        }
        Thread.sleep(3000);
        //click on proceed to check out button
        ResusableObjects.autoReuseLogger.clickMethod(logger1, driver, "//*[@title='Proceed to checkout']", "proceed to checkout button");
        Thread.sleep(3000);
        ResusableObjects.autoReuseLogger.clearMethod(logger1, driver, "//*[@class='cart_quantity_input form-control grey']", "clear text box");
        //change quantity to 3
        ResusableObjects.autoReuseLogger.sendKeysMethod(logger1, driver, "//*[@name='quantity_1_1_0_0']", "3", "Quantity text box");
        //click on proceed to check out
        ResusableObjects.autoReuseLogger.clickMethod(logger1, driver, "//*[@class='button btn btn-default standard-checkout button-medium']", "check out button");
    }
    //}//end of test

    @Test(dependsOnMethods = "testCase1")
    public void testCase2() throws IOException, InterruptedException {
        logger2 = report.startTest("Proceed to check out with summer dress");
        //hover over to dress tab
        Thread.sleep(1000);
        JavascriptExecutor jse3=(JavascriptExecutor)driver;
        jse3.executeScript("scroll(0,-500)");

        Thread.sleep(3000);
        ResusableObjects.autoReuseLogger.mouseHoverIndex(logger2,driver,"//*[@title='Dresses']",1,"Dress tab");
        //click on the summer dress
        Thread.sleep(3000);
        ResusableObjects.autoReuseLogger.clickMethodByIndex(logger2,driver,"//*[@title='Summer Dresses']",1,"click summer dress button");
        Thread.sleep(2000);
        //scroll down
        JavascriptExecutor jse1=(JavascriptExecutor)driver;
        jse1.executeScript("scroll(0,300)");
        Thread.sleep(2000);
        //hover over first picture of the dress
        ResusableObjects.autoReuseLogger.mouseHover(logger2,driver,"//*[@class='product-image-container']","hover over first picture");
        Thread.sleep(2000);
        //click on more tab
        ResusableObjects.autoReuseLogger.clickMethod(logger2,driver,"//*[@title='View']","click on more tab");
        Thread.sleep(2000);
        ResusableObjects.autoReuseLogger.clearMethod(logger2,driver,"//*[@id='quantity_wanted']","quantity text box");
        //change the quantity to 4
        ResusableObjects.autoReuseLogger.sendKeysMethod(logger2,driver,"//*[@id='quantity_wanted']","4","Quantity text box");
        Thread.sleep(2000);
        //select size from drop down box
        ResusableObjects.autoReuseLogger.selectByText(logger2,driver,"//*[@id='group_1']","M","Select Size");
        Thread.sleep(2000);
        //click to add button
        ResusableObjects.autoReuseLogger.clickMethod(logger2,driver,"//*[@class='exclusive']","click to add button");
        Thread.sleep(2000);
        //verify message
       String message1=ResusableObjects.autoReuseLogger.captureText(logger2,driver,"h2","message ");

        if(message1.equalsIgnoreCase("Product successfully added to your shopping cart")){
            logger2.log(LogStatus.PASS,"user expected message displayed as "+message1);
        }
        else
        {
            logger2.log(LogStatus.FAIL,"expected message not matched. "+message1);
        }
        Thread.sleep(2000);
        //click on proceed to check out
        ResusableObjects.autoReuseLogger.clickMethod(logger2,driver,"//*[@class='btn btn-default button button-medium']","proceed to check out");
        Thread.sleep(2000);
        //click on delete icon to delete item
        ResusableObjects.autoReuseLogger.clickMethod(logger2,driver,"//*[@class='icon-trash']","delete button");
        Thread.sleep(2000);
        ResusableObjects.autoReuseLogger.clickMethod(logger2,driver,"//*[@class='icon-trash']","delete button");
        Thread.sleep(3000);
        //verify shopping cart is empty
        String message2=driver.findElement(By.xpath("//p[@class='alert alert-warning']")).getText();
        if(message2.equalsIgnoreCase("Your shopping cart is empty.")){
            logger2.log(LogStatus.PASS,"user expected message displayed as "+message2);
        }
        else
        {
            logger2.log(LogStatus.FAIL,"expected message not matched. Actual message displayed is "+message2);
        }
        Thread.sleep(2000);
    }//end of test 2



}//end of the class
