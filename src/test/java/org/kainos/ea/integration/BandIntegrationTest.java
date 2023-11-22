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

import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class BandIntegrationTest {

    private static final String HOST = "http://localhost:8080";

    static final DropwizardAppExtension<DropwizardTheITCrowdServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardTheITCrowdServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getBands_shouldReturnListOfBands()  {

        // Construct the target URL with the host URL
        String targetUrl = HOST + "/api/bands";

        List<Job> response = APP.client().target(targetUrl)
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
        // Assert that the response size is equal to the expected value
        // You can change the expected value according to your test data
        int expectedSize = 8;
        Assertions.assertEquals(expectedSize, response.size());
    }

}