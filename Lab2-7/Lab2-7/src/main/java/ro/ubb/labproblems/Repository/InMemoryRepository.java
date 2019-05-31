package ro.ubb.labproblems.Repository;

import ro.ubb.labproblems.Domain.BaseEntity;
import ro.ubb.labproblems.Domain.Validators.Validator;
import ro.ubb.labproblems.Domain.Validators.ValidatorException;

import java.util.*;

public class InMemoryRepository<ID, T extends BaseEntity<ID>> implements IRepository<ID, T> {

    private Map<ID, T> entities;
    private Validator<T> validator;

    public InMemoryRepository(Validator<T> validator)
    {
        entities = new HashMap<>();
        this.validator = validator;
    }

    @Override
    public Optional<T> returnOne(ID id) {
        if  (id == null)
            throw new IllegalArgumentException("id must not be null!");
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public Iterable<T> returnAll() {
        return new HashSet<>(entities.values());
    }

    @Override
    public Optional<T> addToRepo(T entity) throws ValidatorException {
        if  (entity == null)
            throw new IllegalArgumentException("The entity must not be null!");
        validator.validate(entity);
        return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
    }

    @Override
    public Optional<T> deleteFromRepo(ID id) {
        if  (id == null)
            throw new IllegalArgumentException("The entity must not be null!");
        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public Optional<T> update(T entity) {
        if  (entity == null)
            throw new IllegalArgumentException("The entity must not be null!");
        validator.validate(entity);
        return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v) -> entity));
    }

    public void setEntities(Map<ID, T> entities) {
        this.entities = entities;
    }
}
