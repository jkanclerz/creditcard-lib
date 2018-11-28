package pl.jkan.clientdetails;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ClientDetailsConfiguration {
    @Bean
    ClientDetailsInMemoryRepository inMemoryRepository() {
        return new ClientDetailsInMemoryRepository();
    }
}
