package org.kainos.ea.db;

import org.kainos.ea.cli.Job;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobDao {

  public List<Job> getAllJobs(Connection c) throws SQLException {

    try (Statement st = c.createStatement()) {
      String queryString = "SELECT jobId, jobName, jobSpecUrl, bandName " +
              "FROM JobRoles " +
              "LEFT JOIN Bands USING(bandId);";

      ResultSet rs = st.executeQuery(queryString);
      List<Job> jobs = new ArrayList<>();

      while (rs.next()) {
        Job job = new Job(
                rs.getInt("jobId"),
                rs.getString("jobName"),
                rs.getString("jobSpecUrl"),
                rs.getString("bandName")
        );
        jobs.add(job);
      }
      return jobs;
    }
  }

  public Job getJobById(int id, Connection c) throws SQLException {
    String sql = "SELECT jobId, jobName, jobSpecUrl, bandName FROM JobRoles " +
            "LEFT JOIN Bands USING(bandId)"
            + "WHERE jobId = ?";

    try (PreparedStatement st = c.prepareStatement(sql)) {
      st.setInt(1, id);
      ResultSet rs = st.executeQuery();
      if (rs.next()) {
        return new Job(
                rs.getInt("jobId"),
                rs.getString("jobName"),
                rs.getString("jobSpecUrl"),
                rs.getString("bandName")
        );
      }
      return null;
    }
  }

}
