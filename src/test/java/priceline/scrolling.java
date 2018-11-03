package priceline;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

public class scrolling {
    @Test

            public void scrollingMe()

    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        ChromeOptions cOption = new ChromeOptions();
        cOption.addArguments("start-maximized");
        WebDriver wDriver = new ChromeDriver(cOption);
        //wDriver.m
        wDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wDriver.navigate().to("https://www.mortgagecalculator.net/");
        JavascriptExecutor jse=(JavascriptExecutor)wDriver;
        //JavascriptException jse= (JavascriptException) wDriver;

        WebElement calBtn=wDriver.findElement(By.xpath("//*[@value='Calculate Now']"));
        jse.executeScript("arguments[0].scrollIntoView(true);",calBtn);

    }

}
