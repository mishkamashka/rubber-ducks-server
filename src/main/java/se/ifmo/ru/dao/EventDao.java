package se.ifmo.ru.dao;

import se.ifmo.ru.model.Event;
import se.ifmo.ru.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EventDao {

    @PersistenceContext(unitName = "persistence")
    private EntityManager entityManager;

    public Event getById(long id) {
        return entityManager.find(Event.class, id);
    }

    public void save(Event event) {
        entityManager.persist(event);
    }

    public void delete(Event event) {
        entityManager.remove(entityManager.contains(event) ? event : entityManager.merge(event));
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
