package se.ifmo.ru.ServiceTest;

import org.joda.time.DateTime;
import org.junit.Test;
import se.ifmo.ru.model.Event;
import se.ifmo.ru.model.Place;
import se.ifmo.ru.service.EventService;
import se.ifmo.ru.service.PlaceService;
import se.ifmo.ru.util.DateFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class EventServiceTest {

    @Test
    public void eventServiceSaveAndGetByIdTest() {
        EventService eventService = new EventService();
        Event event = new Event("Another Event");
        event.setDate("12-09-2020-13-00");
        PlaceService placeService = new PlaceService();
        event.setPlace(placeService.getById(20));
        eventService.save(event);
        assertEquals(eventService.getById(event.getId()), event);
        eventService.delete(event);
//        eventService.delete(eventService.getById(28));
    }

    @Test
    public void eventGetByNameTest() {
        EventService eventService = new EventService();
        Event event = new Event("My Event");
        event.setDate("12-09-2020-13-00");
        PlaceService placeService = new PlaceService();
        event.setPlace(placeService.getById(20));
        eventService.save(event);
        Event event2 = new Event("Mysterious Event");
        event2.setDate("12-09-2020-14-00");
        event2.setPlace(placeService.getById(20));
        eventService.save(event2);
        List<Event> events = eventService.getByName("My");
        for (Event event1 : events) {
            System.out.println(event1.getId() + " " + event1.getName());
        }
        eventService.delete(event);
        eventService.delete(event2);
    }

    @Test
    public void eventGetByDate() {
        EventService eventService = new EventService();
        Event event = new Event("My Event");
        event.setDate("12-09-2020-10-00");
        PlaceService placeService = new PlaceService();
        event.setPlace(placeService.getById(20));
        eventService.save(event);
        List<Event> events = eventService.getByDate(DateFormatter.stringToDate("12-09-2020-10-00"));
        for (Event event1 : events) {
            System.out.println(event1.getId() + " " + event1.getName() + " " + event1.getDate());
        }
        eventService.delete(event);
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
            System.out.println(event1.getId() + " " + event1.getName() + " " + place1.getName());
        }
        eventService.delete(event);
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
        System.out.println(event1.getId() + " " + event1.getName() + " " + event1.getPlace().getName() + " " + event1.getDate());
        eventService.delete(event);
    }

}
