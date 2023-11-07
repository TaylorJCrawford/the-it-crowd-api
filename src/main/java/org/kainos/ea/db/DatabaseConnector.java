package org.kainos.ea.db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {

    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        String user, password, host, name;

        if (conn != null && !conn.isClosed()) { return conn; }

        user = System.getenv("DB_USERNAME");
        password = System.getenv("DB_PASSWORD");
        host = System.getenv("DB_HOST");
        name = System.getenv("DB_NAME");

        if (user == null || password == null || host == null)
            throw new IllegalArgumentException("Missing Environment Variables " +
                        "must contain user, password, name and host variables.");

        conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + name + "?useSSL=false", user, password);
        return conn;
    }
}
