package org.kainos.ea.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.JobResponse;
import org.kainos.ea.cli.JobRoleResponse;
import org.kainos.ea.client.CantGetAnyRolesException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
    List<JobResponse> jobList = new ArrayList<>();

    JobResponse job1 = new JobResponse(1, "Software Engineer");
    JobResponse job2 = new JobResponse(2, "Test Engineer");

    jobList.add(job1);
    jobList.add(job2);

    Connection mockConnection = Mockito.mock(Connection.class);

    Mockito.when(databaseConnector.getConnection()).thenReturn(mockConnection);
    Mockito.when(jobDao.getAllJobs(mockConnection)).thenReturn(jobList);

    // Act
    List<JobResponse> result = jobService.getAllJobs();

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
    List<String> responsibilities = new ArrayList<>();
    responsibilities.add("uno");

    int id = 1;
    JobRoleResponse job = new JobRoleResponse(id, "Software Engineer", "https://www.sample.co.uk/", responsibilities, "Band 3");

    Connection mockConnection = Mockito.mock(Connection.class);

    Mockito.when(databaseConnector.getConnection()).thenReturn(mockConnection);
    Mockito.when(jobDao.getJobById(id, mockConnection)).thenReturn(job);

    // Act
    JobRoleResponse result = jobService.getJobById(id);

    // Assert
    assertEquals(job, result);
  }

  @Test
  void getJobById_shouldThrowExceptionWhenNotFound() throws SQLException, CantGetAnyRolesException {
    int id = 111;

    Connection mockConnection = Mockito.mock(Connection.class);

    Mockito.when(databaseConnector.getConnection()).thenReturn(mockConnection);
    Mockito.when(jobDao.getJobById(id, mockConnection)).thenReturn(null);

    // Act & Assert
    assertThrows(CantGetAnyRolesException.class, () -> jobService.getJobById(id));
  }
}
