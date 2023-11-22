package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.AuthService;
import org.kainos.ea.cli.LoginRequest;

import org.kainos.ea.client.LoginDetailsAreNotInCorrectFormatException;
import org.kainos.ea.client.ActionFailedException;
import org.kainos.ea.client.AuthenticationException;
import org.kainos.ea.client.JWTCouldNotBeCreatedException;
import org.kainos.ea.db.AuthDao;
import org.kainos.ea.validator.AuthValidator;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Authentication Controller")
@Path("/api/auth")
public class AuthController {

  private final AuthService authService;
  private final AuthDao authDAO;
  private final AuthValidator authValidator;

  public AuthController(AuthService authService, AuthDao authDAO, AuthValidator authValidator) {
    this.authService = authService;
    this.authDAO = authDAO;
    this.authValidator = authValidator;
  }

  @POST
  @Path("/login")
  @Produces(MediaType.APPLICATION_JSON)
  public Response userLogin(LoginRequest loginRequest) {

    try {
      String validatorResult = authValidator.areLoginDetailInCorrectFormat(loginRequest);
      if (validatorResult != null) {
        throw new LoginDetailsAreNotInCorrectFormatException(validatorResult);
      }
      String token = authService.userLogin(loginRequest, authDAO);
      return Response.ok().entity(token).build();

    } catch (ActionFailedException | JWTCouldNotBeCreatedException e) {
      System.err.println(e.getMessage());
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Unable to login.").build();

    } catch (AuthenticationException e) {
      System.err.println(e.getMessage());
      return Response.status(Response.Status.FORBIDDEN).entity("Invalid credentials provided.").build();

    } catch (LoginDetailsAreNotInCorrectFormatException e) {
      System.err.println(e.getMessage());
      return Response.status(Response.Status.BAD_REQUEST).entity("Credentials provided are not in the correct format.").build();
    }
  }
}
