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
        List <Event> events = ((org.hibernate.query.Query) query).list();

//        List<Event> events = new ArrayList<>();
//        List<Object[]> objects = query.getResultList();
//        Iterator iterator = objects.iterator();
//        PlaceService placeService = new PlaceService();
//        while (iterator.hasNext()) {
//            Object[] obj = (Object[]) iterator.next();
//            Event event = new Event();
//            event.setId((Long) obj[0]);
//            event.setCost((double) obj[1]);
//            event.setDate((String) obj[2]);
//            event.setName((String) obj[3]);
//            Place place = placeService.getById((long) obj[4]);
//            event.setPlace(place);
//            events.add(event);
//        }

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
