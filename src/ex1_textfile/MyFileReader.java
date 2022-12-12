package ex1_textfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MyFileReader {
    private static final String PATH = "./src/ex1/data/";

    public static boolean checkIfFileExists(String fileName) {
        return Files.exists(Path.of(PATH + fileName));
    }

    public static String readFile(String fileName) {
        try {
            return new String(Files.readAllBytes(Path.of(PATH + fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteFile(String fileName) {
        try {
            Files.deleteIfExists(Path.of(PATH + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
