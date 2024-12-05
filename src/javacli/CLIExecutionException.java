package javacli;

public class CLIExecutionException extends Exception {
    public CLIExecutionException(String errorMessage) {
        super(errorMessage);
    }

    public CLIExecutionException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
