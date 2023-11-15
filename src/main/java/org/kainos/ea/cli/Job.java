package org.kainos.ea.cli;

public class Job {
  private int jobId;
  private int jobSpecId;
  private String jobName;

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

  public Job(int jobId, int jobSpecId, String jobName) {
    this.jobId = jobId;
    this.jobSpecId = jobSpecId;
    this.jobName = jobName;


  }
}
