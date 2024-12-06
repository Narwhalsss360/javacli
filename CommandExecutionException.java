package javacli;

public class CommandExecutionException extends Exception {

    public CommandExecutionException(String commandName, Throwable cause) {
        super("An exception occurred executing '" + commandName + "' command.", cause);
    }
}
