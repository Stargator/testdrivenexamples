package net.maynard.examples.templateEngine.exceptions;

/**
 *
 * @author root
 */
public class MissingValueException extends RuntimeException {
    private static final long serialVersionUID = 33L;

    public MissingValueException(String message) {
        super(message);
    }
    
}
