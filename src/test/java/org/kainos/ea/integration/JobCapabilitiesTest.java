package org.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardTheITCrowdServiceApplication;
import org.kainos.ea.DropwizardTheITCrowdServiceConfiguration;
import org.kainos.ea.cli.Job;
import org.kainos.ea.cli.JobCapabilityRequest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobCapabilitiesTest {

  private static final String host = System.getenv("BASE_URL");

  static final DropwizardAppExtension<DropwizardTheITCrowdServiceConfiguration> APP = new DropwizardAppExtension<>(
          DropwizardTheITCrowdServiceApplication.class, null,
          new ResourceConfigurationSourceProvider()
  );

  @Test
  void getJobAllCapabilities_shouldReturnListOfJobCapabilities()  {

    // Construct the target URL with the host URL
    String targetUrl = host + "/api/job-capabilities";

    List<Job> response = APP.client().target(targetUrl)
            .request()
            .get(List.class);

    Assertions.assertTrue(response.size() > 0);
    // Assert that the response size is equal to the expected value
    int expectedSize = 14;
    assertEquals(expectedSize, response.size());
  }

  @Test
  void getJobCapabilityById_shouldReturnListOfJobCapability()  {
    int id = 1;
    JobCapabilityRequest expected = new JobCapabilityRequest("Platforms");

    // Construct the target URL with the host URL
    String targetUrl = host + "/api/job-capabilities/" + id;

    // Use a generic type to correctly deserialize the response
    JobCapabilityRequest response = APP.client().target(targetUrl)
            .request()
            .get(JobCapabilityRequest.class);

    // Assertions
    assertNotNull(response);
    assertEquals(expected.getJobCapabilityName(), response.getJobCapabilityName());
  }

}
