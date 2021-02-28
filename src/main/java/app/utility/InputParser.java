package app.utility;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputParser {
    public static List<Path> parseInputString(String inputString) {
        List<Path> paths = new ArrayList<>();
        for (String s : inputString.split(" ")) {
            paths.add(Paths.get(s));
        }
        return paths;
    }
}
