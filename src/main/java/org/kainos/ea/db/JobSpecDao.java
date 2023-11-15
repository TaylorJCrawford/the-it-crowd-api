package org.kainos.ea.db;

import org.kainos.ea.cli.JobSpec;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobSpecDao {
    public List<JobSpec> getAllJobSpecs(Connection c) {
        String sql = "SELECT jobSpecId, jobSpecName, jobSpecSharepointLink FROM JobSpecs;";
        try (PreparedStatement st = c.prepareStatement(sql);) {
//            try (Connection c = databaseConnector.getConnection(); PreparedStatement st = c.prepareStatement(sql);) {
            ResultSet rs = st.executeQuery();

            List<JobSpec> jobSpecs = new ArrayList<>();

            while (rs.next()) {
                JobSpec jobSpec = new JobSpec(
                        rs.getInt("jobSpecId"),
                        rs.getString("jobSpecName"),
                        rs.getString("jobSpecSharepointLink")
                );
                System.out.println("in side while loop: " + jobSpec.getJobSpecName());
                jobSpecs.add(jobSpec);
            }

            if (jobSpecs.isEmpty()) {
                return null;
            }

            return jobSpecs;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public JobSpec getJobSpec(int id, Connection c) throws SQLException {
        String sql = "SELECT jobSpecId, jobSpecName, jobSpecSharepointLink FROM JobSpecs WHERE jobSpecId = " + id;
        try (PreparedStatement st = c.prepareStatement(sql);) {
//            try (Connection c = databaseConnector.getConnection(); PreparedStatement st = c.prepareStatement(sql);) {
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new JobSpec(
                        rs.getInt("jobSpecId"),
                        rs.getString("jobSpecName"),
                        rs.getString("jobSpecSharepointLink")
                );
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return null;
    }
}
