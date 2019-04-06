package se.ifmo.ru.service;

import org.joda.time.DateTime;
import se.ifmo.ru.dao.EventDao;
import se.ifmo.ru.model.Event;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class EventService {

    private EventDao eventDao = new EventDao();

    /**
     * Returns a Event object contained in the database, or null if it does not exist
     * @param id - id of the required Event
     * @return - required Event if exists, null if does not exist
     */
    public Event getById(long id) {
        return eventDao.getById(id);
    }

    /**
     * Saves a Event object to the database
     * @param event - event to save
     */
    public void save(Event event) {
        eventDao.save(event);
    }

    /**
     * Deletes a Event object from the database
     * @param event - event to delete
     */
    public void delete(Event event) {
        eventDao.delete(event);
    }

    /**
     * Returns list of all Event objects contained in the database
     * @return - list of all events
     */
    public List<Event> getAll() {
        return eventDao.getAll();
    }

    /**
     * Returns list of events, names of which contain a substring equal to the parameter, ordered from the best match to the worst
     * @param name - substring to find among names
     * @return - list of places or null, if nothing found
     */
    public List<Event> getByName(String name) {
        return eventDao.getByName(name);
    }

    /**
     * Returns list of events held at the certain place
     * @param placeId - id of the place, at which events are held
     * @return - list of events or null, if nothing found
     */
    public List<Event> getByPlaceId(long placeId) {
        return eventDao.getByPlaceId(placeId);
    }

    /**
     * Returns list of events held on the certain date
     * @param date - date of the required events
     * @return - list of events or null, if nothing found
     */
    public List<Event> getByDate(Date date) {
        EventService eventService = new EventService();
        List<Event> events = eventService.getAll();
        List<Event> result = new ArrayList<>();
        DateTime reqDate = new DateTime(date);
        DateTime eventDate;
        for (Event event1: events) {
            eventDate = new DateTime(event1.getDate());
            if (reqDate.equals(eventDate)) {
                result.add(event1);
            }
        }
        return result;
    }

    /**
     * Returns list of events held at the certain place and on the certain date
     * @param placeId - id of the place, at which events are held
     * @param date - date of the required events
     * @return - list of events or null, if nothing found
     */
    public Event getByPlaceIdAndDate(long placeId, Date date) {
        EventService eventService = new EventService();
        List<Event> events = eventService.getAll();
        List<Event> result = new ArrayList<>();
        DateTime reqDate = new DateTime(date);
        DateTime eventDate;
        for (Event event1: events) {
            eventDate = new DateTime(event1.getDate());
            if (reqDate.equals(eventDate) && event1.getPlace().getId() == placeId) {
                result.add(event1);
            }
        }
        return result.size() > 0 ? result.get(0) : null;
    }
}
