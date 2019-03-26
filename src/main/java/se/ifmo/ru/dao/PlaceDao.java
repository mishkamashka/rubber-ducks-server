package se.ifmo.ru.dao;

import se.ifmo.ru.model.Event;
import se.ifmo.ru.model.Place;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class PlaceDao {

    private EntityManager entityManager = Persistence.createEntityManagerFactory("persistence").createEntityManager();
    private EventDao eventDao = new EventDao();

    public Place getById(long id) {
        return entityManager.find(Place.class, id);
    }

    public void save(Place place) {
        entityManager.getTransaction().begin();
        entityManager.persist(place);
        entityManager.getTransaction().commit();
    }

    public void delete(Place place) {
        entityManager.getTransaction().begin();
        List<Event> events = eventDao.getByPlaceId(place.getId());
        events.forEach(request -> eventDao.delete(request));
        place.setEvents(new ArrayList<>());
        entityManager.remove(entityManager.contains(place) ? place : entityManager.merge(place));
        entityManager.getTransaction().commit();
    }

    public List<Place> getAll() {
        Query query = entityManager.createQuery("from Place");
        return query.getResultList();
    }

    public List<Place> getByName(String name) {
        Query query = entityManager.createQuery("from Place where name like '%" + name + "%' order by length(name)");
        List<Place> places = ((org.hibernate.query.Query) query).list();
        return places;
    }
}
