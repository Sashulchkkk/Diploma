package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String cardHolder;
        String cvc;
    }
    
    private static String getRandomCardHolder() {
        Faker faker = new Faker();
        return faker.name().firstName() + " " + faker.name().lastName();
    }
    
    private static String getRandomCvcCvv() {
        Faker faker = new Faker();
        String cvv;
        do {
            cvv = faker.number().digits(3);
        } while (cvv == "000");
        return cvv;
    }
    
    public static String getShiftedMonth(int shift) {
        return LocalDate.now().plusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getShiftedYear(int shift) {
        return LocalDate.now().plusYears(shift).format(DateTimeFormatter.ofPattern("yy"));
    }
    
    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }
    
    public static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static CardInfo getApprovedCard() {
        String cardNumber = getApprovedCardNumber();
        String holder = getRandomCardHolder();
        String month = getShiftedMonth(0);
        String year = getShiftedYear(0);
        String cvv = getRandomCvcCvv();
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardTwoDigitCvcCvv() {
        String cardNumber = getApprovedCardNumber();
        String holder = getRandomCardHolder();
        String month = getShiftedMonth(0);
        String year = getShiftedYear(0);
        String cvv = "00";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardCvcCvvIs000() {
        String cardNumber = getApprovedCardNumber();
        String holder = getRandomCardHolder();
        String month = getShiftedMonth(0);
        String year = getShiftedYear(0);
        String cvv = "000";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getDeclinedCard() {
        String cardNumber = getDeclinedCardNumber();
        String holder = getRandomCardHolder();
        String month = getShiftedMonth(0);
        String year = getShiftedYear(0);
        String cvv = getRandomCvcCvv();
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardWithNoCvcCvv() {
        String cardNumber = getApprovedCardNumber();
        String holder = getRandomCardHolder();
        String month = getShiftedMonth(0);
        String year = getShiftedYear(0);
        String cvv = "";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardWithCardHolderInCyrillic() {
        String cardNumber = getApprovedCardNumber();
        String holder = "Иван Иванов";
        String month = getShiftedMonth(0);
        String year = getShiftedYear(0);
        String cvv = getRandomCvcCvv();
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getCardWithBadCharactersInCardHolder() {
        String cardNumber = getApprovedCardNumber();
        String holder = "?:%; ?:%;№\"";
        String month = getShiftedMonth(0);
        String year = getShiftedYear(0);
        String cvv = getRandomCvcCvv();
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardWithNoCardHolder() {
        String cardNumber = getApprovedCardNumber();
        String holder = "";
        String month = getShiftedMonth(0);
        String year = getShiftedYear(0);
        String cvv = getRandomCvcCvv();
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardWithNoYear() {
        String cardNumber = getApprovedCardNumber();
        String holder = getRandomCardHolder();
        String month = getShiftedMonth(0);
        String year = "";
        String cvv = getRandomCvcCvv();
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardWithNoMonth() {
        String cardNumber = getApprovedCardNumber();
        String holder = getRandomCardHolder();
        String month = "";
        String year = getShiftedYear(0);
        String cvv = getRandomCvcCvv();
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardMonthIs3Digits() {
        String cardNumber = getApprovedCardNumber();
        String holder = getRandomCardHolder();
        String month = "111";
        String year = getShiftedYear(0);
        String cvv = getRandomCvcCvv();
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardMonthIs00() {
        String cardNumber = getApprovedCardNumber();
        String holder = getRandomCardHolder();
        String month = "00";
        String year = getShiftedYear(0);
        String cvv = getRandomCvcCvv();
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardMonthIs13() {
        String cardNumber = getApprovedCardNumber();
        String holder = getRandomCardHolder();
        String month = "13";
        String year = getShiftedYear(0);
        String cvv = getRandomCvcCvv();
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getCardWith15DigitCardNumber() {
        String cardNumber = "4444 4444 4444 444";
        String holder = getRandomCardHolder();
        String month = getShiftedMonth(0);
        String year = getShiftedYear(0);
        String cvv = getRandomCvcCvv();
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getCardWith17DigitCardNumber() {
        String cardNumber = "4444 4444 4444 44444";
        String holder = getRandomCardHolder();
        String month = getShiftedMonth(0);
        String year = getShiftedYear(0);
        String cvv = getRandomCvcCvv();
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getCardWithBadCharactersInCardNumber() {
        String cardNumber = "4444 aaaa &&&& фффф";
        String holder = getRandomCardHolder();
        String month = getShiftedMonth(0);
        String year = getShiftedYear(0);
        String cvv = getRandomCvcCvv();
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getCardWithoutCardNumber() {
        String cardNumber = "";
        String holder = getRandomCardHolder();
        String month = getShiftedMonth(0);
        String year = getShiftedYear(0);
        String cvv = getRandomCvcCvv();
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardDoubleSurname() {
        String cardNumber = getApprovedCardNumber();
        String holder = "Ivan Ivanov-Petrov";
        String month = getShiftedMonth(0);
        String year = getShiftedYear(0);
        String cvv = getRandomCvcCvv();
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }
    
    public static CardInfo getApprovedCardWithoutSpaces() {
        String cardNumber = "4444444444444441";
        String holder = getRandomCardHolder();
        String month = getShiftedMonth(0);
        String year = getShiftedYear(0);
        String cvv = getRandomCvcCvv();
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getExpiredCard1Month() {
        String cardNumber = getApprovedCardNumber();
        String holder = getRandomCardHolder();
        var date = LocalDate.now().minusMonths(1);
        String month = date.format(DateTimeFormatter.ofPattern("MM"));
        String year = date.format(DateTimeFormatter.ofPattern("yy"));
        String cvv = getRandomCvcCvv();
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getExpiredCard1Year() {
        String cardNumber = getApprovedCardNumber();
        String holder = getRandomCardHolder();
        String month = getShiftedMonth(0);
        String year = getShiftedYear(-1);
        String cvv = getRandomCvcCvv();
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getFutureCard() {
        String cardNumber = getApprovedCardNumber();
        String holder = getRandomCardHolder();
        String month = getShiftedMonth(0);
        String year = getShiftedYear(6);
        String cvv = getRandomCvcCvv();
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }
}