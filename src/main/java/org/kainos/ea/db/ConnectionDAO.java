package org.kainos.ea.db;

import org.kainos.ea.client.DatabaseConnectionFailedException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDAO {

    private final DatabaseConnector databaseConnector = new DatabaseConnector();


    public String testDatabaseConnection() throws DatabaseConnectionFailedException {

        try (Connection c = databaseConnector.getConnection(); Statement st = c.createStatement()) {

            ResultSet rs = st.executeQuery(
                    "SHOW STATUS WHERE Variable_name='Uptime'");

            if (rs.next()) {
                return "Database Online For: " + System.getenv("DB_NAME") + "!";
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DatabaseConnectionFailedException();
        }

        throw new DatabaseConnectionFailedException();
    }
}
