package org.kainos.ea.client;

/**
 * Thrown when a job with specified ID does not exist
 */
public class FailedToDeleteException extends Throwable {

    public FailedToDeleteException(String validationMessage) {
        super(validationMessage);
    }
}