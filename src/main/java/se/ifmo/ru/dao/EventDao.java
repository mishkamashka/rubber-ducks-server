package se.ifmo.ru.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import se.ifmo.ru.model.Event;
import se.ifmo.ru.model.Place;
import se.ifmo.ru.service.PlaceService;
import se.ifmo.ru.util.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventDao {

    private Session session;
    private Transaction transaction;
    private Event event;

    public Event getById(long id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        event = session.get(Event.class, id);
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
        Query query = session.createQuery("from Event e inner join e.place p where p.id = e.place.id");
        List<Event> events = new ArrayList<>();
        List<Object[]> objects = ((org.hibernate.query.Query) query).list();
        Iterator iterator = objects.iterator();
        while (iterator.hasNext()) {
            Object[] obj = (Object[]) iterator.next();
            Event event = (Event) obj[0];
//            Place place = (Place) obj[1];
            events.add(event);
        }
        return events;
    }

    public List<Event> getByName(String name) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Event e where e.name like '%" + name + "%' order by length(e.name)");
        List<Event> events = ((org.hibernate.query.Query) query).list();
        session.close();
        return events;
    }

    public List<Event> getByPlaceId(long placeId) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Event where place_id =" + placeId);
        List<Event> events = ((org.hibernate.query.Query) query).list();
        session.close();
        return events;
    }

}
