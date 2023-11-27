package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class JobRoleResponse {
  private int jobId;
  private String jobName;
  private String jobCapabilityName;
  private String jobSpecUrl;
  private List<String> responsibilities;
  private String bandName;

  @JsonCreator
  public JobRoleResponse(
          @JsonProperty("jobId") int jobId,
          @JsonProperty("jobName") String jobName,
          @JsonProperty("jobCapabilityName") String jobCapabilityName,
          @JsonProperty("jobSpecUrl") String jobSpecUrl,
          @JsonProperty("responsibilities") List<String> responsibilities,
          @JsonProperty("bandName") String bandName) {
    this.jobId = jobId;
    this.jobName = jobName;
    this.jobCapabilityName = jobCapabilityName;
    this.jobSpecUrl = jobSpecUrl;
    this.bandName = bandName;
    this.responsibilities = responsibilities;
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

  public List<String> getResponsibilities() {
    return responsibilities;
  }

  public void setResponsibilities(List<String> responsibilities) {
    this.responsibilities = responsibilities;
  }
}
