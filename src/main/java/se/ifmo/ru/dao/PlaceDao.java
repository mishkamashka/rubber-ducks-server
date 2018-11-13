package se.ifmo.ru.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import se.ifmo.ru.model.Place;
import se.ifmo.ru.util.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import java.util.List;

public class PlaceDao {

    private Session session;
    private Transaction transaction;

    public Place getById(long id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Place place = session.get(Place.class, id);
        session.close();
        return place;
    }

    public void save(Place place) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(place);
        transaction.commit();
        session.close();
    }

    public void delete(Place place) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(place);
        transaction.commit();
        session.close();
    }

    public void update(Place place) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.update(place);
        transaction.commit();
        session.close();
    }

    public List<Place> getAll() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Place");
        List <Place> places = ((org.hibernate.query.Query) query).list();
        return places;
    }

    public List<Place> getByName(String name) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Place where name like '%" + name + "%' order by length(name)");
        List<Place> places = ((org.hibernate.query.Query) query).list();
        session.close();
        return places;
    }

}
