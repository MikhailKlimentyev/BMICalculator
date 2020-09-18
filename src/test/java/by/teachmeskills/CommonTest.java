package by.teachmeskills;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class CommonTest {

    protected WebDriver driver;

    @BeforeClass
    public void setDriverLocation() {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
    }

    @BeforeMethod
    public void init() {
        driver = new ChromeDriver();
        driver.get("https://healthunify.com/bmicalculator/");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}
