package fr.esgi.exception;

/**
 * Exception for technical/infrastructure issues
 */
public class TechnicalException extends Exception {
    public TechnicalException(String message) {
        super(message);
    }
}
