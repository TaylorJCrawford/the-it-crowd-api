package org.kainos.ea.util;

import org.kainos.ea.client.CouldNotGenerateKeyPairException;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class KeyGeneratorUtil {

  private static KeyGeneratorUtil instance;
  private RSAPublicKey rPubKey;
  private RSAPrivateKey rPriKey;

  public static KeyGeneratorUtil getInstance() {
    return instance;
  }

  public KeyGeneratorUtil() throws CouldNotGenerateKeyPairException {

    Object[] keys = null;

    // Generate New Keys
    keys = generateNewKeys();

    // Set Object Instance With Keys
    this.setrPubKey((RSAPublicKey) keys[0]);
    this.setrPriKey((RSAPrivateKey) keys[1]);

    // Use Setters to Set Keys
    setrPubKey((RSAPublicKey) keys[0]);
    setrPriKey((RSAPrivateKey) keys[1]);
  }

  public static void setInstance(KeyGeneratorUtil keyGeneratorUtil) {
    instance = keyGeneratorUtil;
  }

  public RSAPublicKey getrPubKey() {
    return rPubKey;
  }

  public void setrPubKey(RSAPublicKey rPubKey) {
    this.rPubKey = rPubKey;
  }

  public RSAPrivateKey getrPriKey() {
    return rPriKey;
  }

  public void setrPriKey(RSAPrivateKey rPriKey) {
    this.rPriKey = rPriKey;
  }

  public Object[]  generateNewKeys () throws CouldNotGenerateKeyPairException {
    try {
      KeyPairGenerator kpg = null;
      kpg = KeyPairGenerator.getInstance("RSA");

      kpg.initialize(1024);
      KeyPair kp = kpg.generateKeyPair();

      RSAPublicKey rPubKey = (RSAPublicKey) kp.getPublic();
      RSAPrivateKey rPriKey = (RSAPrivateKey) kp.getPrivate();

      Object[] keys = new Object[2];
      keys[0] = rPubKey;
      keys[1] = rPriKey;

      return keys;

    } catch (NoSuchAlgorithmException e) {
      throw new CouldNotGenerateKeyPairException();
    }

  }

}
