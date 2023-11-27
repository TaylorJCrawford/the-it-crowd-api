package org.kainos.ea.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRequest {

  private int jobId;
  private int jobSpecId;
  private String jobName;

  private int jobCapabilityId;

  private String jobSpecName;
  private String jobSpecSharepointLink;

  private int bandId;


  @JsonCreator
  public JobRequest(@JsonProperty("jobSpecId") int jobSpecId,
                    @JsonProperty("jobName") String jobName,
                    @JsonProperty("jobCapabilityId") int jobCapabilityId,
                    @JsonProperty("bandId") int bandId,
                    @JsonProperty("jobSpecName") String jobSpecName,
                    @JsonProperty("jobSpecSharepointLink") String jobSpecSharepointLink) {
    this.jobSpecId = jobSpecId;
    this.jobName = jobName;
    this.bandId = bandId;
    this.jobCapabilityId = jobCapabilityId;
    this.jobSpecName = jobSpecName;
    this.jobSpecSharepointLink = jobSpecSharepointLink;
  }

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

  public String getJobSpecName() {
    return jobSpecName;
  }

  public void setJobSpecName(String jobSpecName) {
    this.jobSpecName = jobSpecName;
  }

  public String getJobSpecSharepointLink() {
    return jobSpecSharepointLink;
  }

  public void setJobSpecSharepointLink(String jobSpecSharepointLink) {
    this.jobSpecSharepointLink = jobSpecSharepointLink;
  }

  public int getJobCapabilityId() {
    return jobCapabilityId;
  }

  public void setJobCapabilityId(int jobCapabilityId) {
    this.jobCapabilityId = jobCapabilityId;
  }

  public int getBandId() {
    return bandId;
  }

  public void setBandId(int bandId) {
    this.bandId = bandId;
  }
}