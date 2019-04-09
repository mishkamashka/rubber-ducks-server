package se.ifmo.ru.controller;

import se.ifmo.ru.auth.Secured;
import se.ifmo.ru.model.Duck;
import se.ifmo.ru.model.FeatureSet;
import se.ifmo.ru.model.User;
import se.ifmo.ru.security.api.AuthenticationTokenDetails;
import se.ifmo.ru.security.domain.Authority;
import se.ifmo.ru.security.service.AuthenticationTokenService;
import se.ifmo.ru.service.DuckService;
import se.ifmo.ru.service.UserService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/ducks")
public class DuckResource {

    @EJB
    private UserService userService;

    @EJB
    private DuckService duckService;

    @Context
    private SecurityContext securityContext;

    @EJB
    private AuthenticationTokenService authenticationTokenService;

    @POST
    @Secured(Authority.USER)
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users/all")
    public Response getUserDucks(String nickname) {
        User user = userService.getByNickname(nickname);
        List<Duck> ducks = duckService.getByOwnerId(user.getId());
        if (ducks == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        String json = ducksToJSON(ducks);
        return Response.ok(json).build();
    }

    @GET
    @Secured(Authority.USER)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/details/{id}")
    public Response getDuckDetails(@PathParam("id") Long id) {
        Duck duck = duckService.getByIdWithFeatureSet(id);
        if (duck == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        String json = duckDetailsToJSON(duck);
        return Response.ok(json).build();
    }

    @POST
    @Secured(Authority.USER)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/new")
    public Response addDuck(@HeaderParam("Authorization") String token, Duck duck) {
        User user = getCurrentUser(token);
        duck.setOwner(user);
        duckService.save(duck);
        return Response.ok().build();
    }

    @GET
    @Secured(Authority.USER)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete/{id}")
    public Response deleteDuck(@PathParam("id") Long id) {
        Duck duck = duckService.getByIdWithFeatureSet(id);
        duckService.delete(duck);
        if (duck == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        return Response.ok().build();
    }

    @GET
    @Secured(Authority.USER)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllDucks() {
        List<Duck> ducks = duckService.getAll();
        if (ducks == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        String json = ducksToJSON(ducks);
        return Response.ok(json).build();
    }

    private String ducksToJSON(List<Duck> ducks) {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (Duck duck : ducks) {
            stringBuilder
                    .append("{")
                    .append("\"id\":").append(duck.getId())
                    .append(",\"name\":\"").append(duck.getName()).append("\"")
                    .append(",\"description\":\"").append(duck.getDescription()).append("\"")
                    .append(",\"accessibility\":\"").append(duck.isAccessible()).append("\"")
                    .append("}, ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private String duckDetailsToJSON(Duck duck) {
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder
                .append("\"id\":").append(duck.getId())
                .append(",\"name\":\"").append(duck.getName()).append("\"")
                .append(",\"description\":\"").append(duck.getDescription()).append("\"")
                .append(",\"accessibility\":\"").append(duck.isAccessible()).append("\"")
                .append(",\"gender\":\"").append(duck.getFeatureSet().getGender()).append("\"")
                .append(",\"colour\":\"").append(duck.getFeatureSet().getColour()).append("\"")
                .append(",\"beakColour\":\"").append(duck.getFeatureSet().getBeakColour()).append("\"")
                .append(",\"length\":").append(duck.getFeatureSet().getLength())
                .append(",\"weigh\":").append(duck.getFeatureSet().getWeight())
                .append(",\"swimmingSkill\":").append(duck.getFeatureSet().getSwimmingSkill());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private User getCurrentUser(String token) {
        String authenticationToken = token.substring(7);
        AuthenticationTokenDetails authenticationTokenDetails = authenticationTokenService.parseToken(authenticationToken);
        return userService.getByNickname(authenticationTokenDetails.getUsername());
    }

}
