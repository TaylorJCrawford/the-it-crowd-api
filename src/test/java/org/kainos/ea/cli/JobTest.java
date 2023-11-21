package org.kainos.ea.cli;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobTest {

    @Test
    void getterAndSetterJobRoleTest() {
        String[] responsibilities = {"responsibility 1", "responsibility 2", "responsibility 3"};
        List<String> responsibilitiesList = Arrays.asList(responsibilities);

        JobRoleResponse job = new JobRoleResponse(1, "Software Engineer", "www.test.com","Associate", responsibilitiesList);

        int jobRoleId = 2;
        String jobRoleTitle = "Senior Software Engineer";
        String bandName = "Band 6";

        job.setJobId(jobRoleId);
        job.setJobName(jobRoleTitle);
        job.setBandName(bandName);

        assertEquals(job.getJobId(), jobRoleId);
        assertEquals(job.getJobName(), jobRoleTitle);
        assertEquals(job.getBandName(), bandName);
    }
}
