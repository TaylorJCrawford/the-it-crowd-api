package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.JobService;
import org.kainos.ea.client.CantGetAnyRolesException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Jobs API")
@Path("/api")
public class JobController {

  private final JobService jobService;

  public JobController(JobService jobService) {
    this.jobService = jobService;
  }

  @GET
  @Path("/jobs")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getJobs() {
    try {
      return Response.ok(this.jobService.getAllJobs()).build();
    } catch (SQLException | CantGetAnyRolesException e) {
      System.err.println(e.getMessage());
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GET
  @Path("/jobs/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getJobById(@PathParam("id") int id) {
    try {
      return Response.ok(this.jobService.getJobById(id)).build();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    } catch (CantGetAnyRolesException e) {
      System.err.println(e.getMessage());
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
  }
}

