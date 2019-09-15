package co.villalabs.demo.application;

import java.time.Duration;

import co.villalabs.demo.domain.entity.Quote;
import reactor.core.publisher.Flux;

public interface QuoteGeneratorService {
    Flux<Quote> fetchQuoteStream(Duration period);
}