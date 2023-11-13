package org.kainos.ea.cli;

public class JobSpec {
private int jobSpecID;
private String jobSpecName;

    public JobSpec(int jobSpecID, String jobSpecName) {
        this.jobSpecID = jobSpecID;
        this.jobSpecName = jobSpecName;
    }

    public int getJobSpecID() {
        return jobSpecID;
    }

    public void setJobSpecID(int jobSpecID) {
        this.jobSpecID = jobSpecID;
    }

    public String getJobSpecName() {
        return jobSpecName;
    }

    public void setJobSpecName(String jobSpecName) {
        this.jobSpecName = jobSpecName;
    }
}
