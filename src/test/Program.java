package test;
import javacli.*;

public class Program {
    @Command(Name="add", Required=1)
    public static void add(int a, Float b) {
        if (b == null) {
            b = 0f;
        }
        System.out.println(a + b);
    }

    @Command(Name="hello")
    public static void hello(String name) {
        System.out.println("Hello " + name);
    }

    public static void main(String[] args) throws Exception {
        CommandMethod addCommand = new CommandMethod(
            Program.class.getMethod("add", new Class[] { Class.forPrimitiveName("int"), Float.class })
        );
        CommandMethod helloCommand = new CommandMethod(
            Program.class.getMethod("hello", new Class[] { String.class })
        );

        String[] splitTests = new String[] {
            "command a b c",
            "command abc def ghi",
            "command 1 2 3",
            "command 123 456 789",
            "command \"1 2\" 3",
            "command 1 \"2 3\"",
            "\"my command\" 1 \"2 3\""
        };

        for (String test : splitTests) {
            String[] split = ArgumentParser.split(test);
            System.out.print(test);
            System.out.println(':');
            for (String arg : split) {
                System.out.println(arg);
            }
            System.out.println();
        }

        addCommand.execute(
            ArgumentParser.split("1 2"),
            new ArgumentParser[] {
                new ArgumentParser() {
                    public Class parses() {
                        return Float.class;
                    }

                    public Object parse(String arg) throws ArgumentParseException {
                        try {
                            return Float.valueOf(arg);
                        } catch (Exception exception) {
                            throw new ArgumentParseException("Error parsing " + arg + " as Float", exception);
                        }
                    }
                },
                new ArgumentParser() {
                    public Class parses() {
                        return Class.forPrimitiveName("int");
                    }

                    public Object parse(String arg) throws ArgumentParseException {
                        try {
                            return Integer.valueOf(arg);
                        } catch (Exception exception) {
                            throw new ArgumentParseException("Error parsing " + arg + " as int", exception);
                        }
                    }
                }
            }
        );

        helloCommand.execute(
            ArgumentParser.split("\"First Last\""),
            new ArgumentParser[] {
                new ArgumentParser() {
                    public Class parses() {
                        return String.class;
                    }

                    public Object parse(String arg) {
                        return arg;
                    }
                }
            }
        );
    }
}
