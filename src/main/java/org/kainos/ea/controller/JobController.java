package org.kainos.ea.controller;
import io.swagger.annotations.Api;
import org.kainos.ea.api.JobService;
import org.kainos.ea.client.CantGetAnyRolesException;
import org.kainos.ea.client.CouldNotGetJobResponsibilityException;
import org.kainos.ea.client.NoJobResponsibilityStoredForJobRoleException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobDao;
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

  public JobController(){
    DatabaseConnector databaseConnector = new DatabaseConnector();
    jobService = new JobService(new JobDao(), databaseConnector);
  }


  @GET
  @Path("/jobs")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getJobs(){
    try{
      return Response.ok(jobService.getAllJobs()).build();
    }
    catch(SQLException e){
      System.err.println(e);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
              .entity("Database error: " + e.getMessage())
              .build();
    }
    catch(CantGetAnyRolesException e){
      System.err.println(e);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
              .entity("Job roles retrieval error: " + e.getMessage())
              .build();
    }
  }

  @GET
  @Path("/job-responsibility/{jobId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getJobResponsibility (@PathParam("jobId") int jobId) {
    try {
      return Response.ok(jobService.getJobResponsibility(jobId)).build();
    } catch (NoJobResponsibilityStoredForJobRoleException e) {
      // 400 - Job Responsibility Bad Request
      System.err.println(e.getMessage());
      return Response.status(Response.Status.BAD_REQUEST).build();
    } catch (CouldNotGetJobResponsibilityException | SQLException e) {
      // 500 - Server Error
      System.err.println(e.getMessage());
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
  }
}
