package app;

import java.io.IOException;

import static app.Compressor.compress;

public class Application {
    public static void main(String[] args) {
        String inputString = "";
        try {
            inputString =  ConsoleIO.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (compress(inputString)) {
            ConsoleIO.write("Compressed successfully");
        } else {
            ConsoleIO.write("Error, try again");
        }
    }
}
