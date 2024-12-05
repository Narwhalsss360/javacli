package test;

import javacli.*;

public class Program {

    private static final CLI cli = new CLI(Program.class, ArgumentParser.parsers());

    @Command(Name = "add", Description = "Add 2 numbers (a, b) together")
    public static void add(Float a, Float b) {
        System.out.println(a + b);
    }

    @Command(Name = "subtract", Description = "Subtract 2 numbers (b from a)")
    public static void subtract(Float a, Float b) {
        System.out.println(a - b);
    }

    @Command(Name = "hello", Description = "Says hello!")
    public static void hello(String name) {
        System.out.println("Hello " + name);
    }

    @Command(Name = "help", Description = "Displays help of a command or all", Required = 0)
    public static void help(String name) {
        if (name == null) {
            for (CommandMethod command : cli.getCommands()) {
                System.out.println(command.help());
            }
            return;
        }

        CommandMethod command = cli.getCommand(name);
        if (command == null) {
            help(null);
            return;
        }

        System.out.println(command.help());
    }

    @Command(Name = "quit")
    public static void quit() {
        cli.stop();
    }

    @Command(Name = "exit")
    public static void exit() {
        cli.stop();
    }

    public static void main(String[] args) throws Exception {
        cli.execute(args);
    }
}
