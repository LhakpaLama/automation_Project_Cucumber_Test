package TestSuite;

//import Abstract.Abstract_class;
import Abstract.Abstract_class_parallel;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class YahooSearch extends Abstract_class_parallel {

    @Test
    public void YahooSearch() throws IOException {

        //navigate to yahoo page
        //logger=report.startTest("Yahoo Search Result");
        ResusableObjects.reuse_Method_Logger.navigate(logger,driver,"https://www.yahoo.com");
        String yahooTitle=driver.getTitle();

      /*  if(yahooTitle.equalsIgnoreCase("Yahoo")){
            logger.log(LogStatus.PASS,"The Yahoo title matches");
        }
        else
        {
            logger.log(LogStatus.FAIL,"The Yahoo title doesnt Match "+yahooTitle);
        }*/

        //verify the list count for the link
       // List<WebElement> linkCount=driver.findElements(By.xpath("//*[contains(@class,'Mstart(')]"));
        //logger.log(LogStatus.INFO,"The link count is "+linkCount.size());

        //enter a keyword on a search field
        ResusableObjects.reuse_Method_Logger.sendKey(logger,driver,"//*[@name='p']","Queens","Search field");
        //ResusableObjects.reuse_Method_Logger.mouseHoverClick(logger,wDriver,"//*[@id='uh-search-button']","Search Button");

        ResusableObjects.reuse_Method_Logger.mouseHoverClick(logger,driver,"//*[@id='uhch-button']","Search Button");
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        jse.executeScript("scroll(0,1000)");
        logger.log(LogStatus.INFO,"Scrolling to the bottom of the search result page");
    }

}
