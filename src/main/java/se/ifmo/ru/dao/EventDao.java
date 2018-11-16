package se.ifmo.ru.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.ifmo.ru.model.Event;
import se.ifmo.ru.util.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventDao {

    private Session session;
    private Transaction transaction;

    public Event getById(long id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Event event = session.get(Event.class, id);
        session.close();
        return event;
    }

    public Event getByIdWithPlace(long id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Event e inner join e.place p where p.id = e.place.id and e.id = " + id);
        return this.handleJoinResult(query).get(0);
    }

    public Event getByIdWithPartcipants(long id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Event event = session.get(Event.class, id);
        Hibernate.initialize(event.getParticipants().size());
        session.close();
        return event;
    }

    public Event getByIdWithOrganizers(long id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Event event = session.get(Event.class, id);
        Hibernate.initialize(event.getOrganizers().size());
        session.close();
        return event;
    }

    public void save(Event event) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(event);
        transaction.commit();
        session.close();
    }

    public void delete(Event event) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(event);
        transaction.commit();
        session.close();
    }

    public void update(Event event) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.update(event);
        transaction.commit();
        session.close();
    }

    public List<Event> getAll() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Event");
        return this.handleJoinResult(query);
    }

    public List<Event> getByName(String name) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Event where name like '%" + name + "%' order by length(e.name)");
        return this.handleJoinResult(query);
    }

    public List<Event> getByPlaceId(long placeId) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Event where place_id = " + placeId);
        return this.handleJoinResult(query);
    }

    private List<Event> handleJoinResult(Query query) {
        List<Event> events = new ArrayList<>();
        List<Object[]> objects = ((org.hibernate.query.Query) query).list();
        Iterator iterator = objects.iterator();
        while (iterator.hasNext()) {
            Object[] obj = (Object[]) iterator.next();
            Event event = (Event) obj[0];
            events.add(event);
        }
        session.close();
        return events;
    }
}
