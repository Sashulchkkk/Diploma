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

    public static CardInfo getApprovedCard() {
        String cardNumber = "4444 4444 4444 4441";
        String holder = "IVANOV IVAN";
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "456";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardTwoDigitCvcCvv() {
        String cardNumber = "4444 4444 4444 4441";
        String holder = "IVANOV IVAN";
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "00";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardCvcCvvIs000() {
        String cardNumber = "4444 4444 4444 4441";
        String holder = "IVANOV IVAN";
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "000";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getDeclinedCard() {
        String cardNumber = "4444 4444 4444 4442";
        String holder = "IVANOV IVAN";
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "456";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardWithNoCvcCvv() {
        String cardNumber = "4444 4444 4444 4441";
        String holder = "IVANOV IVAN";
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardWithCardHolderInCyrillic() {
        String cardNumber = "4444 4444 4444 4441";
        String holder = "Иван Иванов";
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "456";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getCardWithBadCharactersInCardHolder() {
        String cardNumber = "4444 4444 4444 4441";
        String holder = "?:%; ?:%;№\"";
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "456";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardWithNoCardHolder() {
        String cardNumber = "4444 4444 4444 4441";
        String holder = "";
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "456";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardWithNoYear() {
        String cardNumber = "4444 4444 4444 4441";
        String holder = "IVANOV IVAN";
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = "";
        String cvv = "456";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardWithNoMonth() {
        String cardNumber = "4444 4444 4444 4441";
        String holder = "IVANOV IVAN";
        String month = "";
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "456";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardMonthIs3Digits() {
        String cardNumber = "4444 4444 4444 4441";
        String holder = "IVANOV IVAN";
        String month = "111";
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "456";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardMonthIs00() {
        String cardNumber = "4444 4444 4444 4441";
        String holder = "IVANOV IVAN";
        String month = "00";
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "456";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardMonthIs13() {
        String cardNumber = "4444 4444 4444 4441";
        String holder = "IVANOV IVAN";
        String month = "13";
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "456";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getCardWith15DigitCardNumber() {
        String cardNumber = "4444 4444 4444 444";
        String holder = "IVANOV IVAN";
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "456";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getCardWith17DigitCardNumber() {
        String cardNumber = "4444 4444 4444 44444";
        String holder = "IVANOV IVAN";
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "456";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getCardWithBadCharactersInCardNumber() {
        String cardNumber = "4444 aaaa &&&& фффф";
        String holder = "IVANOV IVAN";
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "456";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getCardWithoutCardNumber() {
        String cardNumber = "";
        String holder = "IVANOV IVAN";
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "456";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getApprovedCardDoubleSurname() {
        String cardNumber = "4444 4444 4444 4441";
        String holder = "Ivan Ivanov-Petrov";
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "456";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }
    
    public static CardInfo getApprovedCardWithoutSpaces() {
        String cardNumber = "4444444444444441";
        String holder = "IVANOV IVAN";
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "456";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getExpiredCard1Month() {
        String cardNumber = "4444 4444 4444 4441";
        String holder = "IVANOV IVAN";
        var date = LocalDate.now().minusMonths(1);
        String month = date.format(DateTimeFormatter.ofPattern("MM"));
        String year = date.format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "456";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getExpiredCard1Year() {
        String cardNumber = "4444 4444 4444 4441";
        String holder = "IVANOV IVAN";
        var date = LocalDate.now().minusYears(1);
        String month = date.format(DateTimeFormatter.ofPattern("MM"));
        String year = date.format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "456";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }

    public static CardInfo getFutureCard() {
        String cardNumber = "4444 4444 4444 4441";
        String holder = "IVANOV IVAN";
        var date = LocalDate.now().plusYears(6);
        String month = date.format(DateTimeFormatter.ofPattern("MM"));
        String year = date.format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "456";
        return new CardInfo(cardNumber, month, year, holder, cvv);
    }
}