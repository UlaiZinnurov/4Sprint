package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderStatusPage {
    private WebDriver driver;


    public OrderStatusPage(WebDriver driver) {
        this.driver = driver;
    }
    private By notFoundImg = By.cssSelector("img[alt='Not found']");

    public boolean checkOrderNotFoundMessage() {
        WebElement notFoundImage = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(notFoundImg));
        return notFoundImage.isDisplayed();
    }
}
