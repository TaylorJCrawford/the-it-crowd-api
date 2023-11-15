package org.kainos.ea.cli;

public class JobSpec {
    private int jobSpecID;
    private String jobSpecName;
    private String jobSpecSharepointLink;

    public JobSpec(int jobSpecID, String jobSpecName, String jobSpecSharepointLink) {
        this.jobSpecID = jobSpecID;
        this.jobSpecName = jobSpecName;
        this.jobSpecSharepointLink = jobSpecSharepointLink;
    }

    public JobSpec() {

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

    public String getJobSpecSharepointLink() {
        return jobSpecSharepointLink;
    }

    public void setJobSpecSharepointLink(String jobSpecSharepointLink) {
        this.jobSpecSharepointLink = jobSpecSharepointLink;
    }
}
