package org.kainos.ea.cli;

public class JobCapabilityRequest {

  private String jobCapabilityName;

  public JobCapabilityRequest(String jobCapabilityName) {
    this.jobCapabilityName = jobCapabilityName;
  }

  public String getJobCapabilityName() {
    return jobCapabilityName;
  }

  public void setJobCapabilityName(String jobCapabilityName) {
    this.jobCapabilityName = jobCapabilityName;
  }
}


