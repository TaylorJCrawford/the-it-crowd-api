package org.kainos.ea.db;

import org.kainos.ea.cli.JobSpec;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobSpecDao {
    private final DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<JobSpec> getAllJobSpecs() {
        String sql = "SELECT jobSpecId, jobSpecName FROM JobSpecs";
        try (Connection c = databaseConnector.getConnection(); PreparedStatement st = c.prepareStatement(sql);) {
            ResultSet rs = st.executeQuery();

            List<JobSpec> jobSpecs = new ArrayList<>();

            if (!rs.next()) {
                return null;
            }

            while (rs.next()) {
                JobSpec jobSpec = new JobSpec(
                        rs.getInt("jobSpecId"),
                        rs.getString("jobSpecName")
                );
                jobSpecs.add(jobSpec);
            }
            return jobSpecs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public JobSpec getJobSpec(int id) {
        String sql = "SELECT jobSpecId, jobSpecName FROM JobSpecs WHERE jobSpecId = " + id;
        try (Connection c = databaseConnector.getConnection(); PreparedStatement st = c.prepareStatement(sql);) {
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new JobSpec(
                        rs.getInt("jobSpecId"),
                        rs.getString("jobSpecName")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
