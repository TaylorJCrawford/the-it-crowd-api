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

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobIntegrationTest {

    static final DropwizardAppExtension<DropwizardTheITCrowdServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardTheITCrowdServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getJobRoles_shouldReturnListOfJobRoles() {

        // Read the host URL from the "DB_HOST" environment variable
        String host = System.getenv("DB_HOST");

        // Ensure the "DB_HOST" environment variable is set
        if (host == null) {
            throw new IllegalArgumentException("Missing Environment Variable DB_HOST.");
        }

        // Construct the target URL with the host URL
        String targetUrl = "http://" + host + "/api/jobs";

        List<Job> response = APP.client().target(targetUrl)
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }


}
