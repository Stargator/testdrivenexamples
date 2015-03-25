package net.maynard.examples.templateEngine.exceptions;

public class MissingValueException extends RuntimeException {
    private static final long serialVersionUID = 33L;

    public MissingValueException(String variableName) {
        super("No value set for ${" + variableName + "}");
    }
}
