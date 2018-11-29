package pl.jkan.banking.currency;

import java.math.BigDecimal;

public class HardcodedExchangeRates implements ExchangeRates {
    @Override
    public Rate getFor(Currency currency) throws RateCantBeRetrieved {
        switch (currency) {
            case EURO:
                return new Rate(currency.getCode(), BigDecimal.valueOf(2.00));
            case USD:
                return new Rate(currency.getCode(), BigDecimal.valueOf(1.50));
            default:
                throw new RateCantBeRetrieved();
        }
    }
}
