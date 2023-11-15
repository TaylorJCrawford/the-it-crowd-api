package org.kainos.ea.api;

import org.kainos.ea.cli.JobSpec;
import org.kainos.ea.client.JobSpecsNotFoundException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobSpecDao;

import java.sql.SQLException;
import java.util.List;



public class JobSpecService {
    private JobSpecDao jobSpecDao = new JobSpecDao();

    public DatabaseConnector databaseConnector;

    public JobSpecService(JobSpecDao jobSpecDao, DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
        this.jobSpecDao = jobSpecDao;
    }

    public List<JobSpec> getAllJobSpecs() throws JobSpecsNotFoundException, SQLException {

        if (jobSpecDao.getAllJobSpecs(databaseConnector.getConnection()) == null) {
            throw new JobSpecsNotFoundException();
        }
        List<JobSpec> jobSpecs = jobSpecDao.getAllJobSpecs(databaseConnector.getConnection());
        for (JobSpec jobSpec : jobSpecs) {
            System.out.println("job specs --> " + jobSpec.getJobSpecName());
        }

        return jobSpecs;
    }

    public JobSpec getJobSpec(int id) throws JobSpecsNotFoundException, SQLException {

        JobSpec jobSpec = jobSpecDao.getJobSpec(id, databaseConnector.getConnection());
        if (jobSpec == null) {
            throw new JobSpecsNotFoundException();
        }
        return jobSpec;
    }
}
