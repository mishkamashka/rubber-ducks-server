package se.ifmo.ru.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import se.ifmo.ru.model.Meeting;
import se.ifmo.ru.util.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import java.util.List;

public class MeetingDao {

    private Session session;
    private Transaction transaction;
    private Meeting meeting;

    public Meeting getById(long id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        meeting = session.get(Meeting.class, id);
        session.close();
        return meeting;
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
        Query query = session.createQuery("from Meeting");
        List <Meeting> meetings = ((org.hibernate.query.Query) query).list();
        return meetings;
    }
}
