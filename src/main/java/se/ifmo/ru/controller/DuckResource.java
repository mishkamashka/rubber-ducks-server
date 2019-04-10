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
    @Secured({Authority.USER, Authority.ADMIN})
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
    @Secured({Authority.USER, Authority.ADMIN})
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
    @Secured({Authority.USER, Authority.ADMIN})
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    public Response updateDuckDetails(@PathParam("id") Long id, Duck newDuck) {

        Duck oldDuck = duckService.getByIdWithFeatureSet(id);
        if (oldDuck == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        FeatureSet featureSet = oldDuck.getFeatureSet();
        featureSet.setBeakColour(newDuck.getFeatureSet().getBeakColour());
        featureSet.setColour(newDuck.getFeatureSet().getColour());
        featureSet.setGender(newDuck.getFeatureSet().getGender());
        featureSet.setLength(newDuck.getFeatureSet().getLength());
        featureSet.setWeight(newDuck.getFeatureSet().getWeight());
        featureSet.setSwimmingSkill(newDuck.getFeatureSet().getSwimmingSkill());
        oldDuck.setName(newDuck.getName());
        oldDuck.setAccessibility(newDuck.isAccessible());
        oldDuck.setDescription(newDuck.getDescription());

        duckService.update(oldDuck);

        return Response.ok("duck's details have been updated").build();
    }

    @POST
    @Secured({Authority.USER, Authority.ADMIN})
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/new")
    public Response addDuck(@HeaderParam("Authorization") String token, Duck duck) {
        User user = getCurrentUser(token);
        duck.setOwner(user);
        duckService.save(duck);
        return Response.ok("duck has been added").build();
    }

    @GET
    @Secured({Authority.USER, Authority.ADMIN})
    @Path("/delete/{id}")
    public Response deleteDuck(@PathParam("id") Long id) {
        Duck duck = duckService.getByIdWithFeatureSet(id);
        duckService.delete(duck);
        if (duck == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        return Response.ok("duck has been deleted").build();
    }

    @GET
    @Secured({Authority.USER, Authority.ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllDucks() {
        List<Duck> ducks = duckService.getAll();
        if (ducks == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        String json = ducksToJSON(ducks);
        return Response.ok(json).build();
    }

    @GET
    @Secured({Authority.USER, Authority.ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/accessible")
    public Response getAllAccessibleDucks() {
        List<Duck> ducks = duckService.getAccessible();
        if (ducks == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        String json = ducksToJSON(ducks);
        return Response.ok(json).build();
    }

    @GET
    @Secured({Authority.USER, Authority.ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{name}")
    public Response getByName(@PathParam("name") String name) {
        List<Duck> ducks = duckService.getByName(name);
        if (ducks == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        String json = ducksToJSON(ducks);
        return Response.ok(json).build();
    }


    private String ducksToJSON(List<Duck> ducks) {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (Duck duck : ducks) {
            byte[] b = duck.getImage();
            StringBuilder image = new StringBuilder();
            if (b != null)
                for (int i = 0; i < b.length; i++) {
                    image.append(b);
                }
            stringBuilder
                    .append("{")
                    .append("\"id\":").append(duck.getId())
                    .append(",\"name\":\"").append(duck.getName()).append("\"")
                    .append(",\"description\":\"").append(duck.getDescription()).append("\"")
                    .append(",\"accessibility\":\"").append(duck.isAccessible()).append("\"")
                    .append(",\"image\":\"").append(image.toString()).append("\"")
                    .append("}, ");
        }
        if (stringBuilder.length() > 5)
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private String duckDetailsToJSON(Duck duck) {
        StringBuilder image = new StringBuilder();
        byte[] b = duck.getImage();
        if (b != null)
            for (int i = 0; i < b.length; i++) {
                image.append(b);
            }
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder
                .append("\"id\":").append(duck.getId())
                .append(",\"name\":\"").append(duck.getName()).append("\"")
                .append(",\"description\":\"").append(duck.getDescription()).append("\"")
                .append(",\"accessibility\":\"").append(duck.isAccessible()).append("\"")
                .append(",\"image\":\"").append(image.toString()).append("\"");
        stringBuilder
                .append(", \"featureSet\":{")
                .append("\"gender\":\"").append(duck.getFeatureSet().getGender()).append("\"")
                .append(",\"colour\":\"").append(duck.getFeatureSet().getColour()).append("\"")
                .append(",\"beakColour\":\"").append(duck.getFeatureSet().getBeakColour()).append("\"")
                .append(",\"length\":").append(duck.getFeatureSet().getLength())
                .append(",\"weight\":").append(duck.getFeatureSet().getWeight())
                .append(",\"swimmingSkill\":").append(duck.getFeatureSet().getSwimmingSkill())
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
