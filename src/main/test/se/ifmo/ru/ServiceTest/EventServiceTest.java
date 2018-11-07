package se.ifmo.ru.ServiceTest;

import org.junit.Test;
import se.ifmo.ru.model.Event;
import se.ifmo.ru.service.EventService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class EventServiceTest {

    @Test
    public void eventServiceSaveTest() {
        EventService eventService = new EventService();
        Event event = new Event("My Event");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        try {
            Date date = dateFormat.parse("12-09-2019 12:00");
            event.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        eventService.save(event);
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
