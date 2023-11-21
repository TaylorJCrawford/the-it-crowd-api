package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class JobRoleResponse {
  private int jobId;
  private String jobName;
  private String jobSpecUrl;
  private String bandName;
  private List<String> responsibilities;

  @JsonCreator
  public JobRoleResponse(
          @JsonProperty("jobId") int jobId,
          @JsonProperty("jobName") String jobName,
          @JsonProperty("jobSpecUrl") String jobSpecUrl,
          @JsonProperty("bandName") String bandName,
          @JsonProperty("responsibilities") List<String> responsibilities) {
    this.jobId = jobId;
    this.jobName = jobName;
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
