package se.ifmo.ru.controller;

import se.ifmo.ru.auth.Secured;
import se.ifmo.ru.model.Event;
import se.ifmo.ru.model.Place;
import se.ifmo.ru.model.User;
import se.ifmo.ru.security.api.AuthenticationTokenDetails;
import se.ifmo.ru.security.domain.Authority;
import se.ifmo.ru.security.service.AuthenticationTokenService;
import se.ifmo.ru.service.EventService;
import se.ifmo.ru.service.PlaceService;
import se.ifmo.ru.service.UserService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

/**
 * Better not to use update here for a while
 */

@Path("/events")
public class EventResource {

    @EJB
    private UserService userService;

    @EJB
    private EventService eventService;

    @EJB
    private PlaceService placeService;

    @Context
    private SecurityContext securityContext;

    @EJB
    private AuthenticationTokenService authenticationTokenService;


    @POST
    @Secured(Authority.USER)
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users/all")
    public Response getUserEvents(String nickname) {
        User user = userService.getByNickname(nickname);
        List<Event> event = eventService.getByOrganizerId(user.getId());
        if (event == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        String json = eventsToJSON(event);
        return Response.ok(json).build();
    }

    @GET
    @Secured(Authority.USER)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/details/{id}")
    public Response getEventDetails(@PathParam("id") Long id) {
        Event event = eventService.getByIdWithPlace(id);
        if (event == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        String json = eventDetailsToJSON(event);
        return Response.ok(json).build();
    }

    @POST
    @Secured(Authority.USER)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    public Response updateDuckDetails(@PathParam("id") Long id, Event newEvent) {

        Event oldEvent = eventService.getByIdWithPlace(id);
        if (oldEvent == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        Place place = oldEvent.getPlace();
        place.setName(newEvent.getPlace().getName());
        place.setCity(newEvent.getPlace().getCity());
        place.setCountry(newEvent.getPlace().getCountry());
        place.setStreet(newEvent.getPlace().getStreet());
        place.setBuiding(newEvent.getPlace().getBuiding());
        place.setBuildingLetter(newEvent.getPlace().getBuildingLetter());

        oldEvent.setName(newEvent.getName());
        oldEvent.setDate(newEvent.getDate().toString()); //TODO: doesn't work
        oldEvent.setCost(newEvent.getCost());
        oldEvent.setMaxPeople(newEvent.getMaxPeople());

        placeService.update(oldEvent.getPlace());
        eventService.update(oldEvent);

        return Response.ok("event's details have been updated").build();
    }

    @POST
    @Secured(Authority.USER)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/new")
    public Response addEvent(@HeaderParam("Authorization") String token, Event event) {
        //TODO: places are always added as new ones and never deleted
        if (event.getPlace() != null)
            placeService.save(event.getPlace());
        User user = getCurrentUser(token);
        event.setOrganizer(user);
        eventService.save(event);
        return Response.ok("event has been added").build();
    }

    @GET
    @Secured(Authority.USER)
    @Path("/delete/{id}")
    public Response deleteEvent(@PathParam("id") Long id) {
        Event event = eventService.getByIdWithPlace(id);
        eventService.delete(event);
        if (event == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        return Response.ok("event has been deleted").build();
    }

    @GET
    @Secured(Authority.USER)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllEvents() {
        List<Event> events = eventService.getAll();
        if (events == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        String json = eventsToJSON(events);
        return Response.ok(json).build();
    }

    private String eventsToJSON(List<Event> events) {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (Event event : events) {
            stringBuilder
                    .append("{")
                    .append("\"id\":").append(event.getId())
                    .append(",\"name\":\"").append(event.getName()).append("\"")
                    .append(",\"date\":\"").append(event.getDate()).append("\"")
                    .append(",\"cost\":").append(event.getCost())
                    .append(",\"isClubOnly\":\"").append(event.isClubOnly()).append("\"")
                    .append(",\"maxPeople\":").append(event.getMaxPeople())
                    .append("}, ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private String eventDetailsToJSON(Event event) {
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder
                .append("\"id\":").append(event.getId())
                .append(",\"name\":\"").append(event.getName()).append("\"")
                .append(",\"date\":\"").append(event.getDate()).append("\"")
                .append(",\"cost\":").append(event.getCost())
                .append(",\"isClubOnly\":\"").append(event.isClubOnly()).append("\"")
                .append(",\"maxPeople\":").append(event.getMaxPeople());
        stringBuilder
                .append(", \"place\":{")
                .append("\"id\":").append(event.getPlace().getId())
                .append(",\"name\":\"").append(event.getPlace().getName()).append("\"")
                .append(",\"city\":\"").append(event.getPlace().getCity()).append("\"")
                .append(",\"country\":\"").append(event.getPlace().getCountry()).append("\"")
                .append(",\"street\":\"").append(event.getPlace().getStreet()).append("\"")
                .append(",\"building\":").append(event.getPlace().getBuiding())
                .append(",\"buildingLetter\":\"").append(event.getPlace().getBuildingLetter()).append("\"")
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
