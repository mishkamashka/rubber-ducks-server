package se.ifmo.ru.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import se.ifmo.ru.model.Meeting;
import se.ifmo.ru.util.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MeetingDao {

    private Session session;
    private Transaction transaction;

    public Meeting getById(long id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Meeting e inner join e.place p where p.id = e.place.id and e.id = " + id);
        return this.handleJoinResult(query).get(0);
    }

    public void save(Meeting meeting) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(meeting);
        transaction.commit();
        session.close();
    }

    public void delete(Meeting meeting) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(meeting);
        transaction.commit();
        session.close();
    }

    public void update(Meeting meeting) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.update(meeting);
        transaction.commit();
        session.close();
    }

    public List<Meeting> getAll() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Meeting e inner join e.place p where p.id = e.place.id");
        return this.handleJoinResult(query);
    }

    public List<Meeting> getByName(String name) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Meeting e inner join e.place p where p.id = e.place.id and e.name like '%" + name + "%' order by length(e.name)");
        return this.handleJoinResult(query);
    }

    public List<Meeting> getByPlaceId(long placeId) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Meeting e inner join e.place p where p.id = e.place.id and place_id =" + placeId);
        return this.handleJoinResult(query);
    }

    private List<Meeting> handleJoinResult(Query query) {
        List<Meeting> meetings = new ArrayList<>();
        List<Object[]> objects = ((org.hibernate.query.Query) query).list();
        Iterator iterator = objects.iterator();
        while (iterator.hasNext()) {
            Object[] obj = (Object[]) iterator.next();
            Meeting meeting = (Meeting) obj[0];
//            Place place = (Place) obj[1];
            meetings.add(meeting);
        }
        session.close();
        return meetings;
    }

}
