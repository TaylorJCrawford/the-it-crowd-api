package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobRequest;
import org.kainos.ea.api.JobService;
import org.kainos.ea.cli.Job;
import org.kainos.ea.client.CantGetAnyRolesException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {

    JobDao jobDao = Mockito.mock(JobDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    Statement createStatement = Mockito.mock(Statement.class);


    JobService jobService = new JobService(jobDao, databaseConnector);

    @Test
    void getAllJobs_shouldReturnListOfJobs() throws SQLException, CantGetAnyRolesException {
        // Arrange
        List<Job> jobList = new ArrayList<>();

        Job job1 = new Job(1, 1, "Software Engineer");
        Job job2 = new Job(2, 2, "Test Engineer");

        jobList.add(job1);
        jobList.add(job2);

        Connection mockConnection = Mockito.mock(Connection.class);

        Mockito.when(databaseConnector.getConnection()).thenReturn(mockConnection);
        Mockito.when(jobDao.getAllJobs(mockConnection)).thenReturn(jobList);

        // Act
        List<Job> result = jobService.getAllJobs();

        // Assert
        assertEquals(jobList, result);
    }

    @Test
    void getAllJobs_shouldThrowExceptionWhenDatabaseConnectionFails() throws SQLException {
        // Arrange
        Connection mockConnection = Mockito.mock(Connection.class);

        Mockito.when(databaseConnector.getConnection()).thenReturn(mockConnection);
        Mockito.when(jobDao.getAllJobs(mockConnection)).thenThrow(SQLException.class);

        // Act & Assert
        assertThrows(SQLException.class, () -> jobService.getAllJobs());
    }

    @Test
    void getAllJobs_shouldThrowExceptionWhenResponseIsNull() throws SQLException {
        // Arrange
        Connection mockConnection = Mockito.mock(Connection.class);

        Mockito.when(databaseConnector.getConnection()).thenReturn(mockConnection);
        Mockito.when(jobDao.getAllJobs(mockConnection)).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(CantGetAnyRolesException.class, () -> jobService.getAllJobs());
    }

    @Test
    void getJobById_shouldReturnJob() throws SQLException, CantGetAnyRolesException {
        int id = 1;
        JobRequest jobRequest = new JobRequest(id, 1, "Software Engineer", "Codes", "https://www.aye.co.uk/");

        Connection mockConnection = Mockito.mock(Connection.class);

        Mockito.when(databaseConnector.getConnection()).thenReturn(mockConnection);
        Mockito.when(jobDao.getJobById(id,mockConnection)).thenReturn(jobRequest);

        // Act
        JobRequest result = jobService.getJobById(id);

        // Assert
        assertEquals(jobRequest, result);
    }

    @Test
    void getJobById_shouldThrowExceptionWhenNotFound() throws SQLException, CantGetAnyRolesException {
        int id = 111;

        Connection mockConnection = Mockito.mock(Connection.class);

        Mockito.when(databaseConnector.getConnection()).thenReturn(mockConnection);
        Mockito.when(jobDao.getJobById(id,mockConnection)).thenReturn(null);

        // Act & Assert
        assertThrows(CantGetAnyRolesException.class, () -> jobService.getJobById(id));
    }
}
