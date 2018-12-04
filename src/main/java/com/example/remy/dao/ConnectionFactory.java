package com.example.remy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;

public class ConnectionFactory {
    public static final String URL = "jdbc:mysql://localhost:3306/remy_bot?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASS = "quigonjinn9232";

    /**
     * Get a connection to database
     *
     * @return Connection object
     */
    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
}