package org.kainos.ea.controller;

import org.junit.jupiter.api.Test;
import org.kainos.ea.api.JobService;
import org.kainos.ea.cli.JobsResponse;
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

        JobsResponse job1 = new JobsResponse(1,"Testing Engineer");
        JobsResponse job2 = new JobsResponse(2,"Testing2 Engineer");
        JobsResponse job3 = new JobsResponse(3,"Testing3 Engineer");

        List<JobsResponse> jobs = new ArrayList<>();
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
