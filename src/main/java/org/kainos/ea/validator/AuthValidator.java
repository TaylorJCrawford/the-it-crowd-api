package org.kainos.ea.validator;

import org.kainos.ea.cli.LoginRequest;

public class AuthValidator {

  private boolean checkEmailPattern(String email) {
    String ePattern = "^(.+)@(.+)$";
    java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
    java.util.regex.Matcher m = p.matcher(email);
    return m.matches();
  }

  public String areLoginDetailInCorrectFormat(LoginRequest loginRequest) {

    if (loginRequest.getPassword().length() < 8 || loginRequest.getPassword().length() > 64) {
      return "Invalid Password, Password Must Be Between 8 and 64 character";
    }

    if (!checkEmailPattern(loginRequest.getEmail())) {
      return "Invalid Email Address, Email Must Be In The Following Format: supperEmail@domain.com";
    }

    return null;
  }
}
