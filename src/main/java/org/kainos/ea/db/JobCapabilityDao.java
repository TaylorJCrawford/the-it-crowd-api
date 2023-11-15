package org.kainos.ea.db;

import org.kainos.ea.cli.JobCapability;
import org.kainos.ea.cli.JobCapabilityRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobCapabilityDao {

  public List<JobCapability> getAllJobCapabilities(Connection c) throws SQLException {

    try (Statement st = c.createStatement()) {

      ResultSet rs = st.executeQuery("SELECT jobCapabilityId, jobCapabilityName FROM JobCapabilities;");
      List<JobCapability> jobCapabilities = new ArrayList<>();

      while (rs.next()) {
        JobCapability jobCapability = new JobCapability(
                rs.getInt("jobCapabilityId"),
                rs.getString("jobCapabilityName")
        );
        jobCapabilities.add(jobCapability);
      }
      return jobCapabilities;
    }
  }

  public JobCapabilityRequest getJobCapability(int jobCapabilityId, Connection c) throws SQLException {
    try (Statement st = c.createStatement()) {

      ResultSet rs = st.executeQuery("SELECT jobCapabilityName FROM JobCapabilities WHERE jobCapabilityId =" + jobCapabilityId);
      List<JobCapability> jobCapabilities = new ArrayList<>();

      while (rs.next()) {
        JobCapabilityRequest jobCapabilityRequest = new JobCapabilityRequest(
                rs.getString("jobCapabilityName")
        );
        return jobCapabilityRequest;
      }
    }
    return null;
  }
}