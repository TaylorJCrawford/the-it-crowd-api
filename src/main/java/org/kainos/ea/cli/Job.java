package org.kainos.ea.cli;

public class Job {
  private int jobId;
  private String jobName;
  private int jobCapabilityId;
  private int jobSpecId;

  public Job(int jobId, String jobName, int jobCapabilityId, int jobSpecId) {
    this.jobId = jobId;
    this.jobName = jobName;
    this.jobCapabilityId = jobCapabilityId;
    this.jobSpecId = jobSpecId;
  }

  public int getJobCapabilityId() {
    return jobCapabilityId;
  }

  public void setJobCapabilityId(int jobCapabilityId) {
    this.jobCapabilityId = jobCapabilityId;
  }

  public int getJobSpecId() {
    return jobSpecId;
  }

  public void setJobSpecId(int jobSpecId) {
    this.jobSpecId = jobSpecId;
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
}
