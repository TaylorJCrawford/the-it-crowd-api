package org.kainos.ea.db;

import org.kainos.ea.cli.Job;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobDao {

  public List<Job> getAllJobs(Connection c) throws SQLException {

    try (Statement st = c.createStatement()) {

      ResultSet rs = st.executeQuery("SELECT jobId, jobName,jobCapabilityId,jobSpecId FROM JobRoles;");
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
