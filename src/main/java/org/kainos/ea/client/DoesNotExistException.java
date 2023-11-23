package org.kainos.ea.client;

/**
* Thrown when a job with specified ID does not exist
*/
public class DoesNotExistException extends Throwable {

  public DoesNotExistException(String validationMessage) {
    super(validationMessage);
  }
}