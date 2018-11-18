package se.ifmo.ru.service;
import se.ifmo.ru.dao.PlaceDao;
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
    
    public void save(Place place) {
        placeDao.save(place);
    }

    public void update(Place place) {
        placeDao.update(place);
    }

    public void delete(Place place) {
        placeDao.delete(place);
    }

    public List<Place> getAll() {
        return placeDao.getAll();
    }

    public List<Place> getByName(String name) {
        return placeDao.getByName(name);
    }

    //TODO: get by event id, meeting id
}
