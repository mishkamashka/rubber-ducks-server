package se.ifmo.ru.service;

import se.ifmo.ru.dao.EventDao;
import se.ifmo.ru.model.Event;

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

}
