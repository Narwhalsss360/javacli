package javacli;

public class InvalidCommandMethodException extends Exception {

    public InvalidCommandMethodException(String errorMessage) {
        super(errorMessage);
    }
}
