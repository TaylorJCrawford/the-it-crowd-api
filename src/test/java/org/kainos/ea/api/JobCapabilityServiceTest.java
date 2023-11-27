package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobCapabilityService;
import org.kainos.ea.cli.JobCapability;
import org.kainos.ea.cli.JobCapabilityRequest;
import org.kainos.ea.client.CouldNotGetJobCapabilitiesException;
import org.kainos.ea.client.JobCapabilityDoesNotExist;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobCapabilityDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JobCapabilityServiceTest {

  JobCapabilityDao jobCapabilityDao = Mockito.mock(JobCapabilityDao.class);
  DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
  JobCapabilityService jobCapabilityService = new JobCapabilityService(jobCapabilityDao, databaseConnector);
  List<JobCapability> jobCapabilities;
  Connection conn;

  @Test
  void getAllJobCapabilities_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException {
    Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
    Mockito.when(jobCapabilityDao.getAllJobCapabilities(conn)).thenThrow(SQLException.class);

    assertThrows(SQLException.class,
            () -> jobCapabilityService.getAllJobCapabilities());
  }

  @Test
  void getAllJobCapabilities_shouldThrowCouldNotGetJobCapabilitiesException_whenQueryResultsEmpty() throws CouldNotGetJobCapabilitiesException, SQLException {
    Mockito.when(databaseConnector.getConnection()).thenReturn(conn);

    // Mocking the jobCapabilityDao.getAllJobCapabilities to return an empty list
    Mockito.when(jobCapabilityDao.getAllJobCapabilities(conn)).thenReturn(Collections.emptyList());

    assertThrows(CouldNotGetJobCapabilitiesException.class,
            () -> jobCapabilityService.getAllJobCapabilities());
  }

  @Test
  void getAllJobCapabilities_shouldReturnJobCapabilitiesList_whenDaoReturnsJobCapabilities() throws SQLException, CouldNotGetJobCapabilitiesException {
    // create array list and populate with job Capabilities
    jobCapabilities = new ArrayList<>();
    JobCapability jobCapability1 = new JobCapability(1, "Test 1");
    JobCapability jobCapability2 = new JobCapability(2, "Test 2");
    jobCapabilities.add(jobCapability1);
    jobCapabilities.add(jobCapability2);

    // Mocking the database connection and jobCapabilityDao
    Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
    Mockito.when(jobCapabilityDao.getAllJobCapabilities(conn)).thenReturn(jobCapabilities);

    // Perform the actual method call
    List<JobCapability> result = jobCapabilityService.getAllJobCapabilities();

    // Assertions
    assertNotNull(result);
    assertEquals(jobCapabilities.size(), result.size());
    assertEquals(jobCapability1, result.get(0));
    assertEquals(jobCapability2, result.get(1));
  }


  @Test
  void getJobCapability_shouldReturnJobCapabilityRequest_whenDaoReturnsJobCapability() throws SQLException, JobCapabilityDoesNotExist {

    JobCapabilityRequest expectedResult = new JobCapabilityRequest(
            "Test Capability"
    );

    Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
    int id = 1;
    Mockito.when(jobCapabilityDao.getJobCapability(id,conn)).thenReturn(expectedResult);

    JobCapabilityRequest result = jobCapabilityService.getJobCapability(id);
    assertEquals(expectedResult,result);
  }

  @Test
  void getJobCapability_shouldReturnNull_whenResultSetEmpty() throws SQLException {
    Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
    int id = 1;
    Mockito.when(jobCapabilityDao.getJobCapability(id,conn)).thenThrow(SQLException.class);

    assertThrows(SQLException.class,
            () -> jobCapabilityService.getJobCapability(id));
  }

  @Test
  void getJobCapability_shouldThrowJobCapabilityDoesNotExist_whenDaoReturnsNull() throws SQLException, JobCapabilityDoesNotExist {
    Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
    int id = -1;
    // return a null value
    Mockito.when(jobCapabilityDao.getJobCapability(id,conn)).thenReturn(null);

    assertThrows(JobCapabilityDoesNotExist.class,
            () -> jobCapabilityService.getJobCapability(id));
  }

  }


