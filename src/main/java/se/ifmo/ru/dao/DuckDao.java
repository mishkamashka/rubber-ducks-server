package se.ifmo.ru.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import se.ifmo.ru.util.HibernateSessionFactoryUtil;
import se.ifmo.ru.model.Duck;

import javax.persistence.Query;
import java.util.List;

public class DuckDao {

    private Session session;
    private Transaction transaction;
    private Duck duck;

    public Duck getById(long id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        duck = session.get(Duck.class, id);
        session.close();
        return duck;
    }

    public void save(Duck duck) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(duck);
        transaction.commit();
        session.close();
    }

    public void delete(Duck duck) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(duck);
        transaction.commit();
        session.close();
    }

    public void update(Duck duck) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.update(duck);
        transaction.commit();
        session.close();
    }

    public List<Duck> getAll() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Duck");
        List <Duck> ducks = ((org.hibernate.query.Query) query).list();
        return ducks;
    }

    public List<Duck> getByName(String name) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Duck where name like '%" + name + "%' order by length(name)");
        List<Duck> ducks = ((org.hibernate.query.Query) query).list();
        session.close();
        return ducks;
    }

    public List<Duck> getByOwnerId(long ownerId) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Duck where owner_id =" + ownerId);
        List<Duck> ducks = ((org.hibernate.query.Query) query).list();
        session.close();
        return ducks;
    }

}
