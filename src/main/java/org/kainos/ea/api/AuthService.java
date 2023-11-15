package org.kainos.ea.api;

import com.password4j.Password;
import org.kainos.ea.cli.LoginDetails;
import org.kainos.ea.cli.LoginRequest;

import org.kainos.ea.client.CouldNotGeneratePasswordHashException;
import org.kainos.ea.client.CouldNotStoreNewJWTException;
import org.kainos.ea.client.DatabaseConnectionFailedException;
import org.kainos.ea.client.JWTCouldNotBeCreatedException;
import org.kainos.ea.client.InvalidLoginAttemptException;
import org.kainos.ea.client.CouldNotFindUserAccountException;

import org.kainos.ea.db.AuthDAO;
import org.kainos.ea.util.JwtGeneratorUtil;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.Clock;

public class AuthService {

  public String userLogin(LoginRequest loginRequest, AuthDAO authDAO) throws
          SQLException, NoSuchAlgorithmException, DatabaseConnectionFailedException,
          InvalidLoginAttemptException, CouldNotFindUserAccountException, JWTCouldNotBeCreatedException,
          CouldNotStoreNewJWTException, CouldNotGeneratePasswordHashException {

    // Check login details are correct
    LoginRequest userInfo = authDAO.getUserPasswordHash(loginRequest);

    boolean verified = Password.check(userInfo.getPassword(), userInfo.getPasswordHash()).withArgon2();

    if (!verified) {
      System.err.println("Error: Hash Passwords Are Not Equal.");
      throw new InvalidLoginAttemptException();
    }

    // Get further user account details
    LoginDetails loginDetails = authDAO.getUserDetails(loginRequest.getEmail());

    // Generate New JWT
    return JwtGeneratorUtil.generateJWT(loginDetails, Clock.systemDefaultZone());
  }
}
