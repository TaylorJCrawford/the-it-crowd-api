package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobCapabilityRequest {


  private String jobCapabilityName;


  @JsonCreator
  public JobCapabilityRequest(
          @JsonProperty("jobCapabilityName") String jobCapabilityName) {
    this.setJobCapabilityName(jobCapabilityName);
  }

  public String getJobCapabilityName() {
    return jobCapabilityName;
  }

  public void setJobCapabilityName(String jobCapabilityName) {
    this.jobCapabilityName = jobCapabilityName;
  }
}



