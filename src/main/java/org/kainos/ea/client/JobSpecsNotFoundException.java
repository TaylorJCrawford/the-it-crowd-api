package org.kainos.ea.client;

public class JobSpecsNotFoundException extends Throwable {
  public JobSpecsNotFoundException() {
    super("Job specifications were not in the database");
  }
}
