package org.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardTheITCrowdServiceApplication;
import org.kainos.ea.DropwizardTheITCrowdServiceConfiguration;
import org.kainos.ea.cli.LoginRequest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
public class AuthServiceIntegrationTest {

  private static final DropwizardAppExtension<DropwizardTheITCrowdServiceConfiguration> APP = new DropwizardAppExtension<>(
          DropwizardTheITCrowdServiceApplication.class, null,
          new ResourceConfigurationSourceProvider()
  );

  private static final String host = System.getenv("BASE_URL");

  @Test
  void userLogin_shouldReturnValidToken_whenLoginWithValidCredentials() {

    String testUserEmail = System.getenv("TEST_VALID_EMAIL_API");
    String testUserPassword = System.getenv("TEST_VALID_USER_PASSWORD_API");

    LoginRequest loginRequest = new LoginRequest(
            testUserEmail,
            testUserPassword
    );

    Response response  = APP.client().target(host + "/api/auth/login")
            .request()
            .post(Entity.entity(loginRequest, MediaType.APPLICATION_JSON_TYPE));

    Assertions.assertEquals(200,response.getStatus());
    Assertions.assertNotNull(response.readEntity(String.class));

  }

  @Test
  void userLogin_shouldNotReturnToken_whenLoginWithInValidCredentials() {

    String testUserEmail = "user@invalid.com";
    String testUserPassword = "invalidPassword";

    LoginRequest loginRequest = new LoginRequest(
            testUserEmail,
            testUserPassword
    );

    Response response  = APP.client().target(host + "/api/auth/login")
            .request()
            .post(Entity.entity(loginRequest, MediaType.APPLICATION_JSON_TYPE));

    Assertions.assertEquals(403,response.getStatus());
  }
}
