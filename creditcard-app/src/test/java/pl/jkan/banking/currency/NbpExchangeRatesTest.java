package pl.jkan.banking.currency;

import org.junit.Assert;
import org.junit.Test;

public class NbpExchangeRatesTest {

    @Test
    public void verifyEnum() {
        Assert.assertTrue(Currency.EURO.getCode().equals("EUR"));
    }
    @Test
    public void itQueryApiForRate() throws RateCantBeRetrieved {
        NbpExchangeRates nbp = new NbpExchangeRates();
        Rate euroRate = nbp.getFor(Currency.EURO);

        Assert.assertTrue(euroRate instanceof Rate);
        Assert.assertTrue(euroRate.getCode().equals(Currency.EURO.getCode()));
    }
}
