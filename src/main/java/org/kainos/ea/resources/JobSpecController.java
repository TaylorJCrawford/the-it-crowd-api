package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.JobSpecService;
import org.kainos.ea.client.JobSpecsNotFoundException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobSpecDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Job Specification Controller")
@Path("/api")
public class JobSpecController {

   private final JobSpecDao jobSpecDao = new JobSpecDao();
  private DatabaseConnector databaseConnector = new DatabaseConnector();
   private JobSpecService jobSpecService = new JobSpecService(jobSpecDao, databaseConnector);
    @GET
    @Path("/job-specs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobSpecs() {
        try {
            return Response.ok(jobSpecService.getAllJobSpecs()).build();
        } catch (JobSpecsNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return Response.serverError().build();
        }
    }
    @GET
    @Path("/job-specs/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobSpec(@PathParam("id") int id) {
        try {
            return Response.ok(jobSpecService.getJobSpec(id)).build();
        } catch (JobSpecsNotFoundException e) {
            System.out.println(e.getMessage());
           return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }
}
