package org.kainos.ea.controller;

import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;
import org.kainos.ea.api.JobCapabilityService;
import org.kainos.ea.client.CouldNotGetJobCapabilitiesException;
import org.kainos.ea.client.JobCapabilityDoesNotExist;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobCapabilityDao;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Job Capabilities API")
@Path("/api")
public class JobCapabilityController {

  private final JobCapabilityService jobCapabilityService;

  public JobCapabilityController() {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    jobCapabilityService = new JobCapabilityService(new JobCapabilityDao(), databaseConnector);
  }

  @GET
  @Path("/job-capabilities")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getJobCapabilities() {
    try {
      return Response.ok(jobCapabilityService.getAllJobCapabilities()).build();
    } catch (SQLException e) {
      System.err.println(e);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
              .entity("Database error: " + e.getMessage())
              .build();
    } catch (CouldNotGetJobCapabilitiesException e) {
      System.err.println(e);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
              .entity("Job Capabilities retrieval error: " + e.getMessage())
              .build();
    }
  }



  @GET
  @Path("/job-capabilities/{jobCapabilityId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getJobCapabilityById(@PathParam("jobCapabilityId") int jobCapabilityId) {
    try {
      return Response.status(HttpStatus.OK_200).entity(jobCapabilityService.getJobCapability(jobCapabilityId)).build();
    } catch (SQLException e) {
      System.err.println(e);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
              .entity("Database error: " + e.getMessage())
              .build();
    } catch (JobCapabilityDoesNotExist e) {
      System.err.println(e);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
              .entity("Job Capability Error: " + e.getMessage())
              .build();
    }
  }
}


