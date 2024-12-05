package test;
import java.util.Scanner;
import javacli.*;

public class Program {
    private static CLI cli = new CLI(
        Program.class,
        new ArgumentParser[] {
            new ArgumentParser() {
                public Class parses() {
                    return String.class;
                }

                public Object parse(String arg) {
                    return arg;
                }
            },
            new ArgumentParser() {
                public Class parses() {
                    return Float.class;
                }

                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Float.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as Float", exception);
                    }
                }
            }
        }
    );

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
