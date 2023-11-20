package org.kainos.ea.db;

import org.kainos.ea.cli.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDao {

  public List<Job> getAllJobs(Connection c) throws SQLException {
    try (PreparedStatement ps = c.prepareStatement("SELECT jobId, jobName, jobCapabilityId, jobSpecId FROM JobRoles;");
         ResultSet rs = ps.executeQuery()) {

      List<Job> jobs = new ArrayList<>();

      while (rs.next()) {
        Job job = new Job(
                rs.getInt("jobId"),
                rs.getString("jobName"),
                rs.getInt("jobCapabilityId"),
                rs.getInt("jobSpecId")
        );
        jobs.add(job);
      }
      return jobs;
    }
  }
}
