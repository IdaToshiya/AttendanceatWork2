package org.example;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class ExcelFile {
    public static void excelfile() {
        String RDB_DRIVE = "org.h2.Driver";
        String URL = "jdbc:h2:~/AttendDB";
        String USER = "bms";
        String PASSWD = "bms123";

        Scanner scanner = new Scanner(System.in);
        System.out.println("管理者パスワードを入力してください");
        String pas;
        pas = scanner.next();
        if (pas.equals("admin")) {
            System.out.println("出力方法を選択してください\n" +
                    "1.社員番号で絞る\n" +
                    "2.期間で絞る\n" +
                    "3.全て出力");
            int SelectNum;
            SelectNum = scanner.nextInt();

            if (SelectNum == 1) {
                System.out.println("社員番号を入力してください");
                int EmployeeNum;
                EmployeeNum = scanner.nextInt();

                try {
                    Connection con = DriverManager.getConnection(URL, USER, PASSWD);
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("""
                            Select * from AttendList where 社員番号 = '"""+ EmployeeNum + """
                            '""");

                    HSSFWorkbook wb = new HSSFWorkbook();
                    HSSFSheet sheet = wb.createSheet("Excel Sheet");
                    HSSFRow rowhead = sheet.createRow((short) 0);
                    rowhead.createCell((short) 0).setCellValue("社員番号");
                    rowhead.createCell((short) 1).setCellValue("出勤日");
                    rowhead.createCell((short) 2).setCellValue("出勤時間");
                    rowhead.createCell((short) 3).setCellValue("退勤時間");
                    System.out.println("test try");

                    int index = 1;
                    while (rs.next()) {

                        HSSFRow row = sheet.createRow((short) index);
                        row.createCell((short) 0).setCellValue(rs.getInt(1));
                        row.createCell((short) 1).setCellValue(rs.getString(2));
                        row.createCell((short) 2).setCellValue(rs.getString(3));
                        row.createCell((short) 3).setCellValue(rs.getString(4));
                        index++;
                        System.out.println("test while");
                    }
                    FileOutputStream fileOut = new FileOutputStream("C:\\Users\\idako\\OneDrive\\デスクトップ\\Employee.xls");
                    wb.write(fileOut);
                    fileOut.close();
                    System.out.println("Data is saved in Excel file.");
                    rs.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if (SelectNum == 2) {
                System.out.println("開始の月を入力してください");
                String StartMonthPeriod;
                StartMonthPeriod = scanner.next();

                System.out.println("開始の日にちを入力してください");
                String StartDayPeriod;
                StartDayPeriod = scanner.next();

                System.out.println("終わりの月を入力してください");
                String LastMonthPeriod;
                LastMonthPeriod = scanner.next();

                System.out.println("終わりの日にちを入力してください");
                String LastDayPeriod;
                LastDayPeriod = scanner.next();

                String StartPeriod = "%s月%s日".formatted(StartMonthPeriod, StartDayPeriod);
                String LastPeriod = "%s月%s日".formatted(LastMonthPeriod, LastDayPeriod);

                try {
                    Connection con = DriverManager.getConnection(URL, USER, PASSWD);
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("""
                            Select * from AttendList where 出勤日 between '""" + StartPeriod + """
                            ' and '""" + LastPeriod + """
                            ' order by 出勤日
                            """);

                    HSSFWorkbook wb = new HSSFWorkbook();
                    HSSFSheet sheet = wb.createSheet("Excel Sheet");
                    HSSFRow rowhead = sheet.createRow((short) 0);
                    rowhead.createCell((short) 0).setCellValue("社員番号");
                    rowhead.createCell((short) 1).setCellValue("出勤日");
                    rowhead.createCell((short) 2).setCellValue("出勤時間");
                    rowhead.createCell((short) 3).setCellValue("退勤時間");
                    System.out.println("test try");

                    int index = 1;
                    while (rs.next()) {

                        HSSFRow row = sheet.createRow((short) index);
                        row.createCell((short) 0).setCellValue(rs.getInt(1));
                        row.createCell((short) 1).setCellValue(rs.getString(2));
                        row.createCell((short) 2).setCellValue(rs.getString(3));
                        row.createCell((short) 3).setCellValue(rs.getString(4));
                        index++;
                        System.out.println("test while");
                    }
                    FileOutputStream fileOut = new FileOutputStream("C:\\Users\\idako\\OneDrive\\デスクトップ\\Period.xls");
                    wb.write(fileOut);
                    fileOut.close();
                    System.out.println("Data is saved in Excel file.");
                    rs.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if (SelectNum == 3) {
                try {
                    Connection con = DriverManager.getConnection(URL, USER, PASSWD);
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("Select * from AttendList");

                    HSSFWorkbook wb = new HSSFWorkbook();
                    HSSFSheet sheet = wb.createSheet("Excel Sheet");
                    HSSFRow rowhead = sheet.createRow((short) 0);
                    rowhead.createCell((short) 0).setCellValue("社員番号");
                    rowhead.createCell((short) 1).setCellValue("出勤日");
                    rowhead.createCell((short) 2).setCellValue("出勤時間");
                    rowhead.createCell((short) 3).setCellValue("退勤時間");
                    System.out.println("test try");

                    int index = 1;
                    while (rs.next()) {

                        HSSFRow row = sheet.createRow((short) index);
                        row.createCell((short) 0).setCellValue(rs.getInt(1));
                        row.createCell((short) 1).setCellValue(rs.getString(2));
                        row.createCell((short) 2).setCellValue(rs.getString(3));
                        row.createCell((short) 3).setCellValue(rs.getString(4));
                        index++;
                        System.out.println("test while");
                    }
                    FileOutputStream fileOut = new FileOutputStream("C:\\Users\\idako\\OneDrive\\デスクトップ\\AttendList.xls");
                    wb.write(fileOut);
                    fileOut.close();
                    System.out.println("Data is saved in Excel file.");
                    rs.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else {
                System.out.println("番号を確認してください");
            }
        }
        else {
            System.out.println("管理者パスワードが間違えています");
        }
    }
}
