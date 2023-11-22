package org.kainos.ea.api;

import com.password4j.Password;
import org.kainos.ea.cli.LoginDetails;
import org.kainos.ea.cli.LoginRequest;

import org.kainos.ea.client.*;

import org.kainos.ea.db.AuthDao;
import org.kainos.ea.util.JwtGeneratorUtil;

import java.time.Clock;

public class AuthService {
  public String userLogin(LoginRequest loginRequest, AuthDao authDao) throws ActionFailedException, AuthenticationException, JWTCouldNotBeCreatedException {

    // Check login details are correct
    LoginRequest userInfo = authDao.getUserPasswordHash(loginRequest);

    boolean verified = Password.check(userInfo.getPassword(), userInfo.getPasswordHash()).withArgon2();

    if (!verified) {
      System.err.println("Error: Hash Passwords Are Not Equal.");
      throw new AuthenticationException("Password could not be verified.");
    }

    // Get further user account details
    LoginDetails loginDetails = authDao.getUserDetails(loginRequest.getEmail());

    // Generate New JWT
    return JwtGeneratorUtil.generateJWT(loginDetails, Clock.systemDefaultZone());
  }
}
