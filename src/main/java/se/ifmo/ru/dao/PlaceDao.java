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
    private Place place;

    public Place getPlaceById(long id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        place = session.get(Place.class, id);
        session.close();
        return place;
    }

    public void savePlace(Place place) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(place);
        transaction.commit();
        session.close();
    }

    public void deletePlace(Place place) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(place);
        transaction.commit();
        session.close();
    }

    public void updatePlace(Place place) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.update(place);
        transaction.commit();
        session.close();
    }

    public List<Place> getAllPlaces() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Place");
        List <Place> places = ((org.hibernate.query.Query) query).list();
        return places;
    }
}
