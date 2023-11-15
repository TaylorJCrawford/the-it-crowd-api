package org.kainos.ea.cli;

public class JobCapability {

  private int jobCapabilityId;
  private String jobCapabilityName;

  public JobCapability(int jobCapabilityId, String jobCapabilityName) {
    this.jobCapabilityId = jobCapabilityId;
    this.jobCapabilityName = jobCapabilityName;
  }

  public int getJobCapabilityId() {
    return jobCapabilityId;
  }

  public void setJobCapabilityId(int jobCapabilityId) {
    this.jobCapabilityId = jobCapabilityId;
  }

  public String getJobCapabilityName() {
    return jobCapabilityName;
  }

  public void setJobCapabilityName(String jobCapabilityName) {
    this.jobCapabilityName = jobCapabilityName;
  }
}
