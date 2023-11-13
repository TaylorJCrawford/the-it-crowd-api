package org.kainos.ea.api;

import org.kainos.ea.cli.JobSpec;
import org.kainos.ea.client.JobSpecsNotFoundException;
import org.kainos.ea.db.JobSpecDao;

import java.util.List;

public class JobSpecService {

    private final JobSpecDao jobSpecDao = new JobSpecDao();

    public List<JobSpec> getAllJobSpecs() throws JobSpecsNotFoundException {

        if (jobSpecDao.getAllJobSpecs() == null) {
            throw new JobSpecsNotFoundException();
        }
        return jobSpecDao.getAllJobSpecs();
    }
    public JobSpec getJobSpec(int id) throws JobSpecsNotFoundException {

            JobSpec jobSpec = jobSpecDao.getJobSpec(id);
        if (jobSpec == null) {
            throw new JobSpecsNotFoundException();
        }
        return jobSpec;
    }
}
