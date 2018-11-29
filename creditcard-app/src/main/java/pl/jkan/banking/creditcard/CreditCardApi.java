package pl.jkan.banking.creditcard;

import pl.jkan.banking.currency.Currency;
import pl.jkan.banking.currency.ExchangeRates;
import pl.jkan.banking.currency.Rate;
import pl.jkan.banking.currency.RateCantBeRetrieved;

import java.math.BigDecimal;

class CreditCardApi {
    private CreditCardRepository cards;
    private ExchangeRates exchangeRates;

    public CreditCardApi(CreditCardRepository cards, ExchangeRates exchangeRates) {
        this.cards = cards;
        this.exchangeRates = exchangeRates;
    }
    
    public void withdraw(String number, double money) {
        CreditCard c = cards.find(number);
        c.withdraw(BigDecimal.valueOf(money));
    }

    public void withdraw(String number, double money, Currency currency) throws RateCantBeRetrieved {
        CreditCard c = cards.find(number);
        Rate rate = exchangeRates.getFor(currency);
        c.withdraw(BigDecimal.valueOf(money).multiply(rate.getExchange()));

    }
}