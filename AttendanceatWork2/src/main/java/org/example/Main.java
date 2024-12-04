package org.example;

import java.util.Scanner;
import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String RDB_DRIVE = "org.h2.Driver";
        String URL = "jdbc:h2:~/AttendDB";
        String USER = "bms";
        String PASSWD = "bms123";

        try{
            Connection con = DriverManager.getConnection(URL,USER,PASSWD);
            String CreateSql = "CREATE TABLE IF NOT EXISTS AttendList " +
                    "(社員番号 VARCHAR(4)," +
                    " 出勤日 VARCHAR(10)," +
                    " 出勤時間 VARCHAR(10)," +
                    " 退勤時間 VARCHAR(20))";
            PreparedStatement Create = con.prepareStatement(CreateSql);
            Create.executeUpdate();
            CreateSql = "select * from AttendList";
            Create = con.prepareStatement(CreateSql);
            ResultSet CreateRes = Create.executeQuery();

        } catch (SQLException e) {
            // SQLのエラー発生時の処理
            e.printStackTrace();
            System.out.println("作成に失敗しました");
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("====勤怠管理アプリケーション===\n" +
                "1.出勤\n" +
                "2.退勤\n" +
                "3.検索\n" +
                "4.編集\n" +
                "5.削除\n" +
                "6.Excelに出力");

        int SelectNum;
        SelectNum = Integer.parseInt(scanner.nextLine());

        if (SelectNum == 1) {
            System.out.println("出勤を登録します");
            AttendanceatWork attendanceatwork = new AttendanceatWork();
            AttendanceatWork.attendanceatwork();
        } else if (SelectNum == 2) {
            System.out.println("退勤を登録します");
            LeavingWork leavingWork = new LeavingWork();
            LeavingWork.leavingwork();
        } else if (SelectNum == 3) {
            System.out.println("検索します");
            Search search = new Search();
            Search.search();
        } else if (SelectNum == 4) {
            System.out.println("編集します");
            Edit edit = new Edit();
            Edit.edit();
        } else if (SelectNum == 5) {
            System.out.println("削除します");
            Delete delete = new Delete();
            Delete.delete();
        } else if (SelectNum == 6) {
            System.out.println("Excelでファイルを出力します");
            ExcelFile excelfile = new ExcelFile();
            ExcelFile.excelfile();
        } else {
            System.out.println("番号を確認してください");
        }
        scanner.close();
    }
}