package pl.psweatherapi.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnector {
    private static final String SQL_LINK = "jdbc:mysql://localhost:3306/weather";
    private static final String SQL_USER = "root";
    private static final String SQL_PASS = "";
    private static final String SQL_CLASS = "com.mysql.jdbc.Driver";

    private static MysqlConnector connector = new MysqlConnector();
    public static MysqlConnector getInstance() {
        return connector;
    }

    private Connection connection;

    private MysqlConnector(){
        connect();
    }

    private void connect() {
        try {
            Class.forName(SQL_CLASS);
            connection = DriverManager.getConnection(SQL_LINK, SQL_USER, SQL_PASS);
            System.out.println("Połączono");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }
}