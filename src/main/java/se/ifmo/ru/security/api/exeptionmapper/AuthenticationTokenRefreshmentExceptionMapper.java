package se.ifmo.ru.security.api.exeptionmapper;

import se.ifmo.ru.security.exception.AuthenticationTokenRefreshmentException;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception mapper for {@link AuthenticationTokenRefreshmentException}s.
 */
@Provider
public class AuthenticationTokenRefreshmentExceptionMapper implements ExceptionMapper<AuthenticationTokenRefreshmentException> {

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(AuthenticationTokenRefreshmentException exception) {

        Status status = Status.FORBIDDEN;

        String response = status.getStatusCode() + status.getReasonPhrase() +
                "The authentication token cannot be refreshed." + uriInfo.getAbsolutePath().getPath();

        return Response.status(status).entity(response).type(MediaType.APPLICATION_JSON).build();
    }
}