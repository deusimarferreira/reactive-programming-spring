package co.villalabs.demo.presentation;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.villalabs.demo.application.MovieService;
import co.villalabs.demo.domain.entity.Movie;
import co.villalabs.demo.infrastructure.events.MovieEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;

@RestController
@RequestMapping("/movies")
public class MovieRestController {
    private final MovieService movieService;

    public MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<MovieEvent> streamMovieEvents(@PathVariable String id){
        return movieService.events(id);
    }

    @GetMapping(value = "/{id}")
    Mono<Movie> getMovieById(@PathVariable String id){
        return movieService.getMovieById(id);
    }

    @GetMapping
    Flux<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }
}