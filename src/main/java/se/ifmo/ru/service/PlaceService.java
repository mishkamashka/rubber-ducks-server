package se.ifmo.ru.service;
import se.ifmo.ru.dao.PlaceDao;
import se.ifmo.ru.model.Event;
import se.ifmo.ru.model.Place;

import java.util.List;

public class PlaceService {

    private PlaceDao placeDao = new PlaceDao();

    /**
     * Returns a Place object contained in the database, or null if it does not exist
     * @param id - id of the required Place
     * @return - required Place if exists, null if does not exist
     */
    public Place getById(long id) {
        return placeDao.getById(id);
    }

    /**
     * Saves a Place object to the database
     * @param place - place to save
     */
    public void save(Place place) {
        placeDao.save(place);
    }

    /**
     * Updates a Place object in the database
     * @param place - place to update
     */
    public void update(Place place) {
        placeDao.update(place);
    }

    /**
     * Deletes a Place object from the database
     * @param place - place to delete
     */
    public void delete(Place place) {
        placeDao.delete(place);
    }

    /**
     * Returns list of all Request objects contained in the database
     * @return - list of all requests
     */
    public List<Place> getAll() {
        return placeDao.getAll();
    }

    /**
     * Returns list of places, names of which contain a substring equal to the parameter, ordered from the best match to the worst
     * @param name - substring to find among names
     * @return - list of places or null, if nothing found
     */
    public List<Place> getByName(String name) {
        return placeDao.getByName(name);
    }

    /**
     * Returns a Place object id of which is equal to the place_id field in Event object passed as a parameter
     * @param event - event place of which is required
     * @return - place or null, if nothing found
     */
    public Place getByEvent(Event event) {
        EventService eventService = new EventService();
        event = eventService.getByIdWithPlace(event.getId());
        return event.getPlace();
    }
}
