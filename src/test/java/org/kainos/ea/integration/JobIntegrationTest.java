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
import org.kainos.ea.api.JobRequest;
import org.kainos.ea.cli.Job;
import org.kainos.ea.cli.JobResponsibility;

import javax.ws.rs.core.Response;

import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobIntegrationTest {

    private static final String host = System.getenv("BASE_URL");

    static final DropwizardAppExtension<DropwizardTheITCrowdServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardTheITCrowdServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getJobRoles_shouldReturnListOfJobRoles()  {

        // Construct the target URL with the host URL
        String targetUrl = host + "/api/jobs";

        List<Job> response = APP.client().target(targetUrl)
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
        // Assert that the response size is equal to the expected value
        // You can change the expected value according to your test data
        int expectedSize = 7;
        Assertions.assertEquals(expectedSize, response.size());
    }

    @Test
    void getJobRoleById_shouldReturnJobRequest() {
        int id = 1;
        String targetUrl = host + "/api/jobs/" + id;

        JobRequest response = APP.client().target(targetUrl)
                .request()
                .get(JobRequest.class);

        // You can change the expected value according to your test data
        int expectedId = 1;
        Assertions.assertEquals(expectedId, response.getJobId());
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

    @Test
    void getJobResponsibility_shouldJobResponsibility_whenValidJobIdIsPassedIn() {

        int searchJobId = 1;
        String targetUrl = host + "/api/job-responsibility/" + searchJobId;

        JobResponsibility response = APP.client().target(targetUrl)
                .request()
                .get(JobResponsibility.class);

      Assertions.assertNotNull(response.getResponsibilityTextBody());
    }

    @Test
    void getJobResponsibility_shouldThrowNoJobResponsibilityStoredForJobRoleException_whenInValidJobIdIsPassedIn() {

        int searchJobId = -1;
        String targetUrl = host + "/api/job-responsibility/" + searchJobId;

        Response response = APP.client().target(targetUrl)
                .request()
                .get();

        Assertions.assertEquals(400,response.getStatus());
    }
}
