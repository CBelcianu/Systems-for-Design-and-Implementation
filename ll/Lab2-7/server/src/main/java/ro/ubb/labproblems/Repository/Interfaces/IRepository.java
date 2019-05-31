package ro.ubb.labproblems.Repository.Interfaces;

import ro.ubb.labproblems.Domain.BaseEntity;
import ro.ubb.labproblems.Domain.Validators.ValidatorException;

import java.util.Optional;

/**
 * Interface for CRUD operations on a repository
 *
 * @author andrei.
 *
 */

public interface IRepository<ID, T extends BaseEntity<ID>> {
    /**
     * Returns one entity based on {@code id}
     *
     * @param id must not be null.
     *
     * @return an {@code Optional}  encapsulating the entity with the given id.
     * @throws IllegalArgumentException if the given id is null.
     */
    Optional<T> returnOne(ID id);


    /**
     *  Returns all entities from the repository.
     * @return all entities
     */
    Iterable<T> returnAll();

    /**
     * Adds an entity to the repository
     * @param entity must not be null.
     * @return an {@code Optional} null if the entity was saved otherwise returns the entity.
     * @throws IllegalArgumentException if the given entity is null.
     * @throws ValidatorException if the entity is not valid.
     */
    Optional<T> addToRepo(T entity);

    /**
     * Removes the entity with the given id from the repository
     * @param id must not be null.
     * @return an {@code Optional} null if there is no entity with the given ID, otherwise the removed entity.
     * @throws IllegalArgumentException if the given id is null.
     */
    Optional<T> deleteFromRepo(ID id);

    /**
     * Updates the given entity
     * @param entity must not be null.
     * @return an {@code Optional} null if the entity was updated, otherwise returns the entity.
     * @throws IllegalArgumentException if the given entity is null.
     */
    Optional<T> update(T entity);
}
