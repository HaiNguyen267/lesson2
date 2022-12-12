package ex2_excelfile;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ExcelWritingExample {
    private static final String PATH = "src/ex2/data/";

    public static void main(String[] args) throws FileNotFoundException {
        List<Person> persons = getPersonsListFromConsole();

        writePersonsToExcelFile(persons);
    }


    private static List<Person> getPersonsListFromConsole() {
        List<Person> list = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        System.out.println("How many people would you want to enter: ");
        int num = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < num; i++) {
            System.out.println(String.format("Person #%d", (i+1)));
            Person person = askPersonInfo();
            list.add(person);
        }

        return list;
    }

    private static Person askPersonInfo() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter person name: ");
        String name = sc.nextLine();

        System.out.print("Enter person email: ");
        String email = sc.nextLine();

        System.out.print("Enter person age: ");
        int age = Integer.parseInt(sc.nextLine());

        return new Person(name, email, age);
    }

    private static void writePersonsToExcelFile(List<Person> persons) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Persons");

        // write column name
        Row firstRow = sheet.createRow(0);
        firstRow.createCell(0).setCellValue("Id");
        firstRow.createCell(1).setCellValue("Name");
        firstRow.createCell(2).setCellValue("Email");
        firstRow.createCell(3).setCellValue("Age");

        for (int i = 0; i < persons.size(); i++) {
            Row row = sheet.createRow(i + 1); // first row is for the column name
            Person person = persons.get(i);
            row.createCell(0).setCellValue(i + 1);
            row.createCell(1).setCellValue(person.getName());
            row.createCell(2).setCellValue(person.getEmail());
            row.createCell(3).setCellValue(person.getAge());
        }

        try (
                FileOutputStream fos = new FileOutputStream(PATH + "persons.xlsx")
        ) {
            workbook.write(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("File written successfully");
    }

}
