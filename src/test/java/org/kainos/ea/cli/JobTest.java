package org.kainos.ea.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobTest {

    @Test
    void getterAndSetterJobRoleTest() {
        Job job = new Job(1, "Software Engineer", "www.test.com","Associate");

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
