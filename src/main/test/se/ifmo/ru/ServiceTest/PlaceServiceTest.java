package se.ifmo.ru.ServiceTest;

import org.junit.Test;
import se.ifmo.ru.model.Place;
import se.ifmo.ru.service.PlaceService;

import java.util.List;

public class PlaceServiceTest {

    @Test
    public void placeServiceSaveTest() {
        PlaceService placeService = new PlaceService();
        Place place = new Place("My Place");
        placeService.save(place);
    }

    @Test
    public void placeServiceGetTest() {
        PlaceService placeService = new PlaceService();
        Place place = placeService.getById(20);
        System.out.println(place.toString());
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
