package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.AuthService;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.db.AuthDAO;
import org.kainos.ea.client.DatabaseConnectionFailedException;
import org.kainos.ea.client.JWTCouldNotBeCreatedException;
import org.kainos.ea.client.CouldNotGeneratePasswordHashException;
import org.kainos.ea.client.CouldNotFindUserAccountException;
import org.kainos.ea.client.LoginDetailsAreNotInCorrectFormatException;
import org.kainos.ea.client.InvalidLoginAttemptException;
import org.kainos.ea.validator.AuthValidator;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Authentication Controller")
@Path("/api/auth")
public class AuthController {

  private final AuthService authService = new AuthService();
  private final AuthDAO authDAO = new AuthDAO();
  private final AuthValidator authValidator = new AuthValidator();

  @POST
  @Path("/login")
  @Produces(MediaType.APPLICATION_JSON)
  public Response userLogin(LoginRequest loginRequest) {

    try {
      if (!authValidator.areLoginDetailInCorrectFormat(loginRequest)) {
        throw new LoginDetailsAreNotInCorrectFormatException();
      }

      String token = authService.userLogin(loginRequest, authDAO);
      return Response.ok().entity(token).build();
    } catch (DatabaseConnectionFailedException
             | JWTCouldNotBeCreatedException |
             CouldNotGeneratePasswordHashException e) {
      // Server Error
      System.err.println(e.getMessage());
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    } catch (InvalidLoginAttemptException e) {
      // Unauthorised
      System.err.println(e.getMessage());
      return Response.status(Response.Status.FORBIDDEN).build();
    } catch (CouldNotFindUserAccountException | LoginDetailsAreNotInCorrectFormatException e) {
      // Bad Request
      System.err.println(e.getMessage());
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
  }
}
