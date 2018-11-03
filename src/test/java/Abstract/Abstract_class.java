package Abstract;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Abstract_class {
    public static WebDriver driver;
    public static ExtentReports report;
    public static ExtentTest logger;
    @BeforeSuite
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--start-maximized", "--incognito");
        driver = new ChromeDriver(options);
        //timeout using implicit wait
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //define the report path
        report = new ExtentReports("src/main/java/reportFolder/ExtentReport"+ UUID.randomUUID()+".html",true);

    }

    @BeforeMethod
    public void loggerSesssion(Method methodName){
        logger=report.startTest(methodName.getName());
        logger.log(LogStatus.INFO,"Automation test started");

    }

    @AfterMethod
    public void closeTest(){
        report.endTest(logger);
    }

    @AfterSuite
    public void closeBrowser(){
        //end the test of the report

        //flush the report
        report.flush();
        //close the report
        report.close();
        //logger.log(LogStatus.INFO,"Automation test suite ended");

        driver.quit();
    }//end of after suite




}//end of class
