package org.example;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

import static org.example.DB.*;

public class AttendanceatWork {
    public static void attendanceatwork() {

        System.out.println("社員番号を入力してください");
        Scanner scanner = new Scanner(System.in);
        int EmployeeNum;
        EmployeeNum = Integer.parseInt(scanner.nextLine());

        var Date = LocalDateTime.now();
        String Day = "%tm月%td日".formatted(Date, Date);
        String Time = "%tH時%tM分".formatted(Date, Date);

        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWD);
            String InsertSql = """
                            insert into AttendList values(
                            '""" + EmployeeNum + """
                            ', '""" + Day + """
                            ',
                            '""" + Time + """
                            ',null)
                            """;
            PreparedStatement Create = con.prepareStatement(InsertSql);
            Create.executeUpdate();
            InsertSql = """
                        select * from AttendList where 社員番号 =""" + EmployeeNum + """
                        """;
            Create = con.prepareStatement(InsertSql);
            ResultSet InsertRes = Create.executeQuery();

            while (InsertRes.next()) {
                // 実行結果の表示
                System.out.println("社員番号：" + InsertRes.getInt("社員番号"));
                System.out.println("出勤日：" + InsertRes.getString("出勤日"));
                System.out.println("出勤時間：" + InsertRes.getString("出勤時間"));
            }
        } catch (SQLException e) {
            // SQLのエラー発生時の処理
            e.printStackTrace();
        }
        scanner.close();
    }
}
