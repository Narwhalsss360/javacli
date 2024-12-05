package javacli;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.InvocationTargetException;

public class CommandMethod {
    public final Command info;

    public final Method method;

    private final Parameter[] parameters;

    public static boolean isAnnotated(Method method) {
        return method.getAnnotation(Command.class) != null;
    }

    public static boolean isValidCommandMethod(Method method) {
        int modifiers = method.getModifiers();

        if (!isAnnotated(method)) {
            return false;
        }

        if (!Modifier.isStatic(modifiers)) {
            return false;
        }

        if (!Modifier.isPublic(modifiers)) {
            return false;
        }

        return true;
    }

    public CommandMethod(Method method) throws InvalidCommandMethodException {
        if (!isValidCommandMethod(method)) {
            throw new InvalidCommandMethodException("Invalid signature");
        }
        this.method = method;
        info = method.getAnnotation(Command.class);
        parameters = method.getParameters();
    }

    public String help() {
        StringBuilder builder = new StringBuilder(info.Name());

        if (!info.Description().equals("")) {
            builder.append('\t');
            builder.append(info.Description());
        }

        if (parameters.length == 0) {
            return builder.toString();
        }

        builder.append(": ");
        int required = info.Required() == -1 ? parameters.length : info.Required();
        for (int i = 0; i < parameters.length; i++) {
            builder.append('<');
            if (parameters[i].isNamePresent()) {
                builder.append(parameters[i].getName());
            }
            if (required <= i) {
                builder.append('?');
            }
            builder.append(':');
            builder.append(parameters[i].getType().getSimpleName());
            builder.append('>');

            if (i != parameters.length - 1) {
                builder.append(' ');
            }
        }

        return builder.toString();
    }

    public void execute(String[] entries, ArgumentParser[] parsers) throws CommandExecutionException {
        Object[] args = new Object[parameters.length];
        int required = info.Required();
        if (required == -1) {
            required = parameters.length;
        }

        if (entries.length < required || entries.length > parameters.length) {
            throw new CommandExecutionException(
                info.Name(),
                new IllegalArgumentException("Expected " + required + " to " + parameters.length + " arguments.")
            );
        }

        for (int i = 0; i < entries.length; i++) {
            try {
                args[i] = ArgumentParser.parseUsing(parameters[i].getType(), parsers, entries[i]);
            } catch (ArgumentParseException exception) {
                throw new CommandExecutionException(
                    info.Name(),
                    exception
                );
            }
        }

        try {
            method.invoke(null, (Object[])args);
        } catch (IllegalAccessException exception) {
            throw new CommandExecutionException(
                info.Name(),
                exception
            );
        } catch (InvocationTargetException exception) {
            throw new CommandExecutionException(
                info.Name(),
                exception
            );
        } catch (IllegalArgumentException exception) {
            throw new CommandExecutionException(
                info.Name(),
                exception
            );
        }
    }
}
