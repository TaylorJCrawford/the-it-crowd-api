package org.kainos.ea.client;

public class JWTExpiredException extends Throwable {

  @Override
  public String getMessage() {
    return "JWT Expired Exception";
  }
}
