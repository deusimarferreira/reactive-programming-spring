package co.villalabs.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import co.villalabs.demo.domain.entity.Quote;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTest {

    @Autowired
    private WebTestClient webTestClient;
	@Test
	public void testFetchQuotes() {
        webTestClient
            .get()
            .uri("/quotes?size=20")
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
            .expectBodyList(Quote.class)
            .hasSize(20)
            .consumeWith(allQuotes -> {
                assertThat(allQuotes.getResponseBody())
                    .allSatisfy(quote -> assertThat(quote.getPrice()).isPositive());

                assertThat(allQuotes.getResponseBody()).hasSize(20);
            });
    }
    
    @Test
    public void testStreamQuotes() throws InterruptedException {
        //set Countdown latch to 10
        CountDownLatch countDownLatch = new CountDownLatch(10);

        webTestClient
                .get()
                .uri("/quotes")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                .returnResult(Quote.class)
                .getResponseBody()
                .take(10)
                .subscribe(quote -> {
                    assertThat(quote.getPrice()).isPositive();

                    countDownLatch.countDown();
                });

        countDownLatch.await();

        System.out.println("Test Complete");

    }

}
