package se.ifmo.ru;

import se.ifmo.ru.auth.Secured;
import se.ifmo.ru.security.domain.Authority;

import javax.management.Notification;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/notify")
public class NotificationsResource {

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Service online\n").build();
    }

    @GET
    @Secured({Authority.USER})
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotification(@PathParam("id") int id) {
        return Response.ok()
                .entity(id)
                .build();
    }

    @POST
    @Path("/post/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postNotification(Notification notification) {
        return Response.status(201).entity(notification).build();
    }
}