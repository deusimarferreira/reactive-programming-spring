package co.villalabs.demo.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import co.villalabs.demo.domain.Quote;


/**
 * Created by jt on 10/19/17.
 */
public interface QuoteRepository extends ReactiveMongoRepository<Quote, String> {
}
