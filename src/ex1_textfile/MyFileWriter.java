package ex1_textfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MyFileWriter {
    private static final String PATH = "./src/ex1/data/";

    public static void writeToFile(String fileName, String text){
        try {
            Files.writeString(Path.of(PATH + fileName), text);
            System.out.println("File written successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
