package org.kainos.ea.api;

import org.kainos.ea.cli.Job;
import org.kainos.ea.client.CantGetAnyRolesException;
import org.kainos.ea.client.DoesNotExistException;
import org.kainos.ea.client.FailedToDeleteException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobDao;
import java.sql.SQLException;
import java.util.List;

public class JobService {

  private final JobDao jobDao;
  private final DatabaseConnector databaseConnector;

  public JobService(JobDao jobDao, DatabaseConnector databaseConnector) {
    this.jobDao = jobDao;
    this.databaseConnector = databaseConnector;
  }

  public List<Job> getAllJobs() throws SQLException, CantGetAnyRolesException {
    List<Job> jobs = jobDao.getAllJobs(databaseConnector.getConnection());

    if(jobs.isEmpty()){
      throw new CantGetAnyRolesException();
    }
    return jobs;
  }

  public Job getJobById(int id) throws SQLException, CantGetAnyRolesException {
    Job job = jobDao.getJobById(id, databaseConnector.getConnection());
    if(job == null){
      throw new CantGetAnyRolesException();
    }
    return job;
  }

  public void deleteJobRole(int id) throws FailedToDeleteException, DoesNotExistException {
    try {
      if (jobDao.getJobById(id, databaseConnector.getConnection()) == null) {
        throw new DoesNotExistException("Specified jobID (" +id +") does not exist.");
      }
      jobDao.deleteJobRole(id, databaseConnector.getConnection());
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }
}
