package org.kainos.ea.resources;

import org.junit.jupiter.api.Test;
import org.kainos.ea.api.JobService;
import org.kainos.ea.cli.JobResponse;
import org.kainos.ea.client.CantGetAnyRolesException;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobControllerTest {
  JobService jobService = Mockito.mock(JobService.class);
  JobController jobRoleController = new JobController(jobService);

  @Test
  void getJobs_shouldReturn200Response_whenJobServiceDoesNotThrowException() throws CantGetAnyRolesException, SQLException {
    int expectedStatusCode = 200;

    JobResponse job1 = new JobResponse(1, "Testing Engineer", "Associate", "Engineering", "https://example.com");
    JobResponse job2 = new JobResponse(1, "Testing Engineer 2", "Associate", "Engineering", "https://example.com");
    JobResponse job3 = new JobResponse(1, "Testing Engineer 3", "Associate", "Engineering", "https://example.com");


    List<JobResponse> jobs = new ArrayList<>();
    jobs.add(job1);
    jobs.add(job2);
    jobs.add(job3);

    Mockito.when(jobService.getAllJobs()).thenReturn(jobs);

    Response response = jobRoleController.getJobs();

    assertEquals(response.getStatus(), expectedStatusCode);
    assertEquals(response.getEntity(), jobs);
  }

  @Test
  void getJobRoles_shouldReturn500Response_whenJobRoleServiceThrowsSQLException() throws CantGetAnyRolesException, SQLException {
    int expectedStatusCode = 500;

    Mockito.doThrow(SQLException.class).when(jobService).getAllJobs();

    Response response = jobRoleController.getJobs();

    assertEquals(response.getStatus(), expectedStatusCode);
  }
}
