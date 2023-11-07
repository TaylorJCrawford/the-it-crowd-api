package org.kainos.ea.resources;

import org.eclipse.jetty.http.HttpStatus;
import org.kainos.ea.api.JobService;
import org.kainos.ea.client.CantGetAnyRolesException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/api")
public class JobController {

    private JobService jobService = new JobService();

    @GET
    @Path("/jobs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobs(){
        try{
            return Response.ok(jobService.getAllJobs()).build();
        } catch(SQLException e){
            System.out.println(e);
        return Response.serverError().build();
        } catch (CantGetAnyRolesException e) {
            System.out.println(e);
            return Response.serverError().build();
        }
    }
}

