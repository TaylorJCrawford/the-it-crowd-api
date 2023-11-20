package org.kainos.ea.controller;

import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;
import org.kainos.ea.api.BandService;
import org.kainos.ea.client.ActionFailedException;
import org.kainos.ea.client.DoesNotExistException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Bands API")
@Path("/api")
public class BandController {
    private final BandService bandService;

    public BandController(BandService bandService) {
        this.bandService = bandService;
    }

    @GET
    @Path("/bands")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBands(){
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(bandService.getBands())
                    .build();
        } catch (ActionFailedException | SQLException e) {
            System.out.println(e.getMessage());
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }

    @GET
    @Path("/bands/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBandById(@PathParam("id") int id){
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(bandService.getBandById(id))
                    .build();
        } catch (ActionFailedException | SQLException e) {
            System.out.println(e.getMessage());
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        } catch (DoesNotExistException e) {
            System.out.println(e.getMessage());
            return Response.status(HttpStatus.NOT_FOUND_404).build();
        }
    }
}
