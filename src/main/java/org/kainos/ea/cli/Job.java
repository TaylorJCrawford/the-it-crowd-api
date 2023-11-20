package org.kainos.ea.cli;

public class Job {
  private int jobId;
  private String jobName;
  private int jobCapabilityId;
  private String jobSpecUrl;



  public Job(int jobId, String jobName, int jobCapabilityId, String jobSpecUrl) {
    this.jobId = jobId;
    this.jobName = jobName;
    this.jobCapabilityId = jobCapabilityId;
    this.jobSpecUrl = jobSpecUrl;
  }

  public int getJobCapabilityId() {
    return jobCapabilityId;
  }

  public void setJobCapabilityId(int jobCapabilityId) {
    this.jobCapabilityId = jobCapabilityId;
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


