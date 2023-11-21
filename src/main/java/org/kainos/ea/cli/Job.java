package org.kainos.ea.cli;

public class Job {
  private int jobId;
  private String jobName;
  private String bandName;

  public Job(int jobId, String jobName, String bandName) {
    this.jobId = jobId;
    this.jobName = jobName;
    this.bandName = bandName;
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

  public String getBandName() {
    return bandName;
  }

  public void setBandName(String bandName) {
    this.bandName = bandName;
  }
}
