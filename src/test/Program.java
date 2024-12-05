package test;
import java.util.Scanner;
import javacli.*;

public class Program {
    private static CLI cli = new CLI(Program.class, ArgumentParser.parsers());

    @Command(Name="add")
    public static void add(Float a, Float b) {
        System.out.println(a + b);
    }

    @Command(Name="subtract")
    public static void subtract(Float a, Float b) {
        System.out.println(a - b);
    }

    @Command(Name="hello")
    public static void hello(String name) {
        System.out.println("Hello " + name);
    }

    @Command(Name="quit")
    public static void quit() {
        cli.stop();
    }

    @Command(Name="exit")
    public static void exit() {
        cli.stop();
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        cli.run();
    }
}
