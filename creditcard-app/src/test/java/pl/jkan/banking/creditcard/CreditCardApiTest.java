package pl.jkan.banking.creditcard;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.banking.currency.Currency;
import pl.jkan.banking.currency.HardcodedExchangeRates;
import pl.jkan.banking.currency.Rate;
import pl.jkan.banking.currency.RateCantBeRetrieved;

import java.math.BigDecimal;

public class CreditCardApiTest {
    private static final double initialCredit = 200;
    private static final String id = "123456789";
    private CreditCardRepository repository;
    private CreditCardApi api;

    @Test
    public void withdrawFromCard() {
        thereIsCreditCardRepository();
        thereIsCreditCardApi();
        thereIsCardWithId(id);
        
        api.withdraw(id, 20);
        
        balanceOfcardWithIdEquals(id, 180);
    }

    @Test
    public void withdrawForeignCurrency() throws RateCantBeRetrieved {
        thereIsCreditCardRepository();
        thereIsCardWithId(id);
        thereIsCreditCardApi();

        api.withdraw(id, 20, Currency.EURO);

        balanceOfcardWithIdEquals(id, 160.00);
    }

    @Test
    public void withdrawForeignCurrencyFunctionalExchageRates() throws RateCantBeRetrieved {
        thereIsCreditCardRepository();
        thereIsCardWithId(id);

        CreditCardApi api = new CreditCardApi(
                this.repository,
                (Currency x) -> new Rate(x.getCode(), BigDecimal.valueOf(4.00)));

        api.withdraw(id, 20, Currency.EURO);

        balanceOfcardWithIdEquals(id, 120.00);
    }


    private void thereIsCardWithId(String id) {
        CreditCard c = new CreditCard(id);
        c.assignLimit(BigDecimal.valueOf(initialCredit));
        
        repository.add(c);
    }
    
    private void thereIsCreditCardApi() {
        this.api = new CreditCardApi(this.repository, new HardcodedExchangeRates());
    }
    
    private void thereIsCreditCardRepository() {
        this.repository = new CreditCardRepository();
    }
    
    private void balanceOfcardWithIdEquals(String id, double money) {
        CreditCard c = repository.find(id);
        
        Assert.assertTrue(
            BigDecimal.valueOf(money).compareTo(c.getBalance()) == 0
        );
    }

}