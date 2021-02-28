package app;

import app.utility.ConsoleIO;

import java.io.IOException;

import static app.service.CompressService.compressFiles;
import static app.utility.InputParser.parseInputString;

public class Application {
    public static void main(String[] args) {
        String inputString = "";
        try {
            inputString = ConsoleIO.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!parseInputString(inputString).isEmpty()) {
            compressFiles(parseInputString(inputString));
        } else {
            ConsoleIO.write("Error, try again");
        }
    }
}
