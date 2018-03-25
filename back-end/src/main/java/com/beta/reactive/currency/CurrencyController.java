package com.beta.reactive.currency;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;


@RestController
@Configuration
public class CurrencyController {

    private CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @Bean
    public RouterFunction<?> routerFunctions(CurrencyService currencyService) {
        return nest(path("/currency")
                , route(RequestPredicates.GET("/stream"),
                        serverRequest ->
                                ok().contentType(MediaType.TEXT_EVENT_STREAM)
                                        .body(currencyService
                                                        .currencies(serverRequest.queryParam("currencies").get()),
                                                Currency.class)));
    }

}
