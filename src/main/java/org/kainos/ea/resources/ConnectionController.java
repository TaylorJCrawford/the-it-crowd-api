package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.ConnectionService;
import org.kainos.ea.client.DatabaseConnectionFailedException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Database Connection Controller")
@Path("/api")
public class ConnectionController {

    private final ConnectionService connectionService = new ConnectionService();

    @GET
    @Path("/database-active")
    @Produces(MediaType.APPLICATION_JSON)
    public Response testDatabaseConnection() {

        try {
            return Response.status(200).entity(connectionService.testDatabaseConnection()).build();
        } catch (DatabaseConnectionFailedException e) {
            throw new RuntimeException(e);
        }
    }
}
