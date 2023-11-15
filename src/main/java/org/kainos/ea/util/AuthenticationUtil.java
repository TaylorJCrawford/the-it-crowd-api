package org.kainos.ea.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.kainos.ea.cli.LoginDetails;
import org.kainos.ea.client.JWTExpiredException;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class AuthenticationUtil {

  public static LoginDetails validateJwt(String token) throws JWTExpiredException {

    KeyGeneratorUtil keyGeneratorUtil = KeyGeneratorUtil.getInstance();
    RSAPublicKey rPubKey = keyGeneratorUtil.getrPubKey();
    RSAPrivateKey rPriKey = keyGeneratorUtil.getrPriKey();

    Algorithm algorithm = Algorithm.RSA256(rPubKey, rPriKey);

    Verification verifier = JWT.require(algorithm);
    DecodedJWT jwt = verifier.build().verify(token);

    // Check JWT Has Not Expired.
    String expiry = jwt.getClaim("expiry").toString();

    long expiryLong =  Long.parseLong(expiry);
    long currentUnixTime = System.currentTimeMillis() / 1000L;

    if (expiryLong <= currentUnixTime) {
      System.err.println("Token Has Expired!");
      throw new JWTExpiredException();
    }

    LoginDetails loginDetails = new LoginDetails(
            jwt.getClaim("email").toString(),
            jwt.getClaim("firstName").toString(),
            jwt.getClaim("lastName").toString(),
            jwt.getClaim("accessRight").toString()
    );

    return loginDetails;
  }
}
