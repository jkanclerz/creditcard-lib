package pl.jkan.banking.creditcard;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import pl.jkan.banking.currency.HardcodedExchangeRates;

@Configuration
class CreditCardConfiguration {
    
    @Bean
    public CreditCardApi creditcardApi() {
        CreditCardRepository repo = new CreditCardRepository();
        CreditCard c1 = new CreditCard("1234");
        repo.add(c1);
        
        return new CreditCardApi(repo, new HardcodedExchangeRates());
    }
}