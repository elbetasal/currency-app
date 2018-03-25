package com.beta.reactive.currency;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties
public class CurrencyConfiguration {


    @Bean
    public WebClient currencyWebClient(@Value("${com.beta.currency.forgex.baseUrl}") String baseUrl){
        return WebClient
                .builder()
                .baseUrl(baseUrl)
                .build();

    }

}
