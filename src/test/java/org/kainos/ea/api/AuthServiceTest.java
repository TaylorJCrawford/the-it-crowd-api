package org.kainos.ea.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.LoginDetails;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.db.AuthDao;

import org.kainos.ea.util.KeyGeneratorUtil;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

  AuthService authService = new AuthService();
  LoginRequest validLoginRequest = new LoginRequest("email", "password");
  LoginRequest invalidLoginRequest = new LoginRequest("invalidEmail", "invalidPassword");
  AuthDao authDAO = Mockito.mock(AuthDao.class);

  @Test
  void userLogin_shouldReturnValidToken_whenLoginWithValidCredentials () throws
          JWTCouldNotBeCreatedException, CouldNotGenerateKeyPairException, ActionFailedException, AuthenticationException {
    // [Goal] - Able to log in with valid creds and token is returned.

    KeyGeneratorUtil.setInstance(new KeyGeneratorUtil());
    validLoginRequest.setPasswordHash("$argon2id$v=19$m=1024,t=20,p=4$QlanY4WNEr0orKUsTgk$3Tuxa3k7sYRuC9ZIr1wvSESKYyLRQfnWAhQ5macZTFtbZOpt6sZ7JcDjQLsZx0J39EqHRzbgFbRX0Hmsmww1g67CQiLPeAMPID/SfRINED6xFlpY8XQDCzGN+AmtoFwz7Spl/xkgbySt/3H5SFfIIuBvYvN71SegjnIK/Dwm82Y");

    Mockito.when(authDAO.getUserPasswordHash(validLoginRequest)).thenReturn(validLoginRequest);

    LoginDetails loginDetails = new LoginDetails(
            "email",
            "firstName",
            "lastName"
    );

    Mockito.when(authDAO.getUserDetails(validLoginRequest.getEmail())).thenReturn(loginDetails);

    String token = authService.userLogin(validLoginRequest, authDAO);
    Assertions.assertNotNull(token);
  }

  @Test
  void userLogin_shouldReturnInValidLogin_whenGetUserLoginDetailsThrowException () throws ActionFailedException, AuthenticationException {
    // [Goal] - Get error for invalid login.

    Mockito.when(authDAO.getUserPasswordHash(invalidLoginRequest)).thenThrow(AuthenticationException.class);

    assertThrows(AuthenticationException.class,
            () -> authService.userLogin(invalidLoginRequest, authDAO));
  }
}
