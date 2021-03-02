package app;

import app.service.CompressService;
import app.service.DecompressService;
import app.utility.ConsoleIO;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static app.utility.InputParser.parseInputString;

public class Application {
    public static void main(String[] args) throws Exception {
        String inputString = "";
        try {
            inputString = ConsoleIO.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> inputList = parseInputString(inputString);
        if (!inputList.isEmpty() && !inputList.get(0).equals("decompress")) {
            if (new CompressService().compressFiles(inputList)) {
                System.out.println("Compressed successfully!");
            } else {
                System.out.println("Error! Can't compress");
            }
        } else if (!inputList.isEmpty() && inputList.get(0).equals("decompress")) {
            if (new DecompressService().decompressFile(inputList.get(1))) {
                System.out.println("Decompressed successfully!");
            } else {
                System.out.println("Error! Can't decompress file");
            }
        } else {
            ConsoleIO.write("Wrong input! Enter full filenames for compression");
        }
    }
}
