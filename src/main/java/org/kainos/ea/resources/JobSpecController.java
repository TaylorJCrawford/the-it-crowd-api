package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.JobSpecService;
import org.kainos.ea.client.JobSpecsNotFoundException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Job Specification Controller")
@Path("/api")
public class JobSpecController {
    private JobSpecService jobSpecService = new JobSpecService();
    @GET
    @Path("/job-specs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobSpecs() {
        try {
            return Response.ok(jobSpecService.getAllJobSpecs()).build();
        } catch (JobSpecsNotFoundException e) {
            System.out.println(e.getMessage());
            return Response.serverError().build();
        }
    }
}
