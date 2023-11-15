package org.kainos.ea.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.LoginDetails;
import org.kainos.ea.client.CouldNotGenerateKeyPairException;
import org.kainos.ea.client.JWTCouldNotBeCreatedException;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Clock;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JwtGeneratorUtilTest {

  LoginDetails loginDetails = new LoginDetails(
          "email-loginDetails",
          "firstname-loginDetails",
          "lastname-loginDetails",
          "View-loginDetails"
  );

  LoginDetails loginDetailsInvalid = null;

  @Nested
  class TestCasesWithPrecondition1 {
    @BeforeEach
    public void init() {
      // Want to run before each test to use new instance.
      try {
        KeyGeneratorUtil.setInstance(new KeyGeneratorUtil());
      } catch (CouldNotGenerateKeyPairException e) {
        Assertions.fail("Setup Failed!");
      }
    }

    @Test
    void createJwt_shouldReturnValidJwtTokenThatCanBeRead_whenGenerateJwtIsCalled() {
      // [Goal] - Able to generate JWT tokens with correct information.

      try {
        String jwtToken = JwtGeneratorUtil.generateJWT(loginDetails, Clock.systemDefaultZone());

        // Check That JWT Can Be Verified.
        KeyGeneratorUtil keyGeneratorUtil = KeyGeneratorUtil.getInstance();
        RSAPublicKey rPubKey = keyGeneratorUtil.getrPubKey();
        RSAPrivateKey rPriKey = keyGeneratorUtil.getrPriKey();

        Algorithm algorithm = Algorithm.RSA256(rPubKey, rPriKey);

        Verification verifier = JWT.require(algorithm);
        DecodedJWT jwtDecoded = verifier.build().verify(jwtToken);

        // Check Payload Of JWT
        String expiry = jwtDecoded.getClaim("expiry").toString();

        long expiryLong = Long.parseLong(expiry);
        long currentUnixTime = System.currentTimeMillis() / 1000L;

        if (expiryLong <= currentUnixTime) {
          // Check JWT Has Not Expired.
          Assertions.fail("Expiry Not Set Correctly!");
        }

        String firstName = jwtDecoded.getClaim("firstName").asString();
        Assertions.assertEquals(firstName, loginDetails.getFirstname());

        String lastName = jwtDecoded.getClaim("lastName").asString();
        Assertions.assertEquals(lastName, loginDetails.getLastname());

        String email = jwtDecoded.getClaim("email").asString();
        Assertions.assertEquals(email, loginDetails.getEmail());

        String accessRight = jwtDecoded.getClaim("accessRight").asString();
        Assertions.assertEquals(accessRight, loginDetails.getAccessRight());

      } catch (JWTCouldNotBeCreatedException e) {
        Assertions.fail("JWT Could Not Be Created!");
      }
    }

    @Test
    void createJwt_shouldReturnJWTCouldNotBeCreatedException_whenGenerateJwtIsCalled() {
      // [Goal] - Not Able to generate JWT without user details

      assertThrows(JWTCouldNotBeCreatedException.class,
              () -> JwtGeneratorUtil.generateJWT(loginDetailsInvalid, Clock.systemDefaultZone()));
    }
  }

  @Test
  void notAbleToCreateJWT_shouldReturnJWTCouldNotBeCreatedException_whenKeysAreNotGenerated() {
    // [Goal] - Not Able to generate JWT without tokens Pub and Pri

    assertThrows(JWTCouldNotBeCreatedException.class,
            () -> JwtGeneratorUtil.generateJWT(loginDetailsInvalid, Clock.systemDefaultZone()));

  }
}
