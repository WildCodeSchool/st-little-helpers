package dev.wcs.tutoring.littlehelpers.xlsx;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDate;

public class ExcelTransformer {

    public void changeCellValues(File input) throws IOException {
        FileInputStream inputStream = new FileInputStream(input);
        XSSFWorkbook excel = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = excel.getSheetAt(0);

        XSSFCell contentCell = sheet.getRow(8).createCell(2);
        XSSFFont font = excel.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setFontName("Courier New");
        font.setItalic(true);
        font.setColor(Font.COLOR_RED);
        font.setBold(true);
        CellStyle style = excel.createCellStyle();
        style.setFont(font);
        contentCell.setCellStyle(style);
        contentCell.setCellValue("Learn Apache POI");

        XSSFCell dateCell = sheet.getRow(8).getCell(1);
        dateCell.setCellValue(LocalDate.now());

        FileOutputStream outputStream = new FileOutputStream(input.getAbsolutePath() + "_out.xlsx");
        excel.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    public static void main(String[] args) throws IOException {
        File input = new File("src/test/resources/financedata.xlsx");
        new ExcelTransformer().changeCellValues(input);

    }

}
