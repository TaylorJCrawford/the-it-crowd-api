package org.kainos.ea.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.kainos.ea.cli.LoginDetails;
import org.kainos.ea.client.JWTCouldNotBeCreatedException;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtGeneratorUtil {

  public static String generateJWT(LoginDetails loginDetails, Clock clock) throws JWTCouldNotBeCreatedException {

    try {

      if (loginDetails != null) {

        KeyGeneratorUtil keyGeneratorUtil = KeyGeneratorUtil.getInstance();
        RSAPublicKey rPubKey = keyGeneratorUtil.getrPubKey();
        RSAPrivateKey rPriKey = keyGeneratorUtil.getrPriKey();

        Instant instant = Instant.now(clock).plus(1, ChronoUnit.HOURS);
        Date expiry = Date.from(instant);

        Algorithm algorithm = Algorithm.RSA256(rPubKey, rPriKey);
        String token = JWT.create()
                .withClaim("firstName", loginDetails.getFirstname())
                .withClaim("lastName", loginDetails.getLastname())
                .withClaim("email", loginDetails.getEmail())
                .withClaim("expiry", new java.sql.Timestamp(expiry.getTime()))
                .sign(algorithm);

        if (token != null) {
          return token;
        }

        // Should Never Get Here. -> Fail Safe Protection.
        throw new JWTCouldNotBeCreatedException();
      }
    } catch (Exception e) {
      throw new JWTCouldNotBeCreatedException();
    }

    // Should Never Get Here. -> Fail Safe Protection.
    throw new JWTCouldNotBeCreatedException();
  }
}
