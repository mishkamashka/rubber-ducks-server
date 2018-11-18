package se.ifmo.ru.ServiceTest;

import org.junit.Test;
import se.ifmo.ru.model.Event;
import se.ifmo.ru.model.Place;
import se.ifmo.ru.service.EventService;
import se.ifmo.ru.service.PlaceService;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlaceServiceTest {

    @Test
    public void placeServiceSaveAndGetByIdTest() {
        PlaceService placeService = new PlaceService();
        Place place = new Place("Cosy Place");
        placeService.save(place);
        Place place1 = placeService.getById(place.getId());
        assertEquals(place, place1);
        placeService.delete(place1);
    }

    @Test
    public void placeGetByNameTest() {
        PlaceService placeService = new PlaceService();
        List<Place> places = placeService.getByName("My");
        for (Place place: places) {
            System.out.println(place.getId() + " " + place.getName());
        }
    }

    @Test
    public void placeServiceGetByEventTest() {
        EventService eventService = new EventService();
        Event event = new Event("Another Event");
        event.setDate("12-09-2020-13-00");
        PlaceService placeService = new PlaceService();
        Place place = new Place("Place to Be");
        placeService.save(place);
        event.setPlace(place);
        eventService.save(event);

        Place place1 = placeService.getByEvent(event);
        System.out.println(place1.getId() + " " + place1.getName());

        eventService.delete(event);
        placeService.delete(place);
    }
}
