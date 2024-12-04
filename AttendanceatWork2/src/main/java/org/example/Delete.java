package org.example;

import java.sql.*;
import java.util.Scanner;

import static org.example.DB.*;

public class Delete {
    public static void delete() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("管理者パスワードを入力してください");
        String pas;
        pas = scanner.nextLine();
        if (pas.equals("admin")) {
            System.out.println("削除する対象のデータを確認します。\n" +
                    "社員番号を入力してください");
            int EmployeeNum;
            EmployeeNum = scanner.nextInt();
            System.out.println("検索したい月を入力してください");
            String Month;
            Month = scanner.next();
            System.out.println("検索したい日を入力してください *一桁の場合でも必ず'0'を付けてください。　例:一日と入力したい場合、'01'");
            String Day;
            Day = scanner.next();
            String MonthDay = "%s月%s日".formatted(Month, Day);

            try {
                Connection con = DriverManager.getConnection(URL, USER, PASSWD);
                String DeleteSql = """
                            delete from AttendList where 出勤日 ='""" + MonthDay + """
                            '
                            and 社員番号 =""" + EmployeeNum + """
                            """;
                PreparedStatement Create = con.prepareStatement(DeleteSql);
                Create.executeUpdate();

                System.out.println("削除しました");

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            scanner.close();
        }
        else {
            System.out.println("管理者パスワードが間違えています");
        }
    }
}
