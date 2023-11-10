package org.kainos.ea.integration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import sun.tools.jconsole.JConsole;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobIntegrationTest {

    private static final String host = System.getenv("BASE_URL");

    static final DropwizardAppExtension<DropwizardTheITCrowdServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardTheITCrowdServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getJobRoles_shouldReturnListOfJobRoles() throws UnsupportedEncodingException {

        System.out.println("---------------------> YO ITS HERE -->"+host);
        // Construct the target URL with the host URL
        String targetUrl = (host + "/api/jobs");

        List<Job> response = APP.client().target(targetUrl)
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
        // Assert that the response size is equal to the expected value
        // You can change the expected value according to your test data
        int expectedSize = 5;
        Assertions.assertEquals(expectedSize, response.size());
    }


}
