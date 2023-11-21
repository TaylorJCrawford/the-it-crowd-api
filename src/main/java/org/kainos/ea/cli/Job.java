package org.kainos.ea.cli;

public class Job {
  private int jobId;
  private String jobName;
  private String jobCapabilityName;
  private String jobSpecUrl;
  private String bandName;


  public Job(int jobId, String jobName, String jobCapabilityName, String jobSpecUrl,  String bandName) {
    this.jobId = jobId;
    this.jobName = jobName;
    this.jobCapabilityName = jobCapabilityName;
    this.jobSpecUrl = jobSpecUrl;
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

  public String getJobCapabilityName() {
    return jobCapabilityName;
  }

  public void setJobCapabilityName(String jobCapabilityName) {
    this.jobCapabilityName = jobCapabilityName;
  }

  public String getJobSpecUrl() {
    return jobSpecUrl;
  }

  public void setJobSpecUrl(String jobSpecUrl) {
    this.jobSpecUrl = jobSpecUrl;
  }

  public String getBandName() {
    return bandName;
  }

  public void setBandName(String bandName) {
    this.bandName = bandName;
  }
}


