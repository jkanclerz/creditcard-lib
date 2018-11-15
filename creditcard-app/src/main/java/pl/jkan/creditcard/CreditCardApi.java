package pl.jkan.creditcard;

import java.math.BigDecimal;

class CreditCardApi {
    private CreditCardRepository cards;
    
    public CreditCardApi(CreditCardRepository cards) {
        this.cards = cards;
    }
    
    public void withdraw(String number, double money) {
        CreditCard c = cards.find(number);
        c.withdraw(BigDecimal.valueOf(money));
    }
}