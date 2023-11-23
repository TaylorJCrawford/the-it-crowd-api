package org.kainos.ea.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobTest {

    @Test
    void getterAndSetterJobRoleTest() {


        int jobRoleId = 2;
        String jobRoleTitle = "Senior Software Engineer";
        String bandName = "Band 6";

        Job job = new Job(jobRoleId, jobRoleTitle, "https://software-engineer.org", bandName);

        assertEquals(job.getJobId(), jobRoleId);
        assertEquals(job.getJobName(), jobRoleTitle);
        assertEquals(job.getBandName(), bandName);
    }
}
