package org.kainos.ea.client;

public class UnableToDeleteJWTException extends Throwable {

  @Override
  public String getMessage() {
    return "Unable To Delete JWT Exception";
  }
}
