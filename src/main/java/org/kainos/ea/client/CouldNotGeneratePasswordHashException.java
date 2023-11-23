package org.kainos.ea.client;

public class CouldNotGeneratePasswordHashException extends Throwable {

  @Override
  public String getMessage() {
    return "Could Generate Password Hash";
  }
}
