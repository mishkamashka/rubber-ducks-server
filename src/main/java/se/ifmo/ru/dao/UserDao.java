package se.ifmo.ru.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import se.ifmo.ru.util.HibernateSessionFactoryUtil;
import se.ifmo.ru.model.User;
import javax.persistence.Query;
import java.util.List;

public class UserDao {

    private Session session;
    private Transaction transaction;
    private User user;

    public User getById(long id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        user = session.get(User.class, id);
        session.close();
        return user;
    }

    public void save(User user) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public void delete(User user) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    public void update(User user) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    public List<User> getAll() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User");
        List <User> users = ((org.hibernate.query.Query) query).list();
        return users;
    }

}