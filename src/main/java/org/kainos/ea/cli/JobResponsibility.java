package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobResponsibility {

  private int responsibilityId;
  private String responsibilityTextBody;
  private String responsibilityTextPoints;


  @JsonCreator
  public JobResponsibility(@JsonProperty("responsibilityId") int responsibilityId,
                           @JsonProperty("responsibilityTextBody") String responsibilityTextBody,
                           @JsonProperty("responsibilityTextPoints") String responsibilityTextPoints) {
    this.responsibilityId = responsibilityId;
    this.responsibilityTextBody = responsibilityTextBody;
    this.responsibilityTextPoints = responsibilityTextPoints;
  }

  public int getResponsibilityId() {
    return responsibilityId;
  }

  public void setResponsibilityId(int responsibilityId) {
    this.responsibilityId = responsibilityId;
  }

  public String getResponsibilityTextBody() {
    return responsibilityTextBody;
  }

  public void setResponsibilityTextBody(String responsibilityTextBody) {
    this.responsibilityTextBody = responsibilityTextBody;
  }

  public String getResponsibilityTextPoints() {
    return responsibilityTextPoints;
  }

  public void setResponsibilityTextPoints(String responsibilityTextPoints) {
    this.responsibilityTextPoints = responsibilityTextPoints;
  }
}
