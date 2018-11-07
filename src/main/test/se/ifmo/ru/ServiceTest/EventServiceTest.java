package se.ifmo.ru.ServiceTest;

import org.junit.Assert;
import org.junit.Test;
import se.ifmo.ru.model.Event;
import se.ifmo.ru.service.EventService;
import se.ifmo.ru.service.PlaceService;

import javax.validation.constraints.AssertTrue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class EventServiceTest {

    @Test
    public void eventServiceSaveTest() {
        EventService eventService = new EventService();
        Event event = new Event("Another Event");
        event.setDate("12-09-2020-13-00");
        PlaceService placeService = new PlaceService();
        event.setPlace(placeService.getById(20));
        eventService.save(event);

    //(eventService.getById(event.getId()).equals(event));
//        eventService.delete(eventService.getById(23));
//        eventService.delete(eventService.getById(25));
//        eventService.delete(eventService.getById(26));
    }

    @Test
    public void eventServiceGetTest() {
        EventService eventService = new EventService();
        Event event = eventService.getById(20);
        System.out.println(event.toString());
    }

    @Test
    public void eventGetByNameTest() {
        EventService eventService = new EventService();
        List<Event> events = eventService.getByName("My");
        for (Event event: events) {
            System.out.println(event.getId() + " " + event.getName());
        }
    }
}
