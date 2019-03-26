package se.ifmo.ru.dao;

import se.ifmo.ru.model.Event;
import se.ifmo.ru.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
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
        event.setParticipants(new ArrayList<>());
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

    public List<Event> getByOrganizerId(long ownerId) {
        Query query = entityManager.createQuery("from Event where organizer_id =" + ownerId);
        return query.getResultList();
    }

    public List<Event> getByParticipantId(long participantId) {
        Query query = entityManager.createQuery("from Event ev inner join ev.participants ep on ep.id=" + participantId);
        return query.getResultList();
    }
}
