package org.kainos.ea.client;

public class ActionFailedException extends Throwable {

  public ActionFailedException(String validationMessage) {
    super(validationMessage);
  }
}
