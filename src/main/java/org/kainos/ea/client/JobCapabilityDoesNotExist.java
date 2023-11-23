package org.kainos.ea.client;

public class JobCapabilityDoesNotExist extends Throwable {
  @Override
  public String getMessage() {
    return "Job capability does not exist";
  }
}
