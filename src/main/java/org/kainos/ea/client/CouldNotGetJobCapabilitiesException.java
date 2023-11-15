package org.kainos.ea.client;

public class CouldNotGetJobCapabilitiesException extends Throwable {

  @Override
  public String getMessage() {
    return "Can't Get Job Capabilities";
  }
}
