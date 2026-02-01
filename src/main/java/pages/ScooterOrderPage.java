package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

// класс страницы заказа самоката
public class ScooterOrderPage {
    public WebDriver driver;

    private By fieldName = By.cssSelector("input[placeholder='* Имя']");

    private By fieldNameErrorMessage = By.xpath("//input[@placeholder='* Имя']/following-sibling::div[contains(@class,'Input_ErrorMessage')]");

    private By fieldSurname = By.cssSelector("input[placeholder='* Фамилия']");

    private By fieldSurnameErrorMessage = By.xpath("//input[@placeholder='* Фамилия']/ancestor::div[contains(@class,'Input_InputContainer')]" +
            "//div[contains(@class,'Input_ErrorMessage')]");

    private By fieldAddress = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");

    private By fieldAddressErrorMessage = By.xpath("//div[contains(text(),'Введите корректный адрес')]");

    private By dropdownListMetroStation = By.cssSelector("input[placeholder='* Станция метро']");

    private By dropdownListMetroStationErrorMessage = By.xpath("//div[contains(text(),'Выберите станцию')]");

    private By metroStationSelection(String stationName) {
        return By.xpath("//li[contains(@class,'select-search__row')]//button[normalize-space()='" + stationName + "']"
        );
    }

    private By fieldPhoneNumber = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");

    private By fieldPhoneNumberErrorMessage = By.xpath("//div[contains(text(),'Введите корректный номер')]");

    private By nextButton = By.xpath("//button[text()='Далее']");

    private By iconScooter = By.className("Header_LogoScooter__3lsAR");

    private By whoIsTheScooterFor = By.xpath("//div[contains(@class,'Order_Header') and text()='Для кого самокат']");

    public ScooterOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setFirstName(String firstName) {
        WebElement element = driver.findElement(fieldName);
        element.clear();
        element.sendKeys(firstName);
        driver.findElement(fieldSurname).click();
    }

    public void setLastName(String lastName) {
        driver.findElement(fieldSurname).clear();
        driver.findElement(fieldSurname).sendKeys(lastName);
    }

    public void setAddress(String address) {
        driver.findElement(fieldAddress).clear();
        driver.findElement(fieldAddress).sendKeys(address);
    }

    public void selectMetroStation(String stationName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement metroInput = wait.until(
                ExpectedConditions.elementToBeClickable(dropdownListMetroStation)
        );
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", metroInput);

        metroInput.click();
        metroInput.clear();
        metroInput.sendKeys(stationName);

        By optionLocator = By.xpath(
                "//div[contains(@class,'select-search__select')]//button[normalize-space()='" + stationName + "']"
        );

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(optionLocator)
        );
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", option);

        option.click();
    }

    public void setPhone(String phoneNumber) {
        driver.findElement(fieldPhoneNumber).clear();
        driver.findElement(fieldPhoneNumber).sendKeys(phoneNumber);
    }


    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }


    public void unfocus() {
        driver.findElement(whoIsTheScooterFor).click();
    }


    private String getErrorText(By locator) {
        List<WebElement> errors = driver.findElements(locator);
        return errors.isEmpty() ? "" : errors.get(0).getText();
    }

    public String getFirstNameErrorText() {
        return getErrorText(fieldNameErrorMessage);
    }

    public String getLastNameErrorText() {
        return getErrorText(fieldSurnameErrorMessage);
    }

    public String getAddressErrorText() {
        return driver.findElement(fieldAddressErrorMessage).getText();
    }

    public String getMetroErrorText() {
        return getErrorText(dropdownListMetroStationErrorMessage);
    }

    public String getPhoneErrorText() {
        return driver.findElement(fieldPhoneNumberErrorMessage).getText();
    }


    public void completeOrderForm(
            String firstName,
            String lastName,
            String address,
            String metroStation,
            String phoneNumber
    )

    {
        driver.findElement(fieldName).sendKeys(firstName);

        driver.findElement(fieldSurname).sendKeys(lastName);

        driver.findElement(fieldAddress).sendKeys(address);

        selectMetroStation(metroStation);

        driver.findElement(fieldPhoneNumber).sendKeys(phoneNumber);

        driver.findElement(nextButton).click();
    }


    public void clickIconScooter() {
        driver.findElement(iconScooter).click();
    }
}