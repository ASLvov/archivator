package app;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Compressor {
    public static boolean compress(String inputString) {
        List<Path> paths = new ArrayList<>();
        for (String s : inputString.split(" ")) {
            paths.add(Paths.get(s));
        }
        System.out.println(paths);
        return true;
    }
}
