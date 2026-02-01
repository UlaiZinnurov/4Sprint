package tests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ScooterOrderTest extends tests.BaseTest {

    // параметры, которые будут применяться в тестах
    String firstName;
    String lastName;
    String address;
    String metroStation;
    String phoneNumber;
    String date;
    String rentDays;
    String colorId;
    String comment;

    public ScooterOrderTest(String firstName, String lastName, String address, String metroStation, String phoneNumber, String date, String rentDays, String colorId, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentDays = rentDays;
        this.colorId = colorId;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Тест {index}: заказ для {0} {1} - {7} самокат")
    public static Collection<Object[]> getOrderTest() {
        return Arrays.asList(new Object[][]{
                {"Даниэль", "Кучерявый", "Лось Анджелас, Брайтен Битч", "Новокузнецкая", "89050743762", "25.12.2025", "трое суток", "grey", "Оставить у соседки"},
                {"Кира", "Найкли", "Париж, Карла Маркса 10", "Таганская", "+76403652736", "20.01.2026", "сутки", "black", "Мы будем кататься всей семьёй разом на одном"},
                {"Асидора", "Крипошвилли", "Пекин, Патрики 10", "Китай-город", "+79016453728", "30.11.2025", "семеро суток", "black", "Так здорово кататься в минус 20!"}
        });
    }

    @Test

    public void upperOrderButtonFTest() {

        homePage.clickFirstOrderButton();

        scooterOrder.completeOrderForm(firstName, lastName, address, metroStation, phoneNumber);

        orderDetails.fillOrderDetailsAndSubmit(date, rentDays, colorId, comment);
        String text = orderDetails.getOrderConfirmationText();
        assertTrue(text.contains("Заказ оформлен"));

    }


    @Test
    public void lowerOrderButtonTest() {

        homePage.clickBottomOrderButton();

        scooterOrder.completeOrderForm(firstName, lastName, address, metroStation, phoneNumber);

        orderDetails.fillOrderDetailsAndSubmit(date, rentDays, colorId, comment);

        String text = orderDetails.getOrderConfirmationText();
        assertTrue(text.contains("Заказ оформлен"));

    }
}