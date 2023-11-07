package org.kainos.ea.api;

import org.kainos.ea.client.DatabaseConnectionFailedException;
import org.kainos.ea.db.ConnectionDAO;

public class ConnectionService {

    private final ConnectionDAO connectionDAO = new ConnectionDAO();

    public String testDatabaseConnection() throws DatabaseConnectionFailedException {
        return connectionDAO.testDatabaseConnection();
    }
}
