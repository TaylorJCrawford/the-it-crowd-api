package org.kainos.ea.cli;

public class JobResponse {
  private int jobId;
  private String jobName;
  private String bandName;
  private String jobCapabilityName;
  private String jobSpecUrl;

  public JobResponse(int jobId, String jobName, String bandName, String jobCapabilityName, String jobSpecUrl) {
    this.jobId = jobId;
    this.jobName = jobName;
    this.bandName = bandName;
    this.jobCapabilityName = jobCapabilityName;
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

  public String getBandName() {
    return bandName;
  }

  public void setBandName(String bandName) {
    this.bandName = bandName;
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
}
