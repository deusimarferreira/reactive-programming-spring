package co.villalabs.demo;

import org.junit.Before;
import org.junit.Test;

import co.villalabs.demo.application.QuoteGeneratorServiceImpl;
import co.villalabs.demo.domain.entity.Quote;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

public class QuoteGeneratorServiceImplTest {

    QuoteGeneratorServiceImpl quoteGeneratorService = new QuoteGeneratorServiceImpl();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void fetchQuoteStream() throws Exception {

        //get quoteFlux of quotes
        Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(1L));

        quoteFlux.take(22000)
            .subscribe(System.out::println);

    }

    @Test
    public void fetchQuoteStreamCountDown() throws Exception {

        //get quoteFlux of quotes
        Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100L));

        //subscriber lambda
        Consumer<Quote> println = System.out::println;

        //error handler
        Consumer<Throwable> errorHandler = e -> System.out.println("Some Error Occurred");

        //set Countdown latch to 1
        CountDownLatch countDownLatch = new CountDownLatch(1);

        //runnable called upon complete, count down latch
        Runnable allDone = () -> countDownLatch.countDown();

        quoteFlux.take(30)
            .subscribe(println, errorHandler, allDone);

        countDownLatch.await();
    }

    @Test
    public void fetchQuoteStreamCountDownLambda() throws Exception {

        //get quoteFlux of quotes
        Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100L));

        //set Countdown latch to 1
        CountDownLatch countDownLatch = new CountDownLatch(1);

        quoteFlux.take(30)
            .subscribe(
                System.out::println, 
                e -> System.out.println("Some Error Occurred"),
                () -> countDownLatch.countDown());

        countDownLatch.await();
    }

}