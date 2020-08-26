package com.beta.reactive.currency;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.nio.charset.Charset;
import java.time.Duration;
import java.util.HashMap;

@Service
public class CurrencyService {

    private WebClient currencyWebClient;
    

    private String apiKey;

    public CurrencyService(WebClient currencyWebClient,
                           @Value("${com.beta.currency.forgex.apiKey}") String apiKey) {
        this.currencyWebClient = currencyWebClient;
        this.apiKey = apiKey;
    }

    public Flux<Currency> currencies(String queryCurrencies) {

        return currencyWebClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/1.0.3/quotes")
                        .queryParam("pairs", queryCurrencies)
                        .queryParam("api_key", apiKey)
                        .build(new HashMap<>()))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                .acceptCharset(Charset.forName("UTF-8"))
                .retrieve()
                .bodyToFlux(Currency.class)
                .delaySubscription(Duration.ofSeconds(1))
                .repeat();
    }

}
