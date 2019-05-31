package ro.ubb.labproblems.client.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import ro.ubb.labproblems.client.UI.Console;

@Configuration
public class ClientConfig {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    Console console(){ return new Console(); }
}

