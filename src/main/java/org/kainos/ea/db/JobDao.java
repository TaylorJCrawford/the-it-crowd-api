package org.kainos.ea.db;

import org.kainos.ea.api.JobRequest;
import org.kainos.ea.cli.Job;
import org.kainos.ea.client.FailedToDeleteException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobDao {

  public int createJobRole(Connection c, JobRequest jobRequest) throws SQLException {
    String query = "INSERT INTO JobRoles (jobName, jobCapabilityId, bandId, jobSpecUrl) " +
            "VALUES (?, ?, ?, ?);";

    PreparedStatement st = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

    st.setString(1, jobRequest.getJobName());
    st.setInt(2, jobRequest.getJobCapabilityId());
    st.setInt(3, jobRequest.getBandId());
    st.setString(4, jobRequest.getJobSpecSharepointLink());

    st.executeUpdate();

    ResultSet rs = st.getGeneratedKeys();

    if (rs.next()) {
      return rs.getInt(1);
    }

    return 0;
  }

  public List<Job> getAllJobs(Connection c) throws SQLException {

    try (Statement st = c.createStatement()) {

      String queryString = "SELECT jobId, jobName, bandName, jobCapabilityName, jobSpecUrl " +
                "FROM JobRoles LEFT JOIN Bands USING(bandId) " +
                "LEFT JOIN JobCapabilities USING(jobCapabilityId);";

      ResultSet rs = st.executeQuery(queryString);

      List<Job> jobs = new ArrayList<>();

      while (rs.next()) {
        Job job = new Job(
                rs.getInt("jobId"),
                rs.getString("jobName"),
                rs.getString("jobCapabilityName"),
                rs.getString("jobSpecUrl"),
                rs.getString("bandName")
        );
        jobs.add(job);
      }
      return jobs;
    }
  }

  public Job getJobById(int id, Connection c) throws SQLException {
    String sql = "SELECT jobId, jobName, bandName, jobCapabilityName, jobSpecUrl " +
            "FROM JobRoles LEFT JOIN Bands USING(bandId) " +
            "LEFT JOIN JobCapabilities USING(jobCapabilityId)" +
            "WHERE jobId = ?;";


    try (PreparedStatement st = c.prepareStatement(sql)) {
      st.setInt(1, id);
      ResultSet rs = st.executeQuery();
      if (rs.next()) {
        return new Job(
                rs.getInt("jobId"),
                rs.getString("jobName"),
                rs.getString("jobCapabilityName"),
                rs.getString("jobSpecUrl"),
                rs.getString("bandName")
        );
      }
      return null;
    }
  }

  public void deleteJobRole(int id, Connection c) throws SQLException, FailedToDeleteException {
    String sql = "DELETE FROM JobRoles WHERE jobId = ?;";
    PreparedStatement st = c.prepareStatement(sql);
    st.setInt(1, id);

    int affectedRows = st.executeUpdate();

    if (affectedRows == 0) {
      throw new FailedToDeleteException("Unable to delete job role.");
    }
  }
}