package se.ifmo.ru.dao;

import se.ifmo.ru.model.Event;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    public void update(Event event) {
        entityManager.merge(event);
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

    public List<Event> getAllWithPlace() {
        Query query = entityManager.createQuery("select d from Event d join fetch d.place", Event.class);
        return query.getResultList();
    }

    public Event getByIdWithPlace(Long id) {
        Query query = entityManager.createQuery("select d from Event d join fetch d.place where d.id = " + id, Event.class);
        return (Event) query.getSingleResult();
    }

    public Event getByIdWithParticipants(Long id) {
        Query query = entityManager.createQuery("select d from Event d join fetch d.participants where d.id = " + id, Event.class);
        return (Event) query.getSingleResult();
    }
}
