package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.*;

import java.time.Duration;


public class OrderDetailsPage {
    private WebDriver driver;

    public OrderDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    private By dateField = By.cssSelector("input.Input_Input__1iN_Z.Input_Responsible__1jDKN");


    private By rentalDropdown = By.className("Dropdown-placeholder");

    private By rentalPeriodOption(String rentDays) {
        return By.xpath("//div[contains(@class,'Dropdown-option') and text()='" + rentDays + "']");
    }


    private By checkboxScooterColor(String colorId) {
        return By.id(colorId);
    }


    private By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");

    private By orderButton = By.xpath("//div[contains(@class,'Order_Buttons__1xGrp')]//button[normalize-space()='Заказать']");

    private By modalConfirmation = By.xpath("//div[contains(@class,'Order_Modal')]");


    private By yesButton = By.xpath("//button[text()='Да']");

    private By orderConfirmationHeader = By.xpath("//div[contains(@class,'Order_ModalHeader__') and contains(text(),'Заказ оформлен')]");


    public void setDate(String date) {
        driver.findElement(dateField).clear();
        driver.findElement(dateField).sendKeys(date);
        driver.findElement(dateField).sendKeys(Keys.ENTER);

    }

    public void selectRentalPeriod(String rentDays) {
        driver.findElement(rentalDropdown).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(rentalPeriodOption(rentDays)));
        driver.findElement(rentalPeriodOption(rentDays)).click();
    }

    public void selectCheckbox(String colorId) {
        driver.findElement(checkboxScooterColor(colorId)).click();
    }

    public void enterComment(String comment) {
        driver.findElement(commentField).click();
        driver.findElement(commentField).sendKeys(comment);
    }
    public void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(false);",
                element
        );
    }
    public void clickOrderButton() {
        WebElement btn = driver.findElement(orderButton);
        scrollTo(btn);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(btn));
        btn.click();
    }

    public void clickYesButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(yesButton));
        driver.findElement(yesButton).click();
    }


    public void fillOrderDetailsAndSubmit(String date, String rentDays, String colorId, String comment) {
        setDate(date);
        selectRentalPeriod(rentDays);
        selectCheckbox(colorId);
        enterComment(comment);
        clickOrderButton();
        clickYesButton();
    }

    public String getOrderConfirmationText() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderConfirmationHeader));

        return driver.findElement(orderConfirmationHeader).getText();
    }

}