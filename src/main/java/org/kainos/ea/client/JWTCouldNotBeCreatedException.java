package org.kainos.ea.client;

public class JWTCouldNotBeCreatedException extends Throwable {

  @Override
  public String getMessage() {
    return "Could Not Create JWT Exception";
  }
}
