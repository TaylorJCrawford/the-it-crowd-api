package org.kainos.ea.db;

import org.kainos.ea.cli.Job;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JobDao {

  public List<Job> getAllJobs(Connection c) throws SQLException {
    try (PreparedStatement ps = c.prepareStatement("SELECT jobId, jobName, jobCapabilityName, jobSpecUrl FROM JobRoles" +
            " LEFT JOIN JobCapabilities USING(jobCapabilityId)");
         ResultSet rs = ps.executeQuery()) {

      List<Job> jobs = new ArrayList<>();

      while (rs.next()) {
        Job job = new Job(
                rs.getInt("jobId"),
                rs.getString("jobName"),
                rs.getString("jobCapabilityName"),
                rs.getString("jobSpecUrl")
        );
        jobs.add(job);
      }
      return jobs;
    }
  }
}
