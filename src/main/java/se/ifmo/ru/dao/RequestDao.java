package se.ifmo.ru.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import se.ifmo.ru.model.Duck;
import se.ifmo.ru.model.Request;
import se.ifmo.ru.util.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RequestDao {

    private Session session;
    private Transaction transaction;

    public Request getById(long id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Request request = session.get(Request.class, id);
        session.close();
        return request;
    }

    public Request getByIdWithUserAndDuck(long id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Request r inner join r.user u inner join r.duck d where u.id = r.user.id and d.id = r.duck.id and r.id = " + id);
        return this.handleJoinResult(query).get(0);
    }

    public void save(Request request) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(request);
        transaction.commit();
        session.close();
    }

    public void delete(Request request) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(request);
        transaction.commit();
        session.close();
    }

    public void update(Request request) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.update(request);
        transaction.commit();
        session.close();
    }

    public List<Request> getAll() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Request");
        List <Request> requests = ((org.hibernate.query.Query) query).list();
        return requests;
    }

    public List<Request> getByUserId(long userId) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Request where user_id =" + userId);
        return ((org.hibernate.query.Query) query).list();
    }

    public List<Request> getByDuckId(long duckId) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Request where duck_id =" + duckId);
        return ((org.hibernate.query.Query) query).list();
    }

    public List<Request> getByIsApproved(boolean isApproved) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Request where is_approved =" + isApproved);
        return ((org.hibernate.query.Query) query).list();
    }

    private List<Request> handleJoinResult(Query query) {
        List<Request> requests = new ArrayList<>();
        List<Object[]> objects = ((org.hibernate.query.Query) query).list();
        Iterator iterator = objects.iterator();
        while (iterator.hasNext()) {
            Object[] obj = (Object[]) iterator.next();
            Request request = (Request) obj[0];
            requests.add(request);
        }
        session.close();
        return requests;
    }

}
