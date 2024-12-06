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
                @Override
                public Class parses() {
                    return String.class;
                }

                @Override
                public Object parse(String arg) {
                    return arg;
                }
            },
            new ArgumentParser() {
                @Override
                public Class parses() {
                    return Byte.class;
                }

                @Override
                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Byte.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            },
            new ArgumentParser() {
                @Override
                public Class parses() {
                    return byte.class;
                }

                @Override
                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Byte.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            },
            new ArgumentParser() {
                @Override
                public Class parses() {
                    return Short.class;
                }

                @Override
                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Short.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            },
            new ArgumentParser() {
                @Override
                public Class parses() {
                    return short.class;
                }

                @Override
                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Short.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            },
            new ArgumentParser() {
                @Override
                public Class parses() {
                    return Integer.class;
                }

                @Override
                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Integer.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            },
            new ArgumentParser() {
                @Override
                public Class parses() {
                    return int.class;
                }

                @Override
                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Integer.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            },
            new ArgumentParser() {
                @Override
                public Class parses() {
                    return Long.class;
                }

                @Override
                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Long.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            },
            new ArgumentParser() {
                @Override
                public Class parses() {
                    return long.class;
                }

                @Override
                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Long.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            },
            new ArgumentParser() {
                @Override
                public Class parses() {
                    return Float.class;
                }

                @Override
                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Float.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            },
            new ArgumentParser() {
                @Override
                public Class parses() {
                    return float.class;
                }

                @Override
                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Float.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            },
            new ArgumentParser() {
                @Override
                public Class parses() {
                    return Double.class;
                }

                @Override
                public Object parse(String arg) throws ArgumentParseException {
                    try {
                        return Double.valueOf(arg);
                    } catch (Exception exception) {
                        throw new ArgumentParseException("Cannot parse '" + arg + "' as " + parses() + ".", exception);
                    }
                }
            },
            new ArgumentParser() {
                @Override
                public Class parses() {
                    return double.class;
                }

                @Override
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
