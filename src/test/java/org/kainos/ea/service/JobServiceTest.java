package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobService;
import org.kainos.ea.cli.Job;
import org.kainos.ea.client.CantGetAnyRolesException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {

    JobDao jobDao = Mockito.mock(JobDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    JobService jobService = new JobService(jobDao, databaseConnector);

    @Test
    void getAllJobs_shouldReturnListOfJobs() throws SQLException, CantGetAnyRolesException {
        // Arrange
        List<Job> jobList = new ArrayList<>();

        Connection mockConnection = Mockito.mock(Connection.class);

        Mockito.when(databaseConnector.getConnection()).thenReturn(mockConnection);
        Mockito.when(jobDao.getAllJobs(mockConnection)).thenReturn(jobList);

        // Act
        List<Job> result = jobService.getAllJobs();

        // Assert
        assertEquals(jobList, result);
    }

    @Test
    void getAllJobs_shouldThrowExceptionWhenDatabaseConnectionFails() throws SQLException, CantGetAnyRolesException {
        // Arrange
        Connection mockConnection = Mockito.mock(Connection.class);
        CantGetAnyRolesException exception = new CantGetAnyRolesException();

        Mockito.when(databaseConnector.getConnection()).thenReturn(mockConnection);
        Mockito.when(jobDao.getAllJobs(mockConnection)).thenThrow(exception);

        // Act & Assert
        assertThrows(CantGetAnyRolesException.class, () -> jobService.getAllJobs());
    }
}
