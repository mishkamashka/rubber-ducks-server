package se.ifmo.ru.dao;

import se.ifmo.ru.model.Event;
import se.ifmo.ru.model.Place;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PlaceDao {

    @PersistenceContext(unitName = "persistence")
    private EntityManager entityManager;

    public Place getById(long id) {
        return entityManager.find(Place.class, id);
    }

    public void save(Place place) {
        entityManager.persist(place);
    }

    public void delete(Place place) {
        entityManager.remove(entityManager.contains(place) ? place : entityManager.merge(place));
    }

    public void update(Place place) {
        entityManager.merge(place);
    }

    public List<Place> getAll() {
        Query query = entityManager.createQuery("from Place");
        return query.getResultList();
    }

    public List<Place> getByName(String name) {
        Query query = entityManager.createQuery("from Place where name like '%" + name + "%' order by length(name)");
        List<Place> places = ((org.hibernate.query.Query) query).list();
        return places;
    }
}
