package ro.ubb.labproblems.Domain.Validators;

/**
 * @author andrei
 * Exception for when the argument of a method does not correspond to the expectations
 */

public class IllegalArgumentException extends RuntimeException {

    public IllegalArgumentException(String message) {
        super(message);
    }

    public IllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalArgumentException(Throwable cause) {
        super(cause);
    }
}
