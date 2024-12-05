package javacli;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.InputStream;
import java.io.PrintStream;

public class CLI {

    private CommandMethod[] commands = {};

    private ArgumentParser[] parsers = {};

    private boolean running = false;

    public CLI(Class[] classes, ArgumentParser[] parsers) {
        ArrayList<CommandMethod> commandsList = new ArrayList<CommandMethod>();
        for (Class cls : classes) {
            for (Method method : cls.getMethods()) {
                try {
                    commandsList.add(new CommandMethod(method));
                } catch (InvalidCommandMethodException exception) {
                    continue;
                }
            }
        }
        this.parsers = parsers;
        commands = new CommandMethod[commandsList.size()];
        commands = commandsList.toArray(commands);
    }

    public CLI(Class cls, ArgumentParser[] parsers) {
        this(new Class[] { cls }, parsers);
    }

    public CommandMethod[] getCommands() {
        return commands;
    }

    public CommandMethod getCommand(String name) {
        for (CommandMethod command : commands) {
            if (command.info.Name().equals(name)) {
                return command;
            }
        }
        return null;
    }

    public void execute(String[] entries) throws CLIExecutionException {
        if (entries.length == 0) {
            throw new CLIExecutionException("Nothing was entered");
        }

        String name = entries[0];
        CommandMethod command = getCommand(name);
        if (command == null) {
            throw new CLIExecutionException("Command '" + name + "' not found.");
        }

        try {
            command.execute(Arrays.copyOfRange(entries, 1, entries.length), parsers);
        } catch (CommandExecutionException exception) {
            throw new CLIExecutionException(
                "An exception was thrown executing " + name + ".",
                exception
            );
        }
    }

    public void execute(String line) throws CLIExecutionException {
        execute(ArgumentParser.split(line));
    }

    private static String extractCauses(Throwable throwable) {
        StringBuilder builder = new StringBuilder();
        Throwable current = throwable;
        while (true) {
            builder.append(current.toString());
            current = current.getCause();
            if (current == null) {
                break;
            } else {
                builder.append('\n');
            }
        }
        return builder.toString();
    }

    public void run(InputStream in, PrintStream out, PrintStream err) {
        running = true;
        while (running) {
            try {
                out.print('>');
                execute(new Scanner(in).nextLine());
            } catch (Exception exception) {
                err.println(extractCauses(exception));
            }
        }
    }

    public void run() {
        run(System.in, System.out, System.err);
    }

    public void stop() {
        running = false;
    }
}
