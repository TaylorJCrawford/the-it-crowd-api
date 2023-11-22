package org.kainos.ea.client;

public class DoesNotExistException extends Throwable {

  public DoesNotExistException(String validationMessage) {
    super(validationMessage);
  }
}