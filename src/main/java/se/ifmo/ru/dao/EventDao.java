package se.ifmo.ru.dao;

import se.ifmo.ru.model.Event;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class EventDao {

    private EntityManager entityManager = Persistence.createEntityManagerFactory("persistence").createEntityManager();

    public Event getById(long id) {
        return entityManager.find(Event.class, id);
    }

    public void save(Event event) {
        entityManager.getTransaction().begin();
        entityManager.persist(event);
        entityManager.getTransaction().commit();
    }

    public void delete(Event event) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(event) ? event : entityManager.merge(event));
        entityManager.getTransaction().commit();
    }

    public List<Event> getAll() {
        Query query = entityManager.createQuery("from Event");
        return query.getResultList();
    }

    public List<Event> getByName(String name) {
        Query query = entityManager.createQuery("from Event where name like '%" + name + "%' order by length (name)");
        return query.getResultList();
    }

    public List<Event> getByPlaceId(long placeId) {
        Query query = entityManager.createQuery("from Event where place_id = " + placeId);
        return query.getResultList();
    }
}
