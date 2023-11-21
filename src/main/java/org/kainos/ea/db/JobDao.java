package org.kainos.ea.db;

import org.kainos.ea.cli.Job;
import org.kainos.ea.cli.JobsResponse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobDao {

  public List<JobsResponse> getAllJobs(Connection c) throws SQLException {

    try (Statement st = c.createStatement()) {

      String queryString = "SELECT jobId, jobName " +
              "FROM JobRoles " +
              "LEFT JOIN Bands USING(bandId);";

      ResultSet rs = st.executeQuery(queryString);
      List<JobsResponse> jobs = new ArrayList<>();

      while (rs.next()) {
        JobsResponse job = new JobsResponse(
                rs.getInt("jobId"),
                rs.getString("jobName")
        );
        jobs.add(job);
      }
      return jobs;
    }
  }

  public Job getJobById(int id, Connection c) throws SQLException {
    String jobSql = "SELECT jobId, jobName, jobSpecUrl, bandName " +
            "FROM JobRoles " +
            "LEFT JOIN Bands USING(bandId) " +
            "WHERE jobId = ?;";

    String responsibilitiesSql = "SELECT responsibilityDetails " +
            "FROM Responsibilities " +
            "WHERE jobId = ?";

    try (PreparedStatement jobSt = c.prepareStatement(jobSql);
         PreparedStatement responsibilitySt = c.prepareStatement(responsibilitiesSql)) {
      jobSt.setInt(1, id);
      responsibilitySt.setInt(1, id);

      ResultSet jobRs = jobSt.executeQuery();
      ResultSet responsibilityRs = responsibilitySt.executeQuery();

      if (jobRs.next()) {

        List<String> responsibilities = new ArrayList<>();
        while (responsibilityRs.next()) {
          responsibilities.add(responsibilityRs.getString("responsibilityDetails"));
        }

        return new Job(
                jobRs.getInt("jobId"),
                jobRs.getString("jobName"),
                jobRs.getString("jobSpecUrl"),
                jobRs.getString("bandName"),
                responsibilities
        );
      }
      return null;
    }
  }

}
