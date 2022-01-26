package dev.wcs.tutoring.littlehelpers.xlsx;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelReader {

    public void changeCellValues(File input) throws IOException {
        FileInputStream inputStream = new FileInputStream(input);
        XSSFWorkbook excel = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = excel.getSheetAt(0);
        XSSFCell formulaCell = sheet.getRow(1).createCell(1);

        XSSFFont font = excel.createFont();
        font.setFontHeightInPoints((short) 36);
        font.setFontName("Courier New");
        font.setItalic(true);
        font.setColor(Font.COLOR_RED);
        font.setBold(true);
        CellStyle style = excel.createCellStyle();
        style.setFont(font);
        formulaCell.setCellStyle(style);

        formulaCell.setCellValue("HELLO FROM JAVA");
        FileOutputStream outputStream = new FileOutputStream(input.getAbsolutePath() + "_out.xlsx");
        excel.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    public static void main(String[] args) throws IOException {
        File input = new File("src/test/resources/financedata.xlsx");
        new ExcelReader().changeCellValues(input);

    }

}
