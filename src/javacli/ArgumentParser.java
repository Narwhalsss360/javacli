package javacli;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

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

    Class parses();

    Object parse(String arg) throws ArgumentParseException;
}
