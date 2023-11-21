package org.kainos.ea.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
  private Connection conn;

  public Connection getConnection() throws SQLException {
    String user;
    String password;
    String host;
    String name;

    if (conn != null && !conn.isClosed()) { return conn; }

    user = System.getenv("DB_USERNAME");
    System.out.println("user env: " + user);
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
