package org.kainos.ea.client;

public class InvalidLoginAttemptException extends Throwable {

  @Override
  public String getMessage() {
    return "Invalid Login Attempt Exception";
  }
}
