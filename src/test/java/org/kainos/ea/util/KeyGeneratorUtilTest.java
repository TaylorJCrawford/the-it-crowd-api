package org.kainos.ea.util;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kainos.ea.client.CouldNotGenerateKeyPairException;
import static org.hamcrest.CoreMatchers.instanceOf;

public class KeyGeneratorUtilTest {

  @BeforeEach
  public void init() {

    try { // Want to run before each test to use new instance.
      KeyGeneratorUtil.setInstance(new KeyGeneratorUtil());
    } catch (CouldNotGenerateKeyPairException e) {
      Assertions.fail("Setup Failed!");
    }
  }

  @Test
  void getKeyGeneratorInstance_shouldReturnKeyGenInstance_whenKeyGeneratorGetMethodIsCalled() {
    // [Goal] - Able to get instance of keyGen.
    KeyGeneratorUtil keyInstance = KeyGeneratorUtil.getInstance();
    MatcherAssert.assertThat(keyInstance, instanceOf(KeyGeneratorUtil.class));
  }

  @Test
  void getKeysFromInstance_shouldReturnValidPrivateAndPublicKeys_whenGettersAreCalled() {
    // [Goal] - Able to get public and priv key.
    KeyGeneratorUtil keyInstance = KeyGeneratorUtil.getInstance();

    RSAPublicKey pubKey = keyInstance.getrPubKey();
    Assertions.assertNotNull(pubKey);
    Assertions.assertEquals(1024, pubKey.getModulus().bitLength());

    RSAPrivateKey priKey = keyInstance.getrPriKey();
    Assertions.assertNotNull(priKey);
    Assertions.assertEquals(1024, priKey.getModulus().bitLength());
  }

  @Test
  void getKeysFromInstancex2_shouldReturnSamePublicAndPrivateKeys_whenGettersAreCalled() {
    // [Goal] - Able to get instance then another instance -> and then get the same keys.
    KeyGeneratorUtil keyInstance = KeyGeneratorUtil.getInstance();

    RSAPublicKey pubKey = keyInstance.getrPubKey();
    Assertions.assertNotNull(pubKey);

    RSAPrivateKey priKey = keyInstance.getrPriKey();
    Assertions.assertNotNull(priKey);

    KeyGeneratorUtil keyInstance2 = KeyGeneratorUtil.getInstance();

    RSAPublicKey pubKey2 = keyInstance2.getrPubKey();
    Assertions.assertNotNull(pubKey2);

    RSAPrivateKey priKey2 = keyInstance2.getrPriKey();
    Assertions.assertNotNull(priKey2);

    Assertions.assertEquals(pubKey, pubKey2);
    Assertions.assertEquals(priKey, priKey2);
  }
}
