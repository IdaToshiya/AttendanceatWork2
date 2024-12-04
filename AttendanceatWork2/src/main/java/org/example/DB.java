package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    //接続用の情報をフィールドに定数として定義
    public static String RDB_DRIVE = "org.h2.Driver";
    public static String URL = "jdbc:h2:~/AttendDB";
    public static String USER = "bms";
    public static String PASSWD = "bms123";

    public static void main(String[] args) {

        try{
            Class.forName(RDB_DRIVE);
            Connection con = DriverManager.getConnection(URL, USER, PASSWD);

            //接続成功メッセージとコネクション情報の表示
            System.out.println("JDBCデータベース接続成功");
            System.out.println("con = " + con);

            con.close();

        }catch(Exception e){
            System.out.println("JDBCデータベース接続エラー:" + e);
        }
    }
}