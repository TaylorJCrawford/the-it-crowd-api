package org.kainos.ea.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.kainos.ea.cli.LoginRequest;

public class AuthValidatorTest {

  AuthValidator authValidator = new AuthValidator();

  @ParameterizedTest
  @ValueSource(strings = {"user@domain.com", "user@domain.co.in", "user1@domain.com",
  "user.name@domain.com", "user#@domain.co.in", "user@domaincom"})
  void validateUserRequest_shouldReturnTrue_whenEmailIsInTheCorrectFormat (String emailAddress) {

    LoginRequest loginRequest = new LoginRequest(
            emailAddress,
            "fakePassword"
    );

    boolean result = authValidator.areLoginDetailInCorrectFormat(loginRequest);
    Assertions.assertTrue(result);
  }

  @ParameterizedTest
  @ValueSource(strings = {"user#domain.com", "@yahoo.com"})
  void validateUserRequest_shouldReturnFalse_whenEmailIsNotInTheCorrectFormat (String emailAddress) {

    LoginRequest loginRequest = new LoginRequest(
            emailAddress,
            "fakePassword"
    );

    boolean result = authValidator.areLoginDetailInCorrectFormat(loginRequest);
    Assertions.assertFalse(result);
  }

  @ParameterizedTest
  @ValueSource(strings = {"password", "kQuq0jbK1y6FcVZ9BQgfiMRh2EBgoEqGOWdi1n7N2QTpBGR1ceziUhRMwbSfnYiR",
    "iY8cWLRsIjAod9eyYGhECG3iowdIDxvj"})
  void validateUserRequest_shouldReturnTrue_whenPasswordIsInTheCorrectFormat (String password) {

    LoginRequest loginRequest = new LoginRequest(
            "validEmail@emails.com",
            password
    );

    boolean result = authValidator.areLoginDetailInCorrectFormat(loginRequest);
    Assertions.assertTrue(result);
  }

  @ParameterizedTest
  @ValueSource(strings = {"passwor", "wa1YVCYB5obG5HkjznxCzF160ZqsVtE6koGg6XPaNUrQTHrVzY9rlZZeGgkgdQLHX",
    "xoxD92n70s8xevoU2jLg2yPzSSyNSdydmr0k4X68xhsh36OuSGuUOj9oGMnhNqQk5t1K2vCC6wlYUCbxn4V3Am1aEiip4EW1GpqW"})
  void validateUserRequest_shouldReturnFalse_whenPasswordIsNotInTheCorrectFormat (String password) {

    LoginRequest loginRequest = new LoginRequest(
            "validEmail@emails.com",
            password
    );

    boolean result = authValidator.areLoginDetailInCorrectFormat(loginRequest);
    Assertions.assertFalse(result);
  }
}
