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
import org.kainos.ea.cli.JobRoleResponse;

import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobIntegrationTest {

  private static final String HOST = "http://localHOST:8080";

  static final DropwizardAppExtension<DropwizardTheITCrowdServiceConfiguration> APP = new DropwizardAppExtension<>(
          DropwizardTheITCrowdServiceApplication.class, null,
          new ResourceConfigurationSourceProvider()
  );

  @Test
  void getJobRoles_shouldReturnListOfJobRoles() {
    // Construct the target URL with the HOST URL
    String targetUrl = HOST + "/api/jobs";

    List<JobRoleResponse> response = APP.client().target(targetUrl)
            .request()
            .get(List.class);

    Assertions.assertTrue(response.size() > 0);
  }

  @Test
  void getJobRoleById_shouldReturnJobRequest() {
    int id = 1;
    String targetUrl = HOST + "/api/jobs/" + id;

    JobRoleResponse response = APP.client().target(targetUrl)
            .request()
            .get(JobRoleResponse.class);

    // Check id field on response is = 1.
    Assertions.assertEquals(1, response.getJobId());
  }

  @Test
  void getJobRoleById_shouldReturnErrorWhenNotFound() {
    int id = 111;
    String targetUrl = HOST + "/api/jobs/" + id;

    Response response = APP.client().target(targetUrl)
            .request()
            .get(Response.class);

    // You can change the expected value according to your test data
    Assertions.assertEquals(400, response.getStatus());
  }
}
