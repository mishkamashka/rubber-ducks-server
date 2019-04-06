package se.ifmo.ru.security.api.exeptionmapper;

import se.ifmo.ru.security.exception.AccessDeniedException;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception mapper for {@link AccessDeniedException}s.
 */
@Provider
public class AccessDeniedExceptionMapper implements ExceptionMapper<AccessDeniedException> {

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(AccessDeniedException exception) {

        Status status = Status.FORBIDDEN;

        String response = status.getStatusCode() + status.getReasonPhrase() +
                "You don't have enough permissions to perform this action." + uriInfo.getAbsolutePath().getPath();

        return Response.status(status).entity(response).type(MediaType.APPLICATION_JSON).build();
    }
}