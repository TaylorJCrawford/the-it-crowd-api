package org.kainos.ea.integration;

import java.sql.SQLException;
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
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobDao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobIntegrationTest {
  private static final String HOST = "http://localhost:8080";

  private final DatabaseConnector databaseConnector = new DatabaseConnector();

  static final DropwizardAppExtension<DropwizardTheITCrowdServiceConfiguration> APP = new DropwizardAppExtension<>(
          DropwizardTheITCrowdServiceApplication.class, null,
          new ResourceConfigurationSourceProvider()
  );

  @Test
  void getJobRoles_shouldReturnListOfJobRoles() {

    // Construct the target URL with the host URL
    String targetUrl = HOST + "/api/jobs";

    List<Job> response = APP.client().target(targetUrl)
            .request()
            .get(List.class);

    Assertions.assertFalse(response.isEmpty());
    Assertions.assertTrue(response.size() > 0);

  }

  @Test
  void deleteJobRole_shouldDeleteWhenCalled() throws SQLException {
    JobRequest jobRequest = new JobRequest(
            100,
            "Teacher",
            1,
            1,
            "Teacher",
            "www.example.com"
    );

    JobDao dao = new JobDao();
    int returnedId = dao.createJobRole(databaseConnector.getConnection(), jobRequest);

    // Construct the target URL with the host URL
    String targetUrl = HOST + "/api/jobs/" + returnedId;

    System.out.println(returnedId);

    Response response = APP.client().target(targetUrl)
            .request()
            .delete();

    assertEquals(204, response.getStatus());
  }

  @Test
  void deleteJobRole_shouldThrowErrorCode404IfNotFound() throws SQLException {
    // Construct the target URL with the host URL
    String targetUrl = HOST + "/api/jobs/0";

    Response response = APP.client().target(targetUrl)
            .request()
            .delete();

    assertEquals(404, response.getStatus());
  }

  @Test
  void getJobRoleById_shouldReturnJobRequest() {
    int id = 1;

    String targetUrl = HOST + "/api/jobs/" + id;

    Job response = APP.client().target(targetUrl)
            .request()
            .get(Job.class);

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