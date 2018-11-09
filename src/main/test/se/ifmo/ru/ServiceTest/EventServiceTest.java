package se.ifmo.ru.ServiceTest;

import org.junit.Test;
import se.ifmo.ru.model.Event;
import se.ifmo.ru.model.Place;
import se.ifmo.ru.service.EventService;
import se.ifmo.ru.service.PlaceService;
import se.ifmo.ru.util.DateFormatter;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class EventServiceTest {

    @Test
    public void eventServiceSaveTest() {
        EventService eventService = new EventService();
        Event event = new Event("Duck Race");
        event.setDate("12-11-2020-15-00");
        PlaceService placeService = new PlaceService();
        event.setPlace(placeService.getById(59));
        eventService.save(event);
        assertEquals(eventService.getById(event.getId()), event);
        eventService.delete(event);
    }

    @Test
    public void eventServiceSaveAndGetByIdTest() {
        EventService eventService = new EventService();
        Event event = new Event("Another Event");
        event.setDate("12-09-2020-13-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("Place to Be");
        placeService.save(place);
        event.setPlace(place);
        eventService.save(event);
        assertEquals(eventService.getById(event.getId()), event);
        eventService.delete(event);
    }

    @Test
    public void eventGetByNameTest() {
        EventService eventService = new EventService();
        Event event = new Event("My Event");
        event.setDate("12-09-2020-13-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("New place");
        placeService.save(place);
        event.setPlace(place);
        eventService.save(event);
        Event event2 = new Event("Mysterious Event");
        event2.setDate("12-09-2020-14-00");
        Place place2 = new Place("One more");
        placeService.save(place2);
        event2.setPlace(place2);
        eventService.save(event2);
        List<Event> events = eventService.getByName("My");
        for (Event event1 : events) {
            System.out.println(event1.getId() + " " + event1.getName());
        }
        eventService.delete(event);
        eventService.delete(event2);
        placeService.delete(place);
        placeService.delete(place2);
    }

    @Test
    public void eventGetByDate() {
        EventService eventService = new EventService();
        Event event = new Event("My Event");
        event.setDate("12-09-2020-10-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("Super Cafe");
        placeService.save(place);
        event.setPlace(place);
        eventService.save(event);
        List<Event> events = eventService.getByDate(DateFormatter.stringToDate("12-09-2020-10-00"));
        for (Event event1 : events) {
            System.out.println(event1.getId() + " " + event1.getName() + " " + event1.getDate() + " " + event1.getPlace().getName());
        }
        eventService.delete(event);
        placeService.delete(place);
    }

    @Test
    public void eventGetAll() {
        EventService eventService = new EventService();
        Event event = new Event("My Event");
        event.setDate("12-09-2020-10-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("Super cafe");
        placeService.save(place);
        event.setPlace(place);
        eventService.save(event);
        List<Event> events = eventService.getAll();
        for (Event event1 : events) {
            Place place1 = event1.getPlace();
            System.out.println(event1.getId() + " " + event1.getName() + " " + place1.getId());
        }
        eventService.delete(event);
        placeService.delete(place);
    }

    @Test
    public void eventGetByPlaceId() {
        EventService eventService = new EventService();
        Event event = new Event("My Event");
        event.setDate("12-09-2020-10-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("Super cafe");
        placeService.save(place);
        event.setPlace(place);
        eventService.save(event);
        List<Event> events = eventService.getByPlaceId(place.getId());
        for (Event event1 : events) {
            Place place1 = event1.getPlace();
            System.out.println(event1.getId() + " " + event1.getName() + " " + place1.getId());
        }
        eventService.delete(event);
        placeService.delete(place);
    }

    @Test
    public void eventGetByPlaceIdAndDate() {
        EventService eventService = new EventService();
        Event event = new Event("My Event");
        event.setDate("12-09-2020-10-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("Super cafe");
        placeService.save(place);
        event.setPlace(place);
        eventService.save(event);
        Event event1 = eventService.getByPlaceIdAndDate(place.getId(), DateFormatter.stringToDate("12-09-2020-10-00"));
        System.out.println(event1.getId() + " " + event1.getName() + " " + event1.getPlace().getName() + " " + event1.getDate() + " " + event1.getPlace().getName());
        eventService.delete(event);
    }

}
