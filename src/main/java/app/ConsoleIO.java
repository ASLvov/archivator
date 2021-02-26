package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleIO {
    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void write(String message) {
        System.out.println(message);
    }

    public static String read() throws IOException {
        return bufferedReader.readLine();
    }
}
