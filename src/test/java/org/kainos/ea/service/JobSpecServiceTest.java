package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.client.JobSpecsNotFoundException;
import org.kainos.ea.db.DatabaseConnector;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobSpecServiceTest {
    JobSpecDao jobSpecDao = Mockito.mock(JobSpecDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    JobSpecService jobSpecService = new JobSpecService(jobSpecDao, databaseConnector);

    Connection conn;

    @Test
    void getJobSpec_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException, JobSpecsNotFoundException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        int id = 100;
        Mockito.when(jobSpecDao.getJobSpec(id, conn)).thenThrow(SQLException.class);
        assertThrows(SQLException.class,
                () -> jobSpecService.getJobSpec(id));
    }

    @Test
    void getJobSpec_shouldReturnJobSpec_whenDaoReturnsJobSpec() throws SQLException, JobSpecsNotFoundException {
        JobSpec expectedResult = new JobSpec();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        int id = 5;
        Mockito.when(jobSpecDao.getJobSpec(id, conn)).thenReturn(expectedResult);
        JobSpec result = jobSpecService.getJobSpec(id);
        assertEquals(result, expectedResult);
    }
    @Test
    void getJobSpecs_shouldReturnJobSpecs_whenDaoReturnsJobSpecs() throws SQLException, JobSpecsNotFoundException {
        List<JobSpec> expectedResult = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobSpecDao.getAllJobSpecs(conn)).thenReturn(expectedResult);
        List<JobSpec> result = jobSpecService.getAllJobSpecs();
        assertEquals(result, expectedResult);
    }
}
