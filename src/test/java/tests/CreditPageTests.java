package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pages.StartPage;
import io.github.bonigarcia.wdm.WebDriverManager;



import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;


public class CreditPageTests {

    StartPage startPage = open("http://localhost:8080/", StartPage.class);

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().driverVersion("117.0").setup();
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        SQLHelper.clearDB();
    }

    // 1 positive
    @DisplayName("Успешная покупка в кредит по карте, со статусом APPROVED. номер карты с пробелами")
    @Test
    void creditPositiveAllFieldValidWithSpacesApproved() {
        var cardInfo = DataHelper.getApprovedCard();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        creditPage.checkApprovedNotification();
        assertEquals("APPROVED", SQLHelper.getCreditRequestStatus());
    }

    // 2 positive
    @DisplayName("Успешная покупка в кредит по карте, со статусом APPROVED. номер карты без пробелов")
    @Test
    void creditPositiveAllFieldValidWithoutSpacesApproved() {
        var cardInfo = DataHelper.getApprovedCardWithoutSpaces();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        creditPage.checkApprovedNotification();
        assertEquals("APPROVED", SQLHelper.getCreditRequestStatus());
    }

    // 3 positive
    @DisplayName("Успешная покупка в кредит по карте, со статусом APPROVED. двойная фамилия")
    @Test
    void creditPositiveAllFieldValidDoubleSurnameApproved() {
        var cardInfo = DataHelper.getApprovedCardDoubleSurname();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        creditPage.checkApprovedNotification();
        assertEquals("APPROVED", SQLHelper.getCreditRequestStatus());
    }

    // 1 negative
    @DisplayName("Неправильное заполнение формы - номер карты не заполнен")
    @Test
    void creditNegativeCardNumberIsEmpty() {
        var cardInfo = DataHelper.getCardWithoutCardNumber();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        creditPage.checkRequiredFieldNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 2 negative
    @DisplayName("Неправильное заполнение формы - номер карты из 15ти цифр")
    @Test
    void creditNegativeCardNumberIs15Digits() {
        var cardInfo = DataHelper.getCardWith15DigitCardNumber();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        creditPage.checkWrongFormatNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 3 negative
    @DisplayName("Неправильное заполнение формы - номер карты из 17ти цифр")
    @Test
    void creditNegativeCardNumberIs17Digits() {
        var cardInfo = DataHelper.getCardWith17DigitCardNumber();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        assertEquals("4444 4444 4444 4444", creditPage.getCardNumber());
    }

    // 4 negative
    @DisplayName("Неправильное заполнение формы - номер карты содержит запрещённые символы")
    @Test
    void creditNegativeBadCharactersInCardNumber() {
        var cardInfo = DataHelper.getCardWithBadCharactersInCardNumber();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        creditPage.checkWrongFormatNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 5 negative
    @DisplayName("Неправильное заполнение формы - месяц не заполнен")
    @Test
    void creditNegativeMonthIsEmpty() {
        var cardInfo = DataHelper.getApprovedCardWithNoMonth();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        creditPage.checkRequiredFieldNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 6 negative
    @DisplayName("Невалидный период действия карты: месяц предшествующий текущему")
    @Test
    void creditNegativePastMonth() {
        var cardInfo = DataHelper.getExpiredCard1Month();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        creditPage.checkExpiredNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 7 negative
    @DisplayName("Неправильное заполнение формы - месяц из трёх цифр")
    @Test
    void creditNegativeMonthIs3Digits() {
        var cardInfo = DataHelper.getApprovedCardMonthIs3Digits();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        assertEquals("11", creditPage.getMonth());
    }

    // 8 negative
    @DisplayName("Невалидный период действия карты: месяц = 00")
    @Test
    void creditNegativeMonthIs00() {
        var cardInfo = DataHelper.getApprovedCardMonthIs00();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        creditPage.checkWrongValidityNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 9 negative
    @DisplayName("Невалидный период действия карты: месяц = 13")
    @Test
    void creditNegativeMonthIs13() {
        var cardInfo = DataHelper.getApprovedCardMonthIs13();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        creditPage.checkWrongValidityNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 10 negative
    @DisplayName("Неправильное заполнение формы - год не заполнен")
    @Test
    void creditNegativeYearIsEmpty() {
        var cardInfo = DataHelper.getApprovedCardWithNoYear();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        creditPage.checkRequiredFieldNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 11 negative
    @DisplayName("Невалидный период действия карты: год предшествующий текущему")
    @Test
    void creditNegativePastYear() {
        var cardInfo = DataHelper.getExpiredCard1Year();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        creditPage.checkExpiredNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 12 negative
    @DisplayName("Неправильное заполнение формы - владелец не заполнен")
    @Test
    void creditNegativeCardHolderIsEmpty() {
        var cardInfo = DataHelper.getApprovedCardWithNoCardHolder();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        creditPage.checkRequiredFieldNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 13 negative
    @DisplayName("Неправильное заполнение формы - имя владельца на кириллице")
    @Test
    void creditNegativeCardHolderIsInCyrillic() {
        var cardInfo = DataHelper.getApprovedCardWithCardHolderInCyrillic();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        creditPage.checkWrongFormatNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 14 negative
    @DisplayName("Неправильное заполнение формы - имя владельца содержит запрещённые символы")
    @Test
    void creditNegativeBadCharactersInCardHolder() {
        var cardInfo = DataHelper.getCardWithBadCharactersInCardHolder();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        creditPage.checkWrongFormatNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 15 negative
    @DisplayName("Неправильное заполнение формы - CVC/CVV не заполнен")
    @Test
    void creditNegativeCvcCvvIsEmpty() {
        var cardInfo = DataHelper.getApprovedCardWithNoCvcCvv();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        creditPage.checkRequiredFieldNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 16 negative
    @DisplayName("Отказ в покупке в кредит по карте, со статусом DECLINED")
    @Test
    void creditNegativeAllFieldValidDeclined() {
        var cardInfo = DataHelper.getDeclinedCard();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        creditPage.checkDeclinedNotification();
        assertEquals("DECLINED", SQLHelper.getCreditRequestStatus());
    }

    // 17 negative
    @DisplayName("Невалидный период действия карты: год слишком большой")
    @Test
    void creditNegativeTooFarFutureDate() {
        var cardInfo = DataHelper.getFutureCard();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        creditPage.checkWrongValidityNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 18 negative
    @DisplayName("Неправильное заполнение формы - CVC/CVV из двух цифр")
    @Test
    void creditNegativeTwoDigitCvcCvv() {
        var cardInfo = DataHelper.getApprovedCardTwoDigitCvcCvv();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        creditPage.checkWrongFormatNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 19 negative
    @DisplayName("Неправильное заполнение формы - CVC/CVV == 000")
    @Test
    void creditNegative000CvcCvv() {
        var cardInfo = DataHelper.getApprovedCardCvcCvvIs000();
        var creditPage = startPage.goToCreditPage();
        creditPage.insertCardData(cardInfo);
        creditPage.checkWrongFormatNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }
}

