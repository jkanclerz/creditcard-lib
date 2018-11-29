package pl.jkan.banking.currency;

@FunctionalInterface
public interface ExchangeRates {
    Rate getFor(Currency currency) throws RateCantBeRetrieved;
}
