package db;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class db {
    private Connection conn;
    private Statement statement;

    public db() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("完成jdbc驱动的加载<br>");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://103.229.126.165:3306/future simulator?user=commitor&password=123456&useUnicode=true&characterEncoding=UTF-8");
            System.out.println("连接上数据库<br>");
            statement = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void executeUpdate(String sql) {
        /* 数据库修改 */
        try {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet executeQuery(String sql) {
        /* 数据库查询 */
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public void close() {
        /* 关闭数据库 */
        try {
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}