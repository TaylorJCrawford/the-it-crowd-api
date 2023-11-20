package org.kainos.ea.integration;

import java.util.List;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardTheITCrowdServiceApplication;
import org.kainos.ea.DropwizardTheITCrowdServiceConfiguration;
import org.kainos.ea.cli.Job;

import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobIntegrationTest {

  private static final String host = System.getenv("BASE_URL");

  static final DropwizardAppExtension<DropwizardTheITCrowdServiceConfiguration> APP = new DropwizardAppExtension<>(
          DropwizardTheITCrowdServiceApplication.class, null,
          new ResourceConfigurationSourceProvider()
  );

  @Test
  void getJobRoles_shouldReturnListOfJobRoles() {
    System.out.println("LOGGING HOST --> " + host);
    // Construct the target URL with the host URL
    String targetUrl = host + "/api/jobs";

    List<Job> response = APP.client().target(targetUrl)
            .request()
            .get(List.class);

    Assertions.assertTrue(response.size() > 0);
    // Assert that the response size is equal to the expected value
    // You can change the expected value according to your test data
    Assertions.assertEquals(7, response.size());
  }

  @Test
  void getJobRoleById_shouldReturnJobRequest() {
    int id = 1;
    String targetUrl = host + "/api/jobs/" + id;

    Job response = APP.client().target(targetUrl)
            .request()
            .get(Job.class);

    // You can change the expected value according to your test data
    Assertions.assertEquals(1, response.getJobId());
  }

  @Test
  void getJobRoleById_shouldReturnErrorWhenNotFound() {
    int id = 111;
    String targetUrl = host + "/api/jobs/" + id;

    Response response = APP.client().target(targetUrl)
            .request()
            .get(Response.class);

    // You can change the expected value according to your test data
    Assertions.assertEquals(400, response.getStatus());
  }
}
