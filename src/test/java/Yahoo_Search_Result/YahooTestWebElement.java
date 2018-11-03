package Yahoo_Search_Result;

import Abstract.Abstract_class;
import Abstract.Abstract_webClass;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.apache.xalan.xsltc.compiler.util.Type.Element;

public class YahooTestWebElement extends Abstract_webClass {


    @Test
    public void YahooWebElementExamine() throws IOException, InterruptedException {

        driver.navigate().to("https://www.yahoo.com");
        String pageTitle=driver.getTitle();
            if(pageTitle.equalsIgnoreCase("Yahoo")){
            logger.log(LogStatus.PASS,"Page Title matched as "+pageTitle);
            } else {
            logger.log(LogStatus.FAIL,"Page Title doesn't match. Page title is "+pageTitle);
            }
        List <WebElement> countMenuLinks=driver.findElements(By.xpath("//*[contains(@class,'D(ib) Mstart(21px')]"));
            int totalMenuLinks=countMenuLinks.size();
            logger.log(LogStatus.INFO,"total menu links are "+totalMenuLinks);
        //entering nutrition on search box
        ResusableObjects.reuse_Method_Logger.sendKey(logger,driver,"//*[@name='p']","Nutrition","Search field");
        //clicking the search button
        ResusableObjects.reuse_Method_Logger.mouseHoverClick(logger,driver,"//*[@id='uh-search-button']","Search Button");
       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //scrolling down
        Thread.sleep(3000);
        JavascriptExecutor jse=(JavascriptExecutor) driver;
       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");

       // Thread.sleep(3000);

        String searchNumber=driver.findElement(By.xpath("//*[@class='compPagination']")).getText();
        String[] eachNumber=searchNumber.split("Next");
        System.out.println("Number of search result is "+eachNumber[1]);
        logger.log(LogStatus.INFO,"Number of search result is "+eachNumber[1]);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        jse.executeScript("window.scrollTo(document.body.scrollHeight,0)");



        ResusableObjects.reuse_Method_Logger.mouseHoverClick(logger,driver,"//*[@id='yucs-login_signIn']","Search Button");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        boolean elementState = driver.findElement(By.xpath("//*[@id='persistent']")).isSelected(); //inspect the check mark and put the property in xpath
        if(elementState){
            logger.log(LogStatus.PASS,"element is checked by default");
        }
        else
        {
            logger.log(LogStatus.FAIL,"element is not checked by default");
            //getScreenshot
        }
        ResusableObjects.reuse_Method_Logger.sendKey(logger, driver,"//*[@name='username']","ABSCASDF@yahoo.com","email id field");

        ResusableObjects.reuse_Method_Logger.mouseHoverClick(logger,driver,"//*[@name='signin']","next button");

        //error entering wrong email
        String errMsg = "Sorry, we don't recognize this email.";

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String resultErrMsg=driver.findElement(By.xpath("//*[@class='row error']")).getText();
        System.out.println(resultErrMsg);//in order to verify if the error message is captured
        if(errMsg.equalsIgnoreCase(resultErrMsg)){
            logger.log(LogStatus.PASS,"error message matches as = "+resultErrMsg);
        }else{
            logger.log(LogStatus.FAIL,"error message does not match. Error message displayed is "+resultErrMsg);
        }
    }
}
