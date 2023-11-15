package org.kainos.ea.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobService;
import org.kainos.ea.cli.JobCapabilityRequest;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobCapabilityDao;
import org.kainos.ea.db.JobDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JobCapabilityServiceTest {

  JobCapabilityDao jobCapabilityDao = Mockito.mock(JobCapabilityDao.class);
  DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

  JobService jobService = new JobService(jobCapabilityDao, databaseConnector);

  JobCapabilityRequest jobCapabilityRequest = new JobCapabilityRequest(
          "Test Capability"
  );
}
