package co.villalabs.demo.infrastructure.configs;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import co.villalabs.demo.infrastructure.handlers.QuoteHandler;

@Configuration
public class QuoteRouter {

    @Bean
    public RouterFunction<ServerResponse> route(QuoteHandler quoteHandler) {
        return RouterFunctions
            .route(GET("/quotes")
                .and(accept(MediaType.APPLICATION_JSON_UTF8)), quoteHandler::fetchQuotes)
            .andRoute(GET("/quotes")
                .and(accept(MediaType.APPLICATION_STREAM_JSON)), quoteHandler::streamQuotes);
    }
}