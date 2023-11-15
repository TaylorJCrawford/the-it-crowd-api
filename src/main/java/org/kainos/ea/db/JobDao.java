package org.kainos.ea.db;

import org.kainos.ea.api.JobRequest;
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
        try (Statement st = c.createStatement()) {
            String sql = "SELECT A.jobId, A.jobName, A.jobSpecId, B.jobSpecName, B.jobSpecSharepointLink FROM JobRoles A JOIN JobSpecs B ON A.jobSpecId  = B.jobSpecId;";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                return new JobRequest(
                        rs.getInt("jobId"),
                        rs.getInt("jobSpecId"),
                        rs.getString("jobName"),
                        rs.getString("jobSpecName"),
                        rs.getString("jobSpecSharepointLink")
                );
            }
        }
        return null;
    }
}
