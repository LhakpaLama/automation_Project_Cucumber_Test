package TestRunner;

import ResusableObjects.reuseMethod;
import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class cucumberR {
    WebDriver driver=null;
    @Given("^user is navigated to yahoo home page$")
    public void user_is_navigated_to_yahoo_home_page() {

        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--start-maximized", "--incognito");
        driver = new ChromeDriver(options);
        System.out.println("Navigating to www.yahoo.com");
        driver.navigate().to("https://www.yahoo.com");
        //reuseMethod.navigate(driver,"https://www.yahoo.com");
    }

    @When("^user inputs their text in search box$")
    public void user_inputs_their_text_in_search_box() {
        System.out.println("entering Nutrition in search field");
        reuseMethod.sendKey(driver,"//*[@name='p']","Nutrition","search textBox");
    }

    @When("^press the search button$")
    public void press_the_search_button() {
        reuseMethod.mouseHoverClick(driver,"//*[@id='uh-search-button']","search Button");
    }

    @Then("^user should be able to see the total search result$")
    public void user_should_be_able_to_see_the_total_search_result() throws InterruptedException {
        Thread.sleep(3000);
        JavascriptExecutor jse=(JavascriptExecutor) driver;
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // Thread.sleep(3000);

        String searchNumber=driver.findElement(By.xpath("//*[@class='compPagination']")).getText();
        String[] eachNumber=searchNumber.split("Next");
        System.out.println("Number of search result is "+eachNumber[1]);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.quit();

    }
}
