package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Job {
  private int jobId;
  private String jobName;

  private String jobSpecUrl;

  @JsonCreator
  public Job(
          @JsonProperty("jobId") int jobId,
          @JsonProperty("jobName") String jobName,
          @JsonProperty("jobSpecUrl") String jobSpecUrl) {
    this.jobId = jobId;
    this.jobName = jobName;
    this.jobSpecUrl = jobSpecUrl;
  }

  public int getJobId() {
    return jobId;
  }

  public void setJobId(int jobId) {
    this.jobId = jobId;
  }

  public String getJobName() {
    return jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
  }

  public String getJobSpecUrl() {
    return jobSpecUrl;
  }

  public void setJobSpecUrl(String jobSpecUrl) {
    this.jobSpecUrl = jobSpecUrl;
  }

}
