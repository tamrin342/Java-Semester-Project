package com.example.lms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private final String DB_HOSTNAME = "localhost";
    private final String DB_NAME = "panditto";
    private final String DB_URL = "jdbc:mysql://"+DB_HOSTNAME+"/"+DB_NAME;
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "*****";
    private static SingletonConnection instance = new SingletonConnection();
    private static Connection connection;

    private SingletonConnection()
    {
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        }
        catch (SQLException ex)
        {
            System.err.println("SQLException");
        }
    }
    public static Connection getConnection()
    {
        return connection;
    }
}
