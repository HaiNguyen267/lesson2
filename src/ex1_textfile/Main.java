package ex1_textfile;

import java.io.File;
import java.util.Scanner;

public class Main {
    private static final String PATH = "./src/ex1/data/";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Menu mainMenu = createMainMenu();

        mainMenu.run();
    }

    private static Menu createMainMenu() {;
        Menu mainMenu = new Menu("Main Menu");

        Menu readingMenu = createReadingMenu(); // submenu
        Menu writingMenu = createWritingMenu(); // submenu

        mainMenu.addItems(readingMenu); // add submenu to main menu
        mainMenu.addItems(writingMenu); // add submenu to main menu

        return mainMenu;
    }

    private static Menu createReadingMenu() {
        Menu readingFileMenu = new Menu("Reading File Menu");

        Action listAllFiles = new Action("List all file") {
            @Override
            public void run() {
                File[] files = new File(PATH).listFiles();

                System.out.println("Files: ");
                if (files.length > 0) {
                    for (int i = 0; i < files.length; i++) {
                        System.out.println(String.format("%d. %s", i + 1, files[i].getName()));
                    }
                } else {
                    System.out.println("No files found");
                }
            }
        };

        Action readAFile = new Action("Read a file") {
            @Override
            public void run() {
                String fileName = getUserFileNameInput();

                if (MyFileReader.checkIfFileExists(fileName)) {
                    String content = MyFileReader.readFile(fileName);
                    System.out.println(content);
                } else {
                    System.out.println("File not found");
                }
            }
        };

        Action deleteAFile = new Action("Delete a file") {
            @Override
            public void run() {
                String fileName = getUserFileNameInput();

                if (MyFileReader.checkIfFileExists(fileName)) {
                    MyFileReader.deleteFile(fileName);
                    System.out.println("Delete successfully");
                } else {
                    System.out.println("File not found");
                }
            }
        };

        readingFileMenu.addItems(listAllFiles);
        readingFileMenu.addItems(readAFile);
        readingFileMenu.addItems(deleteAFile);

        return readingFileMenu;
    }

    private static Menu createWritingMenu() {

        Menu writingMenu = new Menu("Writing File Menu");

        Action createNewFile = new Action("Create new file") {
            @Override
            public void run() {

                String fileName = getUserFileNameInput();
                String content = getUserTextInput();

                MyFileWriter.writeToFile(fileName, content);
            }
        };


        Action writeToFile = new Action("Write to a file") {
            @Override
            public void run() {
                String fileName = getUserFileNameInput();

                if (!MyFileReader.checkIfFileExists(fileName)) {
                    System.out.println("File does not exist");
                    return;
                }

                String newContent = getUserTextInput();
                String oldContent = MyFileReader.readFile(fileName);

                newContent = oldContent + "\n" + newContent;
                MyFileWriter.writeToFile(fileName, newContent);
                System.out.println("here");
            }
        };

        writingMenu.addItems(createNewFile);
        writingMenu.addItems(writeToFile);
        return writingMenu;
    }

    private static String getUserFileNameInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter file name: ");
        String fileName = sc.nextLine();
        if (fileName.endsWith(".txt")) {
            fileName = fileName + ".txt";
        }

        return fileName;
    }

    private static String getUserTextInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter file content (press Enter twice to stop): ");
        StringBuilder sb = new StringBuilder();

        // when user enters 2 empty lines, stop reading
        String line1 = sc.nextLine();
        String line2 = sc.nextLine();
        while (!line1.isBlank() && !line2.isBlank()) {
            sb.append(line1).append("\n");
            sb.append(line2).append("\n");
            line1 = sc.nextLine();
            line2 = sc.nextLine();
        }

        return sb.toString();

    }
}
