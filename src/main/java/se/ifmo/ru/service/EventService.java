package se.ifmo.ru.service;

import org.joda.time.DateTime;
import se.ifmo.ru.dao.EventDao;
import se.ifmo.ru.model.Event;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
     * Returns a Event object contained in the database, field place of which can be accessed, or null if it does not exist
     * @param id - id of the required Event
     * @return - required Event if exists, null if does not exist
     */
    public Event getByIdWithPlace(long id) {
        return eventDao.getByIdWithPlace(id);
    }

    /**
     * Returns a Event object contained in the database, list of participants (Users) of which can be accessed, or null if it does not exist
     * @param id - id of the required Event
     * @return - required Event if exists, null if does not exist
     */
    public Event getByIdWithParticipants(long id) {
        return eventDao.getByIdWithParticipants(id);
    }

    /**
     * Returns a Event object contained in the database, list of organizers (Users) of which can be accessed, or null if it does not exist
     * @param id - id of the required Event
     * @return - required Event if exists, null if does not exist
     */
    public Event getByIdWithOrganizers(long id) {
        return eventDao.getByIdWithOrganizers(id);
    }

    /**
     * Saves a Event object to the database
     * @param event - event to save
     */
    public void save(Event event) {
        eventDao.save(event);
    }

    /**
     * Updates a Event object in the database
     * @param event - event to update
     */
    public void update(Event event) {
        eventDao.update(event);
    }

    /**
     * Deletes a Event object from the database
     * @param event - event to delete
     */
    public void delete(Event event) {
        eventDao.delete(event);
    }

    public List<Event> getAll() {
        return eventDao.getAll();
    }

    public List<Event> getByName(String name) {
        return eventDao.getByName(name);
    }

    public List<Event> getByPlaceId(long placeId) {
        return eventDao.getByPlaceId(placeId);
    }

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
