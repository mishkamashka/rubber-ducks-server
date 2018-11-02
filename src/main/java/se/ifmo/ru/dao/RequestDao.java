package se.ifmo.ru.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import se.ifmo.ru.model.Request;
import se.ifmo.ru.util.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import java.util.List;

public class RequestDao {

    private Session session;
    private Transaction transaction;
    private Request request;

    public Request getRequestById(int id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        request = session.get(Request.class, id);
        session.close();
        return request;
    }

    public void saveRequest(Request request) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(request);
        transaction.commit();
        session.close();
    }

    public void deleteRequest(Request request) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(request);
        transaction.commit();
        session.close();
    }

    public void updateRequest(Request request) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.update(request);
        transaction.commit();
        session.close();
    }

    public List<Request> getAllRequests() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Request");
        List <Request> requests = ((org.hibernate.query.Query) query).list();
        return requests;
    }
}
