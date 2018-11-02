package se.ifmo.ru.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import se.ifmo.ru.model.Event;
import se.ifmo.ru.util.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import java.util.List;

public class EventDao {

    private Session session;
    private Transaction transaction;
    private Event event;

    public Event getEventById(int id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        event = session.get(Event.class, id);
        session.close();
        return event;
    }

    public void saveEvent(Event event) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(event);
        transaction.commit();
        session.close();
    }

    public void deleteEvent(Event event) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(event);
        transaction.commit();
        session.close();
    }

    public void updateEvent(Event event) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.update(event);
        transaction.commit();
        session.close();
    }

    public List<Event> getAllEvents() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Event");
        List <Event> events = ((org.hibernate.query.Query) query).list();
        return events;
    }
}
