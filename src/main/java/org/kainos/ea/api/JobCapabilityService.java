package org.kainos.ea.api;

import org.kainos.ea.cli.JobCapability;
import org.kainos.ea.cli.JobCapabilityRequest;
import org.kainos.ea.client.CouldNotGetJobCapabilitiesException;
import org.kainos.ea.client.JobCapabilityDoesNotExist;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobCapabilityDao;
import java.sql.SQLException;
import java.util.List;

public class JobCapabilityService {

  private final JobCapabilityDao jobCapabilityDao;
  private final DatabaseConnector databaseConnector;

  public JobCapabilityService(JobCapabilityDao jobCapabilityDao, DatabaseConnector databaseConnector) {
    this.jobCapabilityDao = jobCapabilityDao;
    this.databaseConnector = databaseConnector;
  }

  public List<JobCapability> getAllJobCapabilities() throws SQLException, CouldNotGetJobCapabilitiesException {
    List<JobCapability> jobCapabilities = jobCapabilityDao.getAllJobCapabilities(databaseConnector.getConnection());

    if(jobCapabilities.isEmpty()){
      throw new CouldNotGetJobCapabilitiesException();
    }
    return jobCapabilities;
  }

  public JobCapabilityRequest getJobCapability(int jobCapabilityId) throws SQLException, JobCapabilityDoesNotExist {
    JobCapabilityRequest jobCapabilityRequest = jobCapabilityDao.getJobCapability(jobCapabilityId, databaseConnector.getConnection());

    if (jobCapabilityRequest ==null){
      throw new JobCapabilityDoesNotExist();
    }

    return jobCapabilityRequest;
  }

}
