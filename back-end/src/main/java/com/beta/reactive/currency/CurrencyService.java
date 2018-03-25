package com.beta.reactive.currency;

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


    public Flux<Currency> currencies(String queryCurrencies) {

        WebClient webClient = WebClient.builder().baseUrl("https://forex.1forge.com/")
                .build();
        Flux<Currency> currencies = webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/1.0.3/quotes")
                        .queryParam("pairs", queryCurrencies)
                        .queryParam("api_key", "v2oohGWOwF2VIv9Q2GQDxrfh6FZaUzjq")
                        .build(new HashMap<>()))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                .acceptCharset(Charset.forName("UTF-8"))
                .retrieve()
                .bodyToFlux(Currency.class)
                .delaySubscription(Duration.ofSeconds(1))
                .repeat();


        return currencies;
    }

}
