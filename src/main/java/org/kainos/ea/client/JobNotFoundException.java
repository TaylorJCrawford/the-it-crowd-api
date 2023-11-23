package org.kainos.ea.client;

public class JobNotFoundException extends Throwable {
  @Override
  public String getMessage() {
    return "Can't Get Job Roles";
  }
}
