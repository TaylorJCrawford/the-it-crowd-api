package org.kainos.ea.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRequest {

    private int jobId;
    private int jobSpecId;
    private String jobName;

    private String jobSpecName;
    private String jobSpecSharepointLink;


    @JsonCreator
    public JobRequest(@JsonProperty("jobId") int jobId,
                      @JsonProperty("jobSpecId") int jobSpecId,
                      @JsonProperty("jobName") String jobName,
                      @JsonProperty("jobSpecName") String jobSpecName,
                      @JsonProperty("jobSpecSharepointLink") String jobSpecSharepointLink) {
        this.jobId = jobId;
        this.jobSpecId = jobSpecId;
        this.jobName = jobName;
        this.jobSpecName = jobSpecName;
        this.jobSpecSharepointLink = jobSpecSharepointLink;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getJobSpecId() {
        return jobSpecId;
    }

    public void setJobSpecId(int jobSpecId) {
        this.jobSpecId = jobSpecId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
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
