package hu.imsi.mir.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static hu.imsi.mir.util.Constants.APPLICATION_VND_API_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.OK;

@Path("/send/v1")
@Consumes({APPLICATION_VND_API_JSON, APPLICATION_JSON})
@Produces({APPLICATION_VND_API_JSON, APPLICATION_JSON})
public class JsonMIRAPIv1 {

    @GET
    @Path("/info")
    @Produces({"text/html"})
    public Response info() {
        return Response.status(OK).entity(getClass().getSimpleName()).build();
    }
}
