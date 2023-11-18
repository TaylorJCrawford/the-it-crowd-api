package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobService;
import org.kainos.ea.cli.Job;
import org.kainos.ea.cli.JobResponsibility;
import org.kainos.ea.client.CantGetAnyRolesException;
import org.kainos.ea.client.CouldNotGetJobResponsibilityException;
import org.kainos.ea.client.NoJobResponsibilityStoredForJobRoleException;
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

        Job job1 = new Job(1, "Job1");
        Job job2 = new Job(2, "Job2");

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
  void getJobResponsibility_shouldReturnJobResponsibilityObject_whenValidJobIdIsPassedIn() throws SQLException,
          CouldNotGetJobResponsibilityException, NoJobResponsibilityStoredForJobRoleException {
    Connection mockConnection = Mockito.mock(Connection.class);

    JobResponsibility jobResponsibility = new JobResponsibility(
            1,
            "Example Responsibility Text Body",
            "Example Responsibility Text Points"
    );

    int searchJobId = 1;

    Mockito.when(databaseConnector.getConnection()).thenReturn(mockConnection);
    Mockito.when(jobDao.getJobResponsibility(mockConnection, searchJobId)).thenReturn(jobResponsibility);

    JobResponsibility result = jobService.getJobResponsibility(searchJobId);

    assertEquals(jobResponsibility, result);
  }

  @Test
  void getJobResponsibility_shouldThrowNoJobResponsibilityStoredForJobRoleException_whenInvalidJobIdIsPassedIn() throws
          CouldNotGetJobResponsibilityException, SQLException {

    int searchJobId = -1;

    Connection mockConnection = Mockito.mock(Connection.class);

    Mockito.when(databaseConnector.getConnection()).thenReturn(mockConnection);
    Mockito.when(jobDao.getJobResponsibility(mockConnection, searchJobId)).thenReturn(null);

    assertThrows(NoJobResponsibilityStoredForJobRoleException.class, () -> jobService.getJobResponsibility(searchJobId));
  }
}
