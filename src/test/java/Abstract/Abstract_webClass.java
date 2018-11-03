package Abstract;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Abstract_webClass {
    public static WebDriver driver;
    public static ExtentReports report;
    public static ExtentTest logger;
    @BeforeSuite
    public void openBrowser(){

        report = new ExtentReports("src/main/java/reportFolder/ExtentReport"+ UUID.randomUUID()+".html",true);

    }

    @Parameters("browser")
    @BeforeMethod
    public void loggerSesssion(String browser, Method methodName){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver");
        if(browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--start-maximized", "--incognito");
            driver = new ChromeDriver(options);
            //timeout using implicit wait}
        }
        else if(browser.equalsIgnoreCase("firefox")){
            driver=new FirefoxDriver();
            driver.navigate().to("https://www.google.com");
            driver.manage().window().maximize();

        }
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        logger=report.startTest(methodName.getName());
        logger.log(LogStatus.INFO,"Automation test started");
        ///Users/lhakpalama/Desktop/Maven_project/src/test/java/Abstract/Abstract_webClass.java
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

        driver.quit();
    }//end of after suite




}
