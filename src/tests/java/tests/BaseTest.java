package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import constants.Urls;
import pages.HomePageScooter;
import pages.OrderDetailsPage;
import pages.OrderStatusPage;
import pages.ScooterOrderPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected HomePageScooter homePage;
    protected ScooterOrderPage scooterOrder;
    protected OrderDetailsPage orderDetails;
    public OrderStatusPage orderStatusPage;

    @Before
    public void startDriverChrome() {
        ChromeOptions options = new ChromeOptions();

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        homePage = new HomePageScooter(driver);
        scooterOrder = new ScooterOrderPage(driver);
        orderDetails = new OrderDetailsPage(driver);
        orderStatusPage = new OrderStatusPage(driver);
        driver.get(Urls.BASE_URL);
        homePage.acceptCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}