package org.kainos.ea.client;

public class LoginDetailsAreNotInCorrectFormatException extends Throwable {

  private final String message;

  public LoginDetailsAreNotInCorrectFormatException(String message) {
    this.message = message;
  }
  @Override
  public String getMessage() {

    return "Login Details Are Not In Correct Format Exception: " + message;
  }
}
