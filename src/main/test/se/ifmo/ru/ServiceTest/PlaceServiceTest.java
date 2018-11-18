package se.ifmo.ru.ServiceTest;

import org.junit.Test;
import se.ifmo.ru.model.Place;
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
}
