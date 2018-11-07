package se.ifmo.ru.ServiceTest;

import org.junit.Test;
import se.ifmo.ru.model.Event;
import se.ifmo.ru.service.EventService;
import se.ifmo.ru.service.PlaceService;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class EventServiceTest {

    @Test
    public void eventServiceSaveTest() {
        EventService eventService = new EventService();
        Event event = new Event("Another Event");
        event.setDate("12-09-2020-13-00");
        PlaceService placeService = new PlaceService();
        event.setPlace(placeService.getById(20));
        eventService.save(event);
        assertEquals(eventService.getById(event.getId()), event);
        eventService.delete(event);
//        eventService.delete(eventService.getById(27));
//        eventService.delete(eventService.getById(28));
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
