package se.ifmo.ru.ServiceTest;

import org.junit.Test;
import se.ifmo.ru.model.Event;
import se.ifmo.ru.model.Place;
import se.ifmo.ru.model.User;
import se.ifmo.ru.service.EventService;
import se.ifmo.ru.service.PlaceService;
import se.ifmo.ru.service.UserService;
import se.ifmo.ru.util.DateFormatter;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EventServiceTest {

    @Test
    public void eventServiceSaveAndGetByIdWithParticipantsTest() {
        UserService userService = new UserService();
        User user = new User("user", "email@mail.em");
        userService.save(user);
        User user1 = new User("user1", "email1@mail.em");
        userService.save(user1);

        EventService eventService = new EventService();
        Event event = new Event("Another Event");
        event.setDate("12-09-2020-13-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("Place to Be");
        placeService.save(place);
        event.setPlace(place);
        event.setOrganizer(user);
        eventService.save(event);

        event.getParticipants().add(user1);

        Event event1 = eventService.getById(event.getId());
        System.out.println(event1.getId() + " " + event1.getName() + " " + event1.getParticipants().get(0).getNickname());
        assertEquals(eventService.getById(event.getId()), event);
        userService.delete(user1);
        userService.delete(user);
        placeService.delete(place);
    }

    @Test
    public void eventServiceSaveAndGetByIdWithOrganizerTest() {
        UserService userService = new UserService();
        User user = new User("user", "email@mail.em");
        userService.save(user);

        EventService eventService = new EventService();
        Event event = new Event("Another Event");
        event.setDate("12-09-2020-13-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("Place to Be");
        placeService.save(place);
        event.setPlace(place);
        event.setOrganizer(user);

        user.getOrganizedEvents().add(event);
        eventService.save(event);

        Event event1 = eventService.getById(event.getId());
        System.out.println(event1.getId() + " " + event1.getName() + " " + event1.getOrganizer().getNickname());
        assertEquals(eventService.getById(event.getId()), event);
        userService.delete(user);
        placeService.delete(place);
    }


    @Test
    public void eventGetByNameTest() {
        UserService userService = new UserService();
        User user = new User("user", "email@mail.em");
        userService.save(user);

        EventService eventService = new EventService();
        Event event = new Event("My Event");
        event.setDate("12-09-2020-13-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("New place");
        placeService.save(place);
        event.setPlace(place);
        event.setOrganizer(user);
        eventService.save(event);
        Event event2 = new Event("Mysterious Event");
        event2.setDate("12-09-2020-14-00");
        Place place2 = new Place("One more");
        placeService.save(place2);
        event2.setPlace(place2);
        event2.setOrganizer(user);
        eventService.save(event2);
        List<Event> events = eventService.getByName("My");
        for (Event event1 : events) {
            System.out.println(event1.getId() + " " + event1.getName());
        }
        userService.delete(user);
        placeService.delete(place);
        placeService.delete(place2);
    }

    @Test
    public void eventGetByDate() {
        UserService userService = new UserService();
        User user = new User("user", "email@mail.em");
        userService.save(user);

        EventService eventService = new EventService();
        Event event = new Event("My Event");
        event.setDate("12-09-2020-10-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("Super Cafe");
        placeService.save(place);
        event.setPlace(place);
        event.setOrganizer(user);
        eventService.save(event);
        List<Event> events = eventService.getByDate(DateFormatter.stringToDate("12-09-2020-10-00"));
        for (Event event1 : events) {
            System.out.println(event1.getId() + " " + event1.getName() + " " + event1.getDate() + " " + event1.getPlace().getName());
        }
        eventService.delete(event);
        userService.delete(user);
        placeService.delete(place);
    }

    @Test
    public void eventGetByPlaceId() {
        UserService userService = new UserService();
        User user = new User("user", "email@mail.em");
        userService.save(user);

        EventService eventService = new EventService();
        Event event = new Event("My Event");
        event.setDate("12-09-2020-10-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("Super cafe");
        placeService.save(place);
        event.setPlace(place);
        event.setOrganizer(user);
        eventService.save(event);
        List<Event> events = eventService.getByPlaceId(place.getId());
        for (Event event1 : events) {
            Place place1 = event1.getPlace();
            System.out.println(event1.getId() + " " + event1.getName() + " " + place1.getId());
        }
        userService.delete(user);
        placeService.delete(place);
    }

    @Test
    public void eventGetByPlaceIdAndDate() {
        UserService userService = new UserService();
        User user = new User("user", "email@mail.em");
        userService.save(user);

        EventService eventService = new EventService();
        Event event = new Event("My Event");
        event.setDate("12-09-2020-10-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("Super cafe");
        placeService.save(place);
        event.setPlace(place);
        event.setOrganizer(user);
        eventService.save(event);
        Event event1 = eventService.getByPlaceIdAndDate(place.getId(), DateFormatter.stringToDate("12-09-2020-10-00"));
        System.out.println(event1.getId() + " " + event1.getName() + " " + event1.getPlace().getName() + " " + event1.getDate() + " " + event1.getPlace().getName());
        userService.delete(user);
        placeService.delete(place);
    }

}
