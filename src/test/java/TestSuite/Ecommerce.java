package TestSuite;

//import Abstract.Abstract_class;
import Abstract.Abstract_class_parallel;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.io.IOException;

public class Ecommerce extends Abstract_class_parallel {

    @Test
    public void Tshirt() throws IOException, InterruptedException {

        //navigate to  homepage
        ResusableObjects.autoReuseLogger.navigate(logger, driver, "http://www.automationpractice.com/index.php");

        //verify the home page title
        String pageTitle = driver.getTitle();
        if (pageTitle.equalsIgnoreCase("My Store")) {
            logger.log(LogStatus.PASS, "The page title matches");
        } else {
            logger.log(LogStatus.FAIL, "The page title doesn't match " + pageTitle);
        }
        //hovering over the woman tab
        ResusableObjects.autoReuseLogger.mouseHover(logger, driver, "//*[@title='Women']", "woman tab");
        //clicking on t-shirt link
        Thread.sleep(3000);
        ResusableObjects.autoReuseLogger.clickMethodByIndex(logger, driver, "//*[@title='T-shirts']", 0, "t-shirt link");
        //for scroll down
        Thread.sleep(3000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0,550)");
        Thread.sleep(3000);
        //hovering over the woman picture
        ResusableObjects.autoReuseLogger.mouseHover(logger, driver, "//*[@class='product-image-container']", "woman picture");
        //click on add cart button
        Thread.sleep(3000);
        ResusableObjects.autoReuseLogger.clickMethod(logger, driver, "//*[@title='Add to cart']", "Add cart button");
        //verify pop off message
        Thread.sleep(3000);
        String message = ResusableObjects.autoReuseLogger.captureText(logger, driver, "h2", "message ");
        if (message.equalsIgnoreCase("Product successfully added to your shopping cart")) {
            logger.log(LogStatus.PASS, "user expected message displayed as " + message);
        } else {
            logger.log(LogStatus.FAIL, "expected message not matched."+message);
        }
        Thread.sleep(3000);
        //click on proceed to check out button
        ResusableObjects.autoReuseLogger.clickMethod(logger, driver, "//*[@title='Proceed to checkout']", "proceed to checkout button");
        Thread.sleep(3000);
        ResusableObjects.autoReuseLogger.clearMethod(logger, driver, "//*[@class='cart_quantity_input form-control grey']", "clear text box");
        //change quantity to 3
        ResusableObjects.autoReuseLogger.sendKeysMethod(logger, driver, "//*[@name='quantity_1_1_0_0']", "3", "Quantity text box");
        //click on proceed to check out
        ResusableObjects.autoReuseLogger.clickMethod(logger, driver, "//*[@class='button btn btn-default standard-checkout button-medium']", "check out button");
    }
}
