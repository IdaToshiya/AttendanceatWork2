package org.example;

import java.sql.*;
import java.util.Scanner;

import static org.example.DB.*;

public class Search {
    public static void search() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("管理者パスワードを入力してください");
        String pas;
        pas = scanner.nextLine();
        if (pas.equals("admin")) {

            System.out.println("何を検索しますか？\n" +
                    "1.社員\n" +
                    "2.日付");

            int SelectNum;
            SelectNum = Integer.parseInt(scanner.nextLine());

            if (SelectNum == 1) {
                System.out.println("検索したい社員番号を入力してください");
                int EmployeeNum;
                EmployeeNum = Integer.parseInt(scanner.nextLine());

                try {
                    Connection con = DriverManager.getConnection(URL, USER, PASSWD);
                    String EmployeeSql = """
                            SELECT * FROM AttendList where 社員番号 =""" + EmployeeNum + """
                            """;
                    PreparedStatement EmployeeSelect = con.prepareStatement(EmployeeSql);
                    ResultSet EmployeeRes = EmployeeSelect.executeQuery();

                    while (EmployeeRes.next()) {
                        System.out.print("出勤日：" + EmployeeRes.getString("出勤日") + "\n");
                        System.out.print("出勤時間：" + EmployeeRes.getString("出勤時間") + "\n");
                        System.out.print("退勤時間：" + EmployeeRes.getString("退勤時間") + "\n");
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }return;
            } else if (SelectNum == 2) {
                System.out.println("検索したい月を入力してください");
                String Month;
                Month = scanner.nextLine();
                System.out.println("検索したい日を入力してください *一桁の場合でも必ず'0'を付けてください。　例:一日と入力したい場合、'01'");
                String Day;
                Day = scanner.nextLine();
                String MonthDay = "%s月%s日".formatted(Month, Day);

                try {
                    Connection con = DriverManager.getConnection(URL, USER, PASSWD);
                    String MonthSql = """
                            SELECT * FROM AttendList where 出勤日 ='""" + MonthDay + """
                            '
                            """;
                    PreparedStatement MonthSelect = con.prepareStatement(MonthSql);
                    ResultSet SelectRes = MonthSelect.executeQuery();

                    while (SelectRes.next()) {
                        System.out.print("社員番号：" + SelectRes.getInt("社員番号") + "\n");
                        System.out.print("出勤時間：" + SelectRes.getString("出勤時間") + "\n");
                        System.out.print("退勤時間：" + SelectRes.getString("退勤時間") + "\n");
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("番号を確認してください");
            }
            scanner.close();
        }
        else {
            System.out.println("管理者パスワードが間違えています");
        }
    }
}