package org.kainos.ea.db;

import org.kainos.ea.api.JobRequest;
import org.kainos.ea.cli.Job;
import org.kainos.ea.cli.JobResponsibility;
import org.kainos.ea.client.CouldNotGetJobResponsibilityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobDao {

    public List<Job> getAllJobs(Connection c) throws SQLException {

        try (Statement st = c.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT jobId, jobName, jobSpecId FROM JobRoles;");
            List<Job> jobs = new ArrayList<>();

            while (rs.next()) {
                Job job = new Job(
                        rs.getInt("jobId"),
                        rs.getInt("jobSpecId"),
                        rs.getString("jobName")
                );
                jobs.add(job);
            }
            return jobs;
        }
    }
    public JobRequest getJobById(int id, Connection c) throws SQLException {
        String sql = "SELECT A.jobId, A.jobName, A.jobSpecId, B.jobSpecName, B.jobSpecSharepointLink FROM JobRoles A JOIN JobSpecs B ON A.jobSpecId  = B.jobSpecId WHERE A.jobId = ?;";
        try (PreparedStatement st = c.prepareStatement(sql)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new JobRequest(
                        rs.getInt("jobId"),
                        rs.getInt("jobSpecId"),
                        rs.getString("jobName"),
                        rs.getString("jobSpecName"),
                        rs.getString("jobSpecSharepointLink")
                );
            }
            return null;
        }
    }

  public JobResponsibility getJobResponsibility(Connection c, int jobId) throws CouldNotGetJobResponsibilityException {

    // Return The Responsibility For The Job.

    String selectStatement = "SELECT responsibilityId, responsibilityTextBody, " +
            "responsibilityTextPoints FROM Responsibilities WHERE jobId = ?";
    try (PreparedStatement st = c.prepareStatement(selectStatement)) {

      st.setInt(1, jobId);
      ResultSet rs = st.executeQuery();

      if (rs.next()) {
        return new JobResponsibility(
                rs.getInt("responsibilityId"),
                rs.getString("responsibilityTextBody"),
                rs.getString("responsibilityTextPoints")
        );
      }

      return null;

    } catch (Exception e) {
      throw new CouldNotGetJobResponsibilityException();
    }
  }
}
