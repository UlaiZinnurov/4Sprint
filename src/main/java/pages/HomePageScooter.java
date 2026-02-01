package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePageScooter {
    private WebDriver driver;

    public HomePageScooter(WebDriver driver) {
        this.driver = driver;
    }

    private By cookieAcceptButton = By.id("rcc-confirm-button");
    private By firstOrderButton = By.className("Button_Button__ra12g");

    private By secondBottomOrderButton = By.className("Button_Middle__1CSJM");

    public By accordionButton(int Index) {
        return By.id("accordion__heading-" + Index);
    }

    private By answerAccordion(int Index) {
        return By.id("accordion__panel-" + Index);
    }

    private By orderStatusButton = By.className("Header_Link__1TAG7");

    private By orderStatusInputField = By.cssSelector("input.Input_Input__1iN_Z.Header_Input__xIoUq");

    private By goButton = By.className("Header_Button__28dPO");

    private By iconYandex = By.cssSelector("a[href='//yandex.ru']");

    public void acceptCookies() {
        if (driver.findElements(cookieAcceptButton).isEmpty()) {
            return;
        }

        WebElement button = driver.findElement(cookieAcceptButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }


    public void clickFirstOrderButton() {
        driver.findElement(firstOrderButton).click();
    }

    public void clickBottomOrderButton() {
        scrollToElement(secondBottomOrderButton);
        driver.findElement(secondBottomOrderButton).click();
    }


    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public void clickAccordionButton(int index) {
        By btn = accordionButton(index);
        scrollToElement(btn);
        driver.findElement(accordionButton(index));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(accordionButton(index)))
                .click();
    }

    public String getAccordionAnswerText(int index, String expectedText) {
        WebElement answer = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(answerAccordion(index)));
        return answer.getText();
    }

    public void clickYandexLogo() {
        driver.findElement(iconYandex).click();
    }

    public String getYandexLogoHref() {
        return driver.findElement(iconYandex).getAttribute("href");
    }
    public void clickOrderStatusButton () {
        acceptCookies();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(orderStatusButton))
                .click();
    }
    public void enterTrackNumber (String number){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(orderStatusInputField)).sendKeys(number);
    }

    public void clikGoButton () {
        driver.findElement(goButton).click();
    }
}