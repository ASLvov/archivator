package app.utility;

import java.util.ArrayList;
import java.util.List;

public class InputParser {
    public static List<String> parseInputString(String inputString) {
        List<String> strings = new ArrayList<>();
        for (String s : inputString.split(" ")) {
            strings.add(s);
        }
        return strings;
    }
}
