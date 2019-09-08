package co.villalabs.demo.infrastructure.handlers;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import co.villalabs.demo.application.QuoteGeneratorService;
import co.villalabs.demo.domain.entity.Quote;
import reactor.core.publisher.Mono;

@Component
public class QuoteHandler {
    
    private final QuoteGeneratorService quoteGeneratorService;

    public QuoteHandler(QuoteGeneratorService quoteGeneratorService) {
        this.quoteGeneratorService = quoteGeneratorService;
    }

    public Mono<ServerResponse> fetchQuotes(ServerRequest request) {
        int size = Integer.parseInt(request.queryParam("size").orElse("10"));

        return ok()
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .body(this.quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100))
            .take(size), Quote.class);
    }

    public Mono<ServerResponse> streamQuotes(ServerRequest serverRequest) {
        return ok()
            .contentType(MediaType.APPLICATION_STREAM_JSON)
            .body(this.quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(200)), Quote.class);
    }
}