package co.villalabs.demo.domain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import co.villalabs.demo.domain.entity.Movie;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {
}