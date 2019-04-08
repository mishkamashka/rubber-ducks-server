package se.ifmo.ru.controller;

import org.postgresql.util.PSQLException;
import se.ifmo.ru.auth.Credentials;
import se.ifmo.ru.model.User;
import se.ifmo.ru.security.service.PasswordEncoder;
import se.ifmo.ru.service.UserService;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/register")
public class RegistrationResource {

    @EJB
    private UserService userService;

    private PasswordEncoder passwordEncoder = new PasswordEncoder();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
//    @PermitAll
    @Path("/new")
    public Response register(Credentials credentials) {
        User user = userService.getByNickname(credentials.getUsername());
        if (user != null)
            return Response.status(Response.Status.CONFLICT).build();
        user = new User(credentials.getUsername(), passwordEncoder.hashPassword(credentials.getPassword()));
//        try {
        userService.save(user);
//        } catch (PSQLException e) {
//            e.printStackTrace();
//        }
        return Response.ok().build();
    }
}
