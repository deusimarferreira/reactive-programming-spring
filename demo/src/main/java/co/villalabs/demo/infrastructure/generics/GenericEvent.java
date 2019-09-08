package co.villalabs.demo.infrastructure.generics;

import reactor.core.publisher.Flux;

public interface GenericEvent<E> {

    /**
     * Generate stream of movie events for given movie id
     *
     * @param movieId
     * @return a {@link Flux} based on the produced combinations
     */
    Flux<E> events(String id);

}