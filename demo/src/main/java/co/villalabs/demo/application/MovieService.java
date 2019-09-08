package co.villalabs.demo.application;

import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import co.villalabs.demo.domain.entity.Movie;
import co.villalabs.demo.domain.repository.MovieRepository;
import co.villalabs.demo.infrastructure.events.MovieEvent;
import co.villalabs.demo.infrastructure.generics.GenericEvent;
import co.villalabs.demo.infrastructure.generics.GenericService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class MovieService implements GenericService<Movie>, GenericEvent<MovieEvent> {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Flux<MovieEvent> events(String id) {
        log.debug("Stream Events of Movie");

        return Flux.<MovieEvent>generate(movieEventSynchronousSink -> {
            movieEventSynchronousSink.next(new MovieEvent(id, new Date()));
        }).delayElements(Duration.ofSeconds(1));
    }

    @Override
    public Mono<Movie> getById(String id) {
        return this.movieRepository.findById(id);
    }

    @Override
    public Flux<Movie> getAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public Flux<Movie> insert(List<Movie> entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mono<Movie> insert(Movie entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Movie entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(List<Movie> entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public Mono<Movie> change(Movie entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Flux<Movie> change(List<Movie> entity) {
        // TODO Auto-generated method stub
        return null;
    }
}