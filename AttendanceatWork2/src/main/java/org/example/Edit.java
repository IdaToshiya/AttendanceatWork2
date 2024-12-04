package org.example;

import java.sql.*;
import java.util.Scanner;

import static org.example.DB.*;

public class Edit {
    public static void edit() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("管理者パスワードを入力してください");
        String pas;
        pas = scanner.nextLine();
        if (pas.equals("admin")) {
            System.out.println("出退勤時間の編集を行います\n" +
                        "社員番号を入力してください");
            int EmployeeNum;
            EmployeeNum = Integer.parseInt(scanner.next());
            System.out.println("検索したい月を入力してください");
            String Month;
            Month = scanner.next();
            System.out.println("検索したい日を入力してください *一桁の場合でも必ず'0'を付けてください。　例:一日と入力したい場合、'01'");
            String Day;
            Day = scanner.next();
            String MonthDay = "%s月%s日".formatted(Month, Day);
            System.out.println("編集する項目を選んでください\n" +
                    "1.出勤時間\n" +
                    "2.退勤時間");
            int SelectNum = Integer.parseInt(scanner.next());
            if (SelectNum == 1) {
                System.out.println("正しい短針の時間を入力してください");
                String hour;
                hour = scanner.next();
                System.out.println("正しい長針の時間を入力してください");
                String minute;
                minute = scanner.next();
                String HourMinute = "%s月%s日".formatted(hour, minute);
                    try {
                        Connection con = DriverManager.getConnection(URL,USER,PASSWD);
                        String UpdateSql = """
                               UPDATE AttendList SET 出勤時間 ='""" + HourMinute + """
                               'where 社員番号 =""" + EmployeeNum + """
                               and 出勤日 ='""" + MonthDay + """
                               '
                               """;
                        PreparedStatement Create = con.prepareStatement(UpdateSql);
                        Create.executeUpdate();
                        UpdateSql = """
                        select * from AttendList where 社員番号 =""" + EmployeeNum + """
                        """;
                        Create = con.prepareStatement(UpdateSql);
                        ResultSet InsertRes = Create.executeQuery();

                        while (InsertRes.next()) {
                            // 実行結果の表示
                            System.out.println("社員番号：" + InsertRes.getInt("社員番号"));
                            System.out.println("出勤時間：" + InsertRes.getString("出勤時間"));
                            System.out.println("退勤時間：" + InsertRes.getString("退勤時間"));
                        }
                    } catch (SQLException e) {
                        // SQLのエラー発生時の処理
                        e.printStackTrace();
                    }

                } else if (SelectNum == 2) {
                System.out.println("正しい短針の時間を入力してください");
                String hour;
                hour = scanner.next();
                System.out.println("正しい長針の時間を入力してください");
                String minute;
                minute = scanner.next();
                String HourMinute = "%s月%s日".formatted(hour, minute);

                try {
                    Connection con = DriverManager.getConnection(URL,USER,PASSWD);
                    String UpdateSql = """
                               UPDATE AttendList SET 退勤時間 ='""" + HourMinute + """
                               'where 社員番号 =""" + EmployeeNum + """
                               and 出勤日 ='""" + MonthDay + """
                               '
                               """;
                    PreparedStatement Create = con.prepareStatement(UpdateSql);
                    Create.executeUpdate();
                    UpdateSql = """
                        select * from AttendList where 社員番号 =""" + EmployeeNum + """
                        """;
                    Create = con.prepareStatement(UpdateSql);
                    ResultSet InsertRes = Create.executeQuery();

                    while (InsertRes.next()) {
                        // 実行結果の表示
                        System.out.println("社員番号：" + InsertRes.getInt("社員番号"));
                        System.out.println("出勤時間：" + InsertRes.getString("出勤時間"));
                        System.out.println("退勤時間：" + InsertRes.getString("退勤時間"));
                    }
                } catch (SQLException e) {
                    // SQLのエラー発生時の処理
                    e.printStackTrace();
                }
                    } else{
                        System.out.println("番号を確認してください");
                    }

            }else{
            System.out.println("管理者パスワードが間違えています");
        }
    }
}