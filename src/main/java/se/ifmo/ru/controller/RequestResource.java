package se.ifmo.ru.controller;

import se.ifmo.ru.auth.Secured;
import se.ifmo.ru.model.Duck;
import se.ifmo.ru.model.Request;
import se.ifmo.ru.model.User;
import se.ifmo.ru.security.api.AuthenticationTokenDetails;
import se.ifmo.ru.security.domain.Authority;
import se.ifmo.ru.security.service.AuthenticationTokenService;
import se.ifmo.ru.service.DuckService;
import se.ifmo.ru.service.RequestService;
import se.ifmo.ru.service.UserService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/requests")
public class RequestResource {


    @EJB
    private UserService userService;

    @EJB
    private DuckService duckService;

    @EJB
    private RequestService requestService;

    @EJB
    private AuthenticationTokenService authenticationTokenService;

    @POST
    @Secured({Authority.USER, Authority.ADMIN})
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users/all")
    public Response getUserRequests(String nickname) {
        User user = userService.getByNickname(nickname);
        List<Request> requests = requestService.getByUserId(user.getId());
        if (requests == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        String json = requestsToJSON(requests);
        return Response.ok(json).build();
    }

    @GET
    @Secured({Authority.USER, Authority.ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/details/{id}")
    public Response getRequestDetails(@PathParam("id") Long id) {
        Request request = requestService.getByIdWithDuckAndUser(id);
        if (request == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        String json = requestDetailsToJSON(request);
        return Response.ok(json).build();
    }

    @POST
    @Secured({Authority.USER, Authority.ADMIN})
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/new/{id}")
    public Response addRequest(@HeaderParam("Authorization") String token, @PathParam("id") Long id, Request request) {
        User user = getCurrentUser(token);
        request.setUser(user);
        Duck duck = duckService.getByIdWithOwner(id);
        request.setDuck(duck);
        requestService.save(request);
        return Response.ok("request has been added").build();
    }


    private String requestsToJSON(List<Request> requests) {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (Request request: requests) {
            stringBuilder
                    .append("{")
                    .append("\"id\":").append(request.getId())
                    .append(",\"isApproved\":\"").append(request.isApproved()).append("\"")
                    .append("}, ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private String requestDetailsToJSON(Request request) {
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder
                .append("\"id\":").append(request.getId())
                .append(",\"isApproved\":\"").append(request.isApproved()).append("\"");
        stringBuilder
                .append(", \"user\":{")
                .append("\"id\":").append(request.getUser().getId())
                .append(",\"nickname\":\"").append(request.getUser().getNickname()).append("\"")
                .append(",\"email\":\"").append(request.getUser().getEmail()).append("\"")
                .append("}");
        stringBuilder
                .append(", \"duck\":{")
                .append("\"id\":").append(request.getDuck().getId())
                .append(",\"name\":\"").append(request.getDuck().getName()).append("\"")
                .append("}");
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private User getCurrentUser(String token) {
        String authenticationToken = token.substring(7);
        AuthenticationTokenDetails authenticationTokenDetails = authenticationTokenService.parseToken(authenticationToken);
        return userService.getByNickname(authenticationTokenDetails.getUsername());
    }
}
