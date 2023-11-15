package org.kainos.ea.db;

import org.kainos.ea.cli.LoginDetails;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.client.DatabaseConnectionFailedException;
import org.kainos.ea.client.InvalidLoginAttemptException;
import org.kainos.ea.client.CouldNotFindUserAccountException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAO {

  private final DatabaseConnector databaseConnector = new DatabaseConnector();

  public LoginRequest getUserPasswordHash (LoginRequest loginRequest) throws
          InvalidLoginAttemptException, DatabaseConnectionFailedException {

    String selectStatement = "SELECT passwordHash " +
            "FROM `Users` WHERE email = ?";
    // Check User Login Details Are Correct.
    try (Connection c = databaseConnector.getConnection();
            PreparedStatement st = c.prepareStatement(selectStatement)) {

      st.setString(1, loginRequest.getEmail());

      ResultSet rs = st.executeQuery();

      if (rs.next()) {
        String passwordHash = rs.getString("passwordHash");
        loginRequest.setPasswordHash(passwordHash);

        return loginRequest;
      }

      throw new InvalidLoginAttemptException();

    } catch (SQLException e) {
      System.err.println(e.getMessage());
      throw new DatabaseConnectionFailedException();
    }
  }

  public LoginDetails getUserDetails (String email) throws
          DatabaseConnectionFailedException, CouldNotFindUserAccountException {

    String selectStatement = "SELECT firstName, lastName, accessRight " +
            "FROM `Users` WHERE email = ?";
    try (Connection c = databaseConnector.getConnection();
            PreparedStatement st = c.prepareStatement(selectStatement)) {

      st.setString(1, email);

      ResultSet rs = st.executeQuery();

      while (rs.next()) {
        return new LoginDetails(
                email,
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("accessRight")
        );
      }

      throw new CouldNotFindUserAccountException();

    } catch (SQLException e) {
      System.err.println(e.getMessage());
      throw new DatabaseConnectionFailedException();
    }
  }
}
