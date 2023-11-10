package org.kainos.ea.client;

public class DatabaseConnectionFailedException extends Throwable {
  @Override
  public String getMessage() {
    return "Failed To Connect To Database Exception";
  }
}
