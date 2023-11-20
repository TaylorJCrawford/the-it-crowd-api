package org.kainos.ea.db;

import org.kainos.ea.cli.JobCapability;
import org.kainos.ea.cli.JobCapabilityRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobCapabilityDao {

  public List<JobCapability> getAllJobCapabilities(Connection c) throws SQLException {

    try (PreparedStatement ps = c.prepareStatement("SELECT jobCapabilityId, jobCapabilityName FROM JobCapabilities");
         ResultSet rs = ps.executeQuery()) {

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
    try (PreparedStatement ps = c.prepareStatement("SELECT jobCapabilityName FROM JobCapabilities WHERE jobCapabilityId = ?");
    ) {
      ps.setInt(1, jobCapabilityId);

      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          JobCapabilityRequest jobCapabilityRequest = new JobCapabilityRequest(
                  rs.getString("jobCapabilityName")
          );
          return jobCapabilityRequest;
        }
      }
    }
    return null;
  }
}