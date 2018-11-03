package Yahoo_Search_Result;

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

public class YahooSearchResult {
    /*
    1. navigate to yahoo
    2. verify the home page title as Yahoo.
    3. verify the count of text links exist on home page
    4. enter a key word on  search field
    5. click on search
    6. scroll to bottom for search result
    7. capture search result
    8. send it to extent report
     */
    //declare globle variable
    WebDriver wDriver;
    ExtentReports report;
    ExtentTest logger;

    @BeforeSuite
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        ChromeOptions cOption = new ChromeOptions();
        //cOption.addArguments("--start-fullscreen");
        wDriver = new ChromeDriver(cOption);
        wDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //define the report path
        report=new ExtentReports("src/main/java/Report_Folder/ExtentReport"+ UUID.randomUUID()+".html",true);

    }//end of before suite

    @AfterSuite
    public void closeBrowser(){
        report.endTest(logger);
        report.flush();
        report.close();
       wDriver.quit();
    }//end of after suit

    @Test
    public void YahooSearch() throws IOException {

        //navigate to yahoo page
        //logger=report.startTest("Yahoo Search Result");
        ResusableObjects.reuse_Method_Logger.navigate(logger,wDriver,"https://www.yahoo.com");
        String yahooTitle=wDriver.getTitle();

        if(yahooTitle.equalsIgnoreCase("Yahoo")){
            logger.log(LogStatus.PASS,"The Yahoo title matches");
        }
        else
        {
            logger.log(LogStatus.FAIL,"The Yahoo title doesnt Match "+yahooTitle);
        }

        //verify the list count for the link
        List<WebElement> linkCount=wDriver.findElements(By.xpath("//*[contains(@class,'Mstart(')]"));
        logger.log(LogStatus.INFO,"The link count is "+linkCount.size());

        //enter a keyword on a search field
        ResusableObjects.reuse_Method_Logger.sendKey(logger,wDriver,"//*[@name='p']","Queens","Search field");
        //ResusableObjects.reuse_Method_Logger.mouseHoverClick(logger,wDriver,"//*[@id='uh-search-button']","Search Button");

        ResusableObjects.reuse_Method_Logger.mouseHoverClick(logger,wDriver,"//*[@id='uhch-button']","Search Button");
        JavascriptExecutor jse=(JavascriptExecutor)wDriver;
        jse.executeScript("scroll(0,1000)");
        logger.log(LogStatus.INFO,"Scrolling to the bottom of the search result page");
    }

}
