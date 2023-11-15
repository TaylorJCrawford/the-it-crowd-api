package org.kainos.ea.validator;

import org.kainos.ea.cli.LoginRequest;

public class AuthValidator {

  private boolean checkEmailPattern(String email) {
    String ePattern = "^(.+)@(.+)$";
    java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
    java.util.regex.Matcher m = p.matcher(email);
    return m.matches();
  }

  public boolean areLoginDetailInCorrectFormat(LoginRequest loginRequest) {

    if (loginRequest.getPassword().length() < 8 || loginRequest.getPassword().length() > 64) {
      return false;
    }

    if (!checkEmailPattern(loginRequest.getEmail())) {
      return false;
    }

    return true;
  }
}
