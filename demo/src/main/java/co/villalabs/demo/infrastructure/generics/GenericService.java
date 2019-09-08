package co.villalabs.demo.infrastructure.generics;

import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Generic class to service implementation
 * 
 * @param <T> the element type of this Reactive Streams {@link Publisher}
 * 
 * @author Deusimar Ferreira
 */
public interface GenericService<T> {

    /**
     * 
     * @param entity
     * 
     * @return a {@link Flux} based on the produced combinations
     */
    Flux<T> insert(List<T> entity);

    /**
     * 
     * @param entity
     * @return a {@link Mono} based on the produced combinations
     */
    Mono<T> insert(T entity);

    /**
     * 
     * @param id
     * 
     * @return a {@link Mono} based on the produced combinations
     */
    Mono<T> getById(String id);

    /**
     * Return list of all movies
     *
     * @return a {@link Flux} based on the produced combinations
     */
    Flux<T> getAll();

    /**
     * 
     * @param id
     */
    void delete(Long id);

    /**
     * 
     * @param entity
     */
    void delete(T entity);

    /**
     * 
     * @param entity
     */
    void delete(List<T> entity);

    /**
     * 
     * @param entity
     * @return
     */
    Mono<T> change(T entity);

    /**
     * 
     * @param entity
     * @return
     */
    Flux<T> change(List<T> entity);
}