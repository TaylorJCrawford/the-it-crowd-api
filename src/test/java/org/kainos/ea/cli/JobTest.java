package org.kainos.ea.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobTest {

    @Test
    void getterAndSetterJobRoleTest() {

        int jobRoleId = 1;
        String jobRoleTitle = "testName";
        String capabilityName = "testCapability";
        String testUrlName = "testUrl";
        String bandName = "testBandName";

        Job job1 = new Job(1,"testName","testCapability","testUrl","testBandName");

        job1.setJobId(jobRoleId);
        job1.setJobName(jobRoleTitle);
        job1.setJobCapabilityName(capabilityName);
        job1.setJobSpecUrl(testUrlName);
        job1.setBandName(bandName);

        assertEquals(job1.getJobId(), jobRoleId);
        assertEquals(job1.getJobName(), jobRoleTitle);
        assertEquals(job1.getJobCapabilityName(), capabilityName);
        assertEquals(job1.getJobSpecUrl(), testUrlName);
        assertEquals(job1.getBandName(), bandName);
    }
}
