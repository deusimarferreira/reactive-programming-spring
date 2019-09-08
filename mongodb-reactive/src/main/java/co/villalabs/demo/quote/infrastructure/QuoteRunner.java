package co.villalabs.demo.quote.infrastructure;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import co.villalabs.demo.quote.domain.entity.Quote;
import co.villalabs.demo.quote.domain.repository.QuoteRepository;
import co.villalabs.demo.quote.presentation.StockQuoteClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

@Component
public class QuoteRunner implements CommandLineRunner {

    private final StockQuoteClient stockQuoteClient;
    private final QuoteRepository repository;

    public QuoteRunner(StockQuoteClient stockQuoteClient, QuoteRepository repository) {
        this.stockQuoteClient = stockQuoteClient;
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        //Flux<Quote> quoteFlux = stockQuoteClient.getQuoteStream();
        //quoteFlux.subscribe(System.out::println);

        Flux<Quote> quoteFlux = repository.findWithTailableCursorBy();

        Disposable disposable = quoteFlux.subscribe(quote -> {
            System.out.println("*#*#*#*#*#*#*#*#*#*#*#*#* Id: " + quote.getId());
        });

        disposable.dispose();
    }
}