package ro.ubb.labproblems.Domain.Validators;

/**
    Validator interface

    @author Catalin.
 */
public interface Validator<T> {
    /**
    Function validate(T entity) validates the entity
     */
    void validate(T entity) throws ValidatorException;
}
