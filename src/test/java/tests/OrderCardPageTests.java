package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pages.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;


public class OrderCardPageTests {

    StartPage startPage = open("http://localhost:8080/", StartPage.class);

    @BeforeAll
    static void setUpAll() {
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
    @DisplayName("Успешная покупка по карте, со статусом APPROVED. номер карты с пробелами")
    @Test
    void orderPositiveAllFieldsValidWithSpacesApproved() {
        var cardInfo = DataHelper.getApprovedCard();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        orderPage.checkNotification("Операция одобрена Банком.");
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    // 2 positive
    @DisplayName("Успешная покупка по карте, со статусом APPROVED. номер карты без пробелов")
    @Test
    void orderPositiveAllFieldsValidWithoutSpacesApproved() {
        var cardInfo = DataHelper.getApprovedCardWithoutSpaces();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        orderPage.checkNotification("Операция одобрена Банком.");
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    // 3 positive
    @DisplayName("Успешная покупка по карте, со статусом APPROVED. двойная фамилия")
    @Test
    void orderPositiveAllFieldsValidDoubleSurnameApproved() {
        var cardInfo = DataHelper.getApprovedCardDoubleSurname();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        orderPage.checkNotification("Операция одобрена Банком.");
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    // 1 negative
    @DisplayName("Неправильное заполнение формы - номер карты не заполнен")
    @Test
    void orderNegativeCardNumberIsEmpty() {
        var cardInfo = DataHelper.getCardWithoutCardNumber();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        orderPage.checkErrorMessage("Поле обязательно для заполнения");
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 2 negative
    @DisplayName("Неправильное заполнение формы - номер карты из 15ти цифр")
    @Test
    void orderNegativeCardNumberIs15Digits() {
        var cardInfo = DataHelper.getCardWith15DigitCardNumber();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        orderPage.checkErrorMessage("Неверный формат");
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 3 negative
    @DisplayName("Неправильное заполнение формы - номер карты из 17ти цифр")
    @Test
    void orderNegativeCardNumberIs17Digits() {
        var cardInfo = DataHelper.getCardWith17DigitCardNumber();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        assertEquals("4444 4444 4444 4444", orderPage.getCardNumber());
    }

    // 4 negative
    @DisplayName("Неправильное заполнение формы - номер карты содержит запрещённые символы")
    @Test
    void orderNegativeBadCharactersInCardNumber() {
        var cardInfo = DataHelper.getCardWithBadCharactersInCardNumber();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        orderPage.checkErrorMessage("Неверный формат");
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 5 negative
    @DisplayName("Неправильное заполнение формы - месяц не заполнен")
    @Test
    void orderNegativeMonthIsEmpty() {
        var cardInfo = DataHelper.getApprovedCardWithNoMonth();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        orderPage.checkErrorMessage("Поле обязательно для заполнения");
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 6 negative
    @DisplayName("Невалидный период действия карты: месяц предшествующий текущему")
    @Test
    void orderNegativePastMonth() {
        var cardInfo = DataHelper.getExpiredCard1Month();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        orderPage.checkErrorMessage("Истёк срок действия карты");
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 7 negative
    @DisplayName("Неправильное заполнение формы - месяц из трёх цифр")
    @Test
    void orderNegativeMonthIs3Digits() {
        var cardInfo = DataHelper.getApprovedCardMonthIs3Digits();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        assertEquals("11", orderPage.getMonth());
    }

    // 8 negative
    @DisplayName("Невалидный период действия карты: месяц = 00")
    @Test
    void orderNegativeMonthIs00() {
        var cardInfo = DataHelper.getApprovedCardMonthIs00();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        orderPage.checkErrorMessage("Неверно указан срок действия карты");
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 9 negative
    @DisplayName("Невалидный период действия карты: месяц = 13")
    @Test
    void orderNegativeMonthIs13() {
        var cardInfo = DataHelper.getApprovedCardMonthIs13();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        orderPage.checkErrorMessage("Неверно указан срок действия карты");
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 10 negative
    @DisplayName("Неправильное заполнение формы - год не заполнен")
    @Test
    void orderNegativeYearIsEmpty() {
        var cardInfo = DataHelper.getApprovedCardWithNoYear();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        orderPage.checkErrorMessage("Поле обязательно для заполнения");
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 11 negative
    @DisplayName("Невалидный период действия карты: год предшествующий текущему")
    @Test
    void orderNegativePastYear() {
        var cardInfo = DataHelper.getExpiredCard1Year();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        orderPage.checkErrorMessage("Истёк срок действия карты");
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 12 negative
    @DisplayName("Неправильное заполнение формы - владелец не заполнен")
    @Test
    void orderNegativeCardHolderIsEmpty() {
        var cardInfo = DataHelper.getApprovedCardWithNoCardHolder();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        orderPage.checkErrorMessage("Поле обязательно для заполнения");
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 13 negative
    @DisplayName("Неправильное заполнение формы - имя владельца на кириллице")
    @Test
    void orderNegativeCardHolderIsInCyrillic() {
        var cardInfo = DataHelper.getApprovedCardWithCardHolderInCyrillic();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        orderPage.checkErrorMessage("Неверный формат");
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 14 negative
    @DisplayName("Неправильное заполнение формы - имя владельца содержит запрещённые символы")
    @Test
    void orderNegativeBadCharactersInCardHolder() {
        var cardInfo = DataHelper.getCardWithBadCharactersInCardHolder();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        orderPage.checkErrorMessage("Неверный формат");
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 15 negative
    @DisplayName("Неправильное заполнение формы - CVC/CVV не заполнен")
    @Test
    void orderNegativeCvcCvvIsEmpty() {
        var cardInfo = DataHelper.getApprovedCardWithNoCvcCvv();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        orderPage.checkErrorMessage("Поле обязательно для заполнения");
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 16 negative
    @DisplayName("Отказ в покупке по карте со статусом DECLINED")
    @Test
    void orderNegativeAllFieldValidDeclined() {
        var cardInfo = DataHelper.getDeclinedCard();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        orderPage.checkNotification("Ошибка! Банк отказал в проведении операции.");
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }

    // 17 negative
    @DisplayName("Невалидный период действия карты: год слишком большой")
    @Test
    void orderNegativeTooFarFutureDate() {
        var cardInfo = DataHelper.getFutureCard();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        orderPage.checkErrorMessage("Неверно указан срок действия карты");
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 18 negative
    @DisplayName("Неправильное заполнение формы - CVC/CVV из двух цифр")
    @Test
    void orderNegativeTwoDigitCvcCvv() {
        var cardInfo = DataHelper.getApprovedCardTwoDigitCvcCvv();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        orderPage.checkErrorMessage("Неверный формат");
        assertEquals("0", SQLHelper.getOrderCount());
    }

    // 19 negative
    @DisplayName("Неправильное заполнение формы - CVC/CVV == 000")
    @Test
    void orderNegative000CvcCvv() {
        var cardInfo = DataHelper.getApprovedCardCvcCvvIs000();
        var orderPage = startPage.goToOrderCardPage();
        orderPage.insertCardData(cardInfo);
        orderPage.checkErrorMessage("Неверный формат");
        assertEquals("0", SQLHelper.getOrderCount());
    }
}

