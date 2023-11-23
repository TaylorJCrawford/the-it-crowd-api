package org.kainos.ea.resources;
import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;
import org.kainos.ea.api.JobService;
import org.kainos.ea.client.CantGetAnyRolesException;
import org.kainos.ea.client.DoesNotExistException;
import org.kainos.ea.client.FailedToDeleteException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
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
      return Response.ok(jobService.getAllJobs()).build();
    }
    catch(SQLException e){
      System.err.println(e.getMessage());
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    catch(CantGetAnyRolesException e){
      System.err.println(e.getMessage());
      return Response.status(Response.Status.NOT_FOUND).build();
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

  @DELETE
  @Path("/jobs/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteJobRole(@PathParam("id") int id) {
    try {
      jobService.deleteJobRole(id);
      return Response.status(HttpStatus.NO_CONTENT_204).build();
    } catch (FailedToDeleteException e) {
      return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
    } catch (DoesNotExistException e) {
      return Response.status(HttpStatus.NOT_FOUND_404).build();
    }
  }

}

