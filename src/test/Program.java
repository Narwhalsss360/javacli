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

        addCommand.execute(
            new String[] { "1", "2" },
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
            new String[] { "First Last" },
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
