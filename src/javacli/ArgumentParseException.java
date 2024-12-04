package javacli;

public class ArgumentParseException extends Exception {
    public ArgumentParseException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}