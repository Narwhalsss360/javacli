package javacli;

public interface ArgumentParser {
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

    Class parses();

    Object parse(String arg) throws ArgumentParseException;
}
