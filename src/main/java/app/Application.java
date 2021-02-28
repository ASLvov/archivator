package app;

import app.service.CompressService;
import app.utility.ConsoleIO;

import java.io.IOException;

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
            if (new CompressService().compressFiles(parseInputString(inputString))) {
                System.out.println("Compressed successfully!");
            } else {
                System.out.println("Error! Can't compress");
            }
        } else {
            ConsoleIO.write("Wrong input! Enter full filenames for compression");
        }
    }
}
