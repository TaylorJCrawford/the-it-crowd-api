package org.kainos.ea.api;

import org.kainos.ea.cli.Job;
import org.kainos.ea.client.CantGetAnyRolesException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobService {

    private JobDao jobDao = new JobDao();
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public JobService(JobDao jobDao, DatabaseConnector databaseConnector) {
        this.jobDao = jobDao;
        this.databaseConnector = databaseConnector;
    }

    public List<Job> getAllJobs() throws SQLException, CantGetAnyRolesException {
      return jobDao.getAllJobs(databaseConnector.getConnection());
}}
