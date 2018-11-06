package se.ifmo.ru.service;

import se.ifmo.ru.dao.EventDao;
import se.ifmo.ru.model.Event;

import java.util.Date;
import java.util.List;

public class EventService {

    private EventDao eventDao = new EventDao();

    public Event getById(long id) {
        return eventDao.getById(id);
    }

    public void save(Event event) {
        eventDao.save(event);
    }

    public void update(Event event) {
        eventDao.update(event);
    }

    public void delete(Event event) {
        eventDao.delete(event);
    }

    public List<Event> getAll() {
        return eventDao.getAll();
    }

    //TODO: test
    public List<Event> getByName(String name) {
        return eventDao.getByName(name);
    }

    //TODO: test
    public List<Event> getByPlaceId(long placeId) {
        return eventDao.getByPlaceId(placeId);
    }

    //TODO: test
    public List<Event> getByDate(Date date) {
        return eventDao.getByDate(date);
    }

    //TODO: test
    public Event getByPlaceIdAndDate(long placeId, Date date) {
        return eventDao.getByPlaceIdAndDate(placeId, date);
    }
}
