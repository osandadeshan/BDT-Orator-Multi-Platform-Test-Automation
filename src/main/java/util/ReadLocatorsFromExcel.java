package util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Created by Osanda on 7/14/2017.
 */


public abstract class ReadLocatorsFromExcel {

    private static final String LOCATORS_FILE_PATH = System.getProperty("user.dir") + "\\" + System.getenv("locators_file_path");
    private static String value = "";

    public static String getDataFromExcel(int row, int column) {
        try {
            FileInputStream excelFile = new FileInputStream(new File(LOCATORS_FILE_PATH));
            System.out.println(LOCATORS_FILE_PATH);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet workSheet = workbook.getSheetAt(0);
            value = workSheet.getRow(row).getCell(column).getStringCellValue();
            System.out.println(value);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String getElementName(String elementName) throws IOException {
        int row, column;
        row = GetCellAddressInExcel.findRowNumber(elementName);
        column = GetCellAddressInExcel.findColumnNumber(elementName);
        return getDataFromExcel(row, column);
    }

    public static String getHow(String elementName) throws IOException {
        int row, column;
        row = GetCellAddressInExcel.findRowNumber(elementName);
        column = GetCellAddressInExcel.findColumnNumber(elementName) + 1;
        return getDataFromExcel(row, column);
    }

    public static String getAndroidLocator(String elementName) throws IOException {
        int row, column;
        row = GetCellAddressInExcel.findRowNumber(elementName);
        column = GetCellAddressInExcel.findColumnNumber(elementName) + 2;
        return getDataFromExcel(row, column);
    }

    public static String getIosLocator(String elementName) throws IOException {
        int row, column;
        row = GetCellAddressInExcel.findRowNumber(elementName);
        column = GetCellAddressInExcel.findColumnNumber(elementName) + 3;
        return getDataFromExcel(row, column);
    }


}