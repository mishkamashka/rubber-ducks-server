package se.ifmo.ru.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import se.ifmo.ru.util.HibernateSessionFactoryUtil;
import se.ifmo.ru.model.Duck;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DuckDao {

    private Session session;
    private Transaction transaction;

    public Duck getById(long id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Duck duck = session.get(Duck.class, id);
        session.close();
        return duck;
    }

    public Duck getByIdWithOwnerAndFeatureSet(long id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Duck d inner join d.owner o inner join d.featureSet f where o.id = d.owner.id and f.id = d.featureSet.id and d.id = " + id);
        return this.handleJoinResult(query).get(0);
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
        return ((org.hibernate.query.Query) query).list();
    }

    public List<Duck> getAllWithOwnerAndFeatureSet() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Duck d inner join d.owner o inner join d.featureSet f where o.id = d.owner.id and f.id = d.featureSet.id");
        return this.handleJoinResult(query);
    }

    public List<Duck> getByName(String name) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Duck where name like '%" + name + "%' order by length(name)");
        return ((org.hibernate.query.Query) query).list();
    }

    public List<Duck> getByNameWithOwnerAndFeatureSet(String name) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Duck d inner join d.owner o inner join d.featureSet f where o.id = d.owner.id and f.id = d.featureSet.id and d.name like '%" + name + "%' order by length(d.name)");
        return this.handleJoinResult(query);
    }

    public List<Duck> getByOwnerId(long ownerId) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Duck where owner_id =" + ownerId);
        return ((org.hibernate.query.Query) query).list();
    }

    private List<Duck> handleJoinResult(Query query) {
        List<Duck> ducks = new ArrayList<>();
        List<Object[]> objects = ((org.hibernate.query.Query) query).list();
        Iterator iterator = objects.iterator();
        while (iterator.hasNext()) {
            Object[] obj = (Object[]) iterator.next();
            Duck duck = (Duck) obj[0];
            ducks.add(duck);
        }
        session.close();
        return ducks;
    }

}
