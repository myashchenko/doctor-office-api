package ua.kpi.exception;

/**
 * @author Mykola Yashchenko
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message, String... values) {
        super(String.format(message, values));
    }
}
