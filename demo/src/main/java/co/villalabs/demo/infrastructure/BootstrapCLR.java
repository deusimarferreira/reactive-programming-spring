package co.villalabs.demo.infrastructure;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import co.villalabs.demo.domain.entity.Movie;
import co.villalabs.demo.domain.repository.MovieRepository;
import reactor.core.publisher.Flux;

@Component
public class BootstrapCLR implements CommandLineRunner {

    private final MovieRepository movieRepository;

    public BootstrapCLR(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //clear old data
        movieRepository.deleteAll()
                .thenMany(
                Flux.just("Silence of the Lambdas", "AEon Flux", "Enter the Mono<Void>", "The Fluxxinator",
                        "Back to the Future", "Meet the Fluxes", "Lord of the Fluxes")
                        .map(title -> new Movie(title))
                        .flatMap(movieRepository::save))
                        .subscribe(null, null, () -> {
                            movieRepository.findAll().subscribe(System.out::println);
                        });
    }
}