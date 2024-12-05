package javacli;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

public interface ArgumentParser {
    Class parses();

    Object parse(String arg) throws ArgumentParseException;

    static Object parseUsing(Class type, ArgumentParser[] parsers, String arg) throws ArgumentParseException {
        ArgumentParser typeParser = null;
        for (ArgumentParser parser : parsers) {
            if (parser.parses() == type) {
                typeParser = parser;
                break;
            }
        }

        if (typeParser == null) {
            throw new ArgumentParseException("Parser for " + type + " doesn't exist.", null);
        }

        return typeParser.parse(arg);
    }

    static String[] split(String args) {
        ArrayList<String> matches = new ArrayList<String>();
        Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"");
        Matcher regexMatcher = regex.matcher(args);
        while (regexMatcher.find()) {
            if (regexMatcher.group(1) != null) {
                matches.add(regexMatcher.group(1));
            } else {
                matches.add(regexMatcher.group());
            }
        }
        String[] strings = new String[matches.size()];
        return matches.toArray(strings);
    }

    static ArgumentParser[] parsers() {
        return new ArgumentParser[] {
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
                    return Byte.class;
                }

                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Byte.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            },
            new ArgumentParser() {
                public Class parses() {
                    return Class.forPrimitiveName("byte");
                }

                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Byte.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            },
            new ArgumentParser() {
                public Class parses() {
                    return Short.class;
                }

                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Short.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            },
            new ArgumentParser() {
                public Class parses() {
                    return Class.forPrimitiveName("short");
                }

                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Short.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            },
            new ArgumentParser() {
                public Class parses() {
                    return Integer.class;
                }

                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Integer.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
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
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            },
            new ArgumentParser() {
                public Class parses() {
                    return Long.class;
                }

                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Long.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            },
            new ArgumentParser() {
                public Class parses() {
                    return Class.forPrimitiveName("long");
                }

                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Long.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
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
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            },
            new ArgumentParser() {
                public Class parses() {
                    return Class.forPrimitiveName("float");
                }

                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Float.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            },
            new ArgumentParser() {
                public Class parses() {
                    return Double.class;
                }

                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Double.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            },
            new ArgumentParser() {
                public Class parses() {
                    return Class.forPrimitiveName("double");
                }

                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Double.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            }
        };
    }
}
