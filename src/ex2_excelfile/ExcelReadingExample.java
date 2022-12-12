package ex2_excelfile;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class ExcelReadingExample {
    private static final String PATH = "src/ex2/data/";
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter file name: ");
        String fileName = sc.nextLine();

        FileInputStream fis = new FileInputStream(PATH + fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        XSSFSheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iter = firstSheet.rowIterator();

        while (iter.hasNext()) {
            Row row = iter.next();
            String column1 = getCellValue(row, 0);
            String column2 = getCellValue(row, 1);
            String column3 = getCellValue(row, 2);
            String column4 = getCellValue(row, 3);
            System.out.println(String.format("| %-3s | %-10s | %-20S | %-3s |",
                    column1,
                    column2,
                    column3,
                    column4)
            );
        }
    }

    private static String getCellValue(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex);

        switch (cell.getCellType()) {
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case STRING:
                return cell.getStringCellValue();
        }

        return null;
    }
}
