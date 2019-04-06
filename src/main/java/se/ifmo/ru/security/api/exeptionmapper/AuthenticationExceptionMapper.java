package se.ifmo.ru.security.api.exeptionmapper;

import se.ifmo.ru.security.exception.AuthenticationException;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception mapper for {@link AuthenticationException}s.
 */
@Provider
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException> {

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(AuthenticationException exception) {

        Status status = Status.FORBIDDEN;

        String response = status.getStatusCode() + status.getReasonPhrase() +
                exception.getMessage() + uriInfo.getAbsolutePath().getPath();

        return Response.status(status).entity(response).type(MediaType.APPLICATION_JSON).build();
    }
}