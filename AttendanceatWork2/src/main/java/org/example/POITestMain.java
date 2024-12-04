package org.example;

import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;

public class POITestMain {
    public static void main(String [] args) {

        FileOutputStream fos = null;
        XSSFWorkbook workbook = null;

        try {

            // ワークブック→シート→行→セルの生成
            workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet();
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell = row.createCell(0);

            // セルの書式の生成
            XSSFCellStyle cellStyle = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setFontName("ＭＳ ゴシック");
            cellStyle.setFont(font);
            cell.setCellStyle(cellStyle);

            // セルに書き込み
            cell.setCellValue("Hello World!");

            // ファイル書き込み
            fos = new FileOutputStream("C:\\Users\\idako\\OneDrive\\デスクトップ\\newFileFromOrigin.xlsx");
            workbook.write(fos);

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (workbook != null) {
                    workbook.close();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
