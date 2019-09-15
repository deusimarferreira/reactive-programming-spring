package co.villalabs.demo.service;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import co.villalabs.demo.client.StockQuoteClient;
import co.villalabs.demo.repositories.QuoteRepository;
import co.villalabs.demo.domain.Quote;
import reactor.core.publisher.Mono;

/**
 * Created by jt on 10/19/17.
 */
@Service
public class QuoteMonitorService implements ApplicationListener<ContextRefreshedEvent> {

    private final StockQuoteClient stockQuoteClient;
    private final QuoteRepository quoteRepository;

    public QuoteMonitorService(StockQuoteClient stockQuoteClient, QuoteRepository quoteRepository) {
        this.stockQuoteClient = stockQuoteClient;
        this.quoteRepository = quoteRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        stockQuoteClient.getQuoteStream()
                .log("quote-monitor-service")
                .subscribe(quote -> {
                    Mono<Quote> savedQuote = quoteRepository.save(quote);

                    System.out.println("I saved a quote! Id: " +savedQuote.block().getId());
                });
    }
}
