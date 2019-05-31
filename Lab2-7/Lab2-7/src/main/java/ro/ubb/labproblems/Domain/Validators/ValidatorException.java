package ro.ubb.labproblems.Domain.Validators;

/**
class ValidatorException is a custom exception class that extends RuntimeException
 @author Catalin.
 */
public class ValidatorException extends RuntimeException {
    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorException(Throwable cause) {
        super(cause);
    }
}
