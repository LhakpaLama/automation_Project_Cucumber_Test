package priceline;

import ResusableObjects.processHandle;
import ResusableObjects.reuseMethod;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test_mlcalc_Main {
    //global variables
    WebDriver wDriver;
    Workbook sourceBook;
    WritableWorkbook resultBook;
    Sheet sourceSheet;
    WritableSheet resultSheet;
    int rowNum;
    SoftAssert myAssert;
    @BeforeSuite
    public void OpenBrowser() throws IOException, BiffException {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
        ChromeOptions cOption=new ChromeOptions();
        cOption.addArguments("start-maximized");
        wDriver= new ChromeDriver(cOption);
        wDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        File sourceFile=new File("src/main/resources/input_values.xls");
        File resultFile=new File("src/main/resources/result_values.xls");

        sourceBook=Workbook.getWorkbook(sourceFile);
        resultBook=Workbook.createWorkbook(resultFile,sourceBook);

        sourceSheet=sourceBook.getSheet(0);
        resultSheet=resultBook.getSheet(0);
        rowNum=sourceSheet.getRows();

        myAssert=new SoftAssert();
    }//end of before suit


    @AfterSuite
    public void CloseBrowser() throws IOException, WriteException {

        resultBook.write();
        resultBook.close();
        sourceBook.close();
        wDriver.quit();
    }//end of after suit

    @Test
    public void TestCase1_inputValue() throws Exception {

        for (int i = 1; i < rowNum; i++) {
            String purchasePrice=sourceSheet.getCell(0, i).getContents();
            String downPayment=sourceSheet.getCell(1, i).getContents();
            String interestRate=sourceSheet.getCell(2, i).getContents();
            String zipCode=sourceSheet.getCell(3, i).getContents();
            String payMonth=sourceSheet.getCell(4, i).getContents();
            String payYear=sourceSheet.getCell(5, i).getContents();

            reuseMethod.navigate(wDriver,"https://www.mlcalc.com");

            //verify the title of the page
            //Assert.assertEquals("Mortgage Calc",wDriver.getTitle());
            myAssert.assertEquals("Mortgage Calc",wDriver.getTitle());

           // wDriver.navigate().to("https://www.mlcalc.com");
            reuseMethod.sendKey(wDriver, "//*[@name='ma']",purchasePrice , "purchase price field");

            reuseMethod.sendKey(wDriver, "//*[@name='dp']", downPayment, "down Payment field");
            reuseMethod.sendKey(wDriver, "//*[@name='ir']",interestRate , "input rate field");
            reuseMethod.sendKey(wDriver, "//*[@name='zipCode']", zipCode, "zipCode field");

            reuseMethod.selectByVisibleText(wDriver, "//*[@name='sm']", payMonth, "date field");
            reuseMethod.selectByVisibleText(wDriver, "//*[@name='sy']", payYear , "year field");

            wDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            reuseMethod.mouseHoverClick(wDriver, "//*[@class='button-calculate action']", "Calculate button");

            String eachMonthPay=reuseMethod.getWebText(wDriver, "//*[@class='big']", 0, "monthly payment label");
            String exactPayOffDate=reuseMethod.getPayOffDate(wDriver, "//*[@class='big']", 2, "pay off date");


            System.out.println("monthly pay => "+eachMonthPay);
            System.out.println("pay of date => "+exactPayOffDate);

            Label monthlyPayLabel= new Label(6,i,eachMonthPay);
            Label totalMortgage=new Label(7,i,exactPayOffDate);

            resultSheet.addCell(monthlyPayLabel);
            resultSheet.addCell(totalMortgage);
        }
        myAssert.assertAll();
    }//end of test01 method


}//End of Test_mlcalc_main class
