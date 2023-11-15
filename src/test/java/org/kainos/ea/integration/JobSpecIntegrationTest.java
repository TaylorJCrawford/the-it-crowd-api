package org.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardTheITCrowdServiceApplication;
import org.kainos.ea.DropwizardTheITCrowdServiceConfiguration;
import org.kainos.ea.cli.JobSpec;
import org.kainos.ea.db.JobSpecDao;

import javax.ws.rs.core.Response;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobSpecIntegrationTest {

    private static final String host = System.getenv("BASE_URL");
    static final DropwizardAppExtension<DropwizardTheITCrowdServiceConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardTheITCrowdServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getJobSpecs_shouldReturnListOfJobSpecs() {
       String targetUrl = host + "/api/job-specs";
        List<JobSpec> response = APP.client().target(targetUrl)
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }

    @Test
    void getJobSpec_shouldReturnJobSpec() {
        String targetUrl = host + "/api/job-specs/8";
        Response response = APP.client().target(targetUrl)
                .request()
                .get();
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(8, response.readEntity(JobSpec.class).getJobSpecID());
    }
    @Test
    void getJobSpec_shouldReturnErrorWhenNotFound() {
        String targetUrl = host + "/api/job-specs/88";
        Response response = APP.client().target(targetUrl)
                .request()
                .get();

        Assertions.assertEquals(400, response.getStatus());
    }
}
