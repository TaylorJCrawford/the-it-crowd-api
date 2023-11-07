package org.kainos.ea.db;

import org.kainos.ea.cli.Job;
import org.kainos.ea.client.CantGetAnyRolesException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobDao {

    public List<Job> getAllJobs(Connection c) throws SQLException, CantGetAnyRolesException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT * FROM JobRoles;");

        List<Job> jobs = new ArrayList<>();

        if(rs ==null){
            throw new CantGetAnyRolesException();
        }

        while (rs.next()) {
            Job job = new Job(
                    rs.getInt("jobId"),
                    rs.getString("jobName")
            );

            System.out.println(job);

            job.setJobId(rs.getInt("jobId"));
            jobs.add(job);

        }
        System.out.println(jobs);
        return jobs;
    }

}
