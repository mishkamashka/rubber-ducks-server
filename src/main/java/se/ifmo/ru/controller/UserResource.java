package se.ifmo.ru.controller;

import se.ifmo.ru.auth.Secured;
import se.ifmo.ru.model.User;
import se.ifmo.ru.security.api.AuthenticationTokenDetails;
import se.ifmo.ru.security.domain.Authority;
import se.ifmo.ru.security.service.AuthenticationTokenService;
import se.ifmo.ru.service.UserService;
import se.ifmo.ru.util.DateFormatter;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
public class UserResource {

    @EJB
    private UserService userService;

    @EJB
    private AuthenticationTokenService authenticationTokenService;

    @GET
    @Secured(Authority.ADMIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllDucks() {
        List<User> users = userService.getAll();
        if (users == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        String json = usersToJSON(users);
        return Response.ok(json).build();
    }

    @GET
    @Secured({Authority.USER, Authority.ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/me")
    public Response getMe(@HeaderParam("Authorization") String token) {
        String json = userToJSON(this.getCurrentUser(token));
        return Response.ok(json).build();
    }

    @POST
    @Secured({Authority.USER, Authority.ADMIN})
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    public Response updateUser(@PathParam("id") Long id, User newUser) {
        User oldUser = userService.getById(id);
        if (oldUser == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setLastName(newUser.getLastName());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPhoneNumber(newUser.getPhoneNumber());
        oldUser.setBirthDate(DateFormatter.dateToSQLString(newUser.getBirthDate()));
        oldUser.setCountry(newUser.getCountry());
        oldUser.setCity(newUser.getCity());
        oldUser.setStreet(newUser.getStreet());
        oldUser.setBuilding(newUser.getBuilding());
        oldUser.setBuildingLetter(newUser.getBuildingLetter());

        userService.update(oldUser);

        return Response.ok("user's details have been updated").build();
    }

    @GET
    @Secured({Authority.USER, Authority.ADMIN})
    @Path("/delete/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        User user = userService.getById(id);
        userService.delete(user);
        if (user == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        return Response.ok("user has been deleted").build();
    }

    private String usersToJSON(List<User> users) {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (User user : users) {
            StringBuilder image = new StringBuilder();
            if (user.getImage() != null) {
                byte[] b = user.getImage();
                for (int i = 0; i < b.length; i++) {
                    image.append(b);
                }
            }
            stringBuilder
                    .append("{")
                    .append("\"id\":").append(user.getId())
                    .append(",\"nickname\":\"").append(user.getNickname()).append("\"")
                    .append(",\"firstName\":\"").append(user.getFirstName()).append("\"")
                    .append(",\"lastName\":\"").append(user.getLastName()).append("\"")
                    .append(",\"gender\":\"").append(user.getGender()).append("\"")
                    .append(",\"email\":\"").append(user.getEmail()).append("\"")
                    .append(",\"image\":\"").append(image.toString()).append("\"")
                    .append(",\"birthDate\":\"").append(user.getBirthDate()).append("\"")
                    .append(",\"phoneNumber\":\"").append(user.getPhoneNumber()).append("\"")
                    .append(",\"country\":\"").append(user.getCountry()).append("\"")
                    .append(",\"city\":\"").append(user.getCity()).append("\"")
                    .append(",\"street\":\"").append(user.getStreet()).append("\"")
                    .append(",\"building\":").append(user.getBuilding())
                    .append(",\"buildingLetter\":\"").append(user.getBuildingLetter()).append("\"")
                    .append("}, ");
        }
        if (stringBuilder.length() > 5)
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private String userToJSON(User user) {
        StringBuilder image = new StringBuilder();
        if (user.getImage() != null) {
            byte[] b = user.getImage();
            for (int i = 0; i < b.length; i++) {
                image.append(b);
            }
        }
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder
                .append("\"id\":").append(user.getId())
                .append(",\"nickname\":\"").append(user.getNickname()).append("\"")
                .append(",\"firstName\":\"").append(user.getFirstName()).append("\"")
                .append(",\"lastName\":\"").append(user.getLastName()).append("\"")
                .append(",\"gender\":\"").append(user.getGender()).append("\"")
                .append(",\"email\":\"").append(user.getEmail()).append("\"")
                .append(",\"image\":\"").append(image.toString()).append("\"")
                .append(",\"birthDate\":\"").append(user.getBirthDate()).append("\"")
                .append(",\"phoneNumber\":\"").append(user.getPhoneNumber()).append("\"")
                .append(",\"country\":\"").append(user.getCountry()).append("\"")
                .append(",\"city\":\"").append(user.getCity()).append("\"")
                .append(",\"street\":\"").append(user.getStreet()).append("\"")
                .append(",\"building\":").append(user.getBuilding())
                .append(",\"buildingLetter\":\"").append(user.getBuildingLetter()).append("\"")
                .append("}");
        return stringBuilder.toString();
    }

    private User getCurrentUser(String token) {
        String authenticationToken = token.substring(7);
        AuthenticationTokenDetails authenticationTokenDetails = authenticationTokenService.parseToken(authenticationToken);
        return userService.getByNickname(authenticationTokenDetails.getUsername());
    }
}
