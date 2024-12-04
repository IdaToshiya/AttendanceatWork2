package org.example;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

import static org.example.DB.*;

public class LeavingWork {
    public static void leavingwork(){

        System.out.println("社員番号を入力してください");
        Scanner scanner = new Scanner(System.in);
        int EmployeeNum;
        EmployeeNum = Integer.parseInt(scanner.nextLine());

        var Data = LocalDateTime.now();
        String Time = "%tH時%tM分".formatted(Data, Data);

        try {
            Connection con = DriverManager.getConnection(URL,USER,PASSWD);
            String UpdateSql = """
                               UPDATE AttendList SET 退勤時間 ='""" + Time + """
                                'where 出勤時間 =
                               (select 出勤時間 from attendlist where　社員番号 =""" + EmployeeNum + """
                                order by 出勤日 DESC limit 1)
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
        System.out.println("退勤しました");
        scanner.close();
    }
}
