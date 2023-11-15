package org.kainos.ea.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.LoginDetails;
import org.kainos.ea.client.CouldNotGenerateKeyPairException;
import org.kainos.ea.client.JWTCouldNotBeCreatedException;
import org.kainos.ea.client.JWTExpiredException;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AuthenticationUtilTest {

  @BeforeEach
  public void init() {

    try { // Want to run before each test to use new instance.
      KeyGeneratorUtil.setInstance(new KeyGeneratorUtil());
    } catch (CouldNotGenerateKeyPairException e) {
      Assertions.fail("Setup Failed!");
    }
  }

  @Test
  void validateJWT_shouldReturnCorrectDetails_whenValidJWTIsPassedIn () throws JWTCouldNotBeCreatedException, JWTExpiredException {

    LoginDetails loginDetails = new LoginDetails(
            "email-loginDetails",
            "firstname-loginDetails",
            "lastname-loginDetails"
    );

    String token = JwtGeneratorUtil.generateJWT(loginDetails, Clock.systemDefaultZone());

    LoginDetails loginDetailsReturned = AuthenticationUtil.validateJwt(token);

    Assertions.assertNotNull(loginDetailsReturned);

  }

  @Test
  void validateJWT_shouldReturnJWTExpiredException_whenInvalidJWTIsPassedIn () throws JWTCouldNotBeCreatedException {
    // [Goal] - Check Expiry OF JWT Token

    String instantExpected = "2014-12-22T10:15:30Z";
    Clock clock = Clock.fixed(Instant.parse(instantExpected), ZoneId.of("UTC"));

    LoginDetails loginDetails = new LoginDetails(
            "email-loginDetails",
            "firstname-loginDetails",
            "lastname-loginDetails"
    );

    String tokenExpired = JwtGeneratorUtil.generateJWT(loginDetails, clock);

    assertThrows(JWTExpiredException.class,
            () -> AuthenticationUtil.validateJwt(tokenExpired));

  }

}
