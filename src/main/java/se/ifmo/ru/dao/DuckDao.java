package se.ifmo.ru.dao;

import se.ifmo.ru.model.Duck;
import se.ifmo.ru.model.Request;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DuckDao {

    @PersistenceContext(unitName = "persistence")
    private EntityManager entityManager;

    private RequestDao requestDao = new RequestDao();

    public Duck getById(long id) {
        return entityManager.find(Duck.class, id);
    }

    public void save(Duck duck) {
        entityManager.persist(duck);
    }

    public void delete(Duck duck) {
        entityManager.remove(entityManager.contains(duck) ? duck : entityManager.merge(duck));
    }

    public void update(Duck duck) {
        entityManager.merge(duck);
    }

    public List<Duck> getAll() {
        Query query = entityManager.createQuery("from Duck");
        return query.getResultList();
    }

    public List<Duck> getByName(String name) {
        Query query = entityManager.createQuery("from Duck where name like '%" + name + "%' order by length(name)");
        return query.getResultList();
    }

    public List<Duck> getByOwnerId(long ownerId) {
        Query query = entityManager.createQuery("from Duck where owner_id =" + ownerId);
        return query.getResultList();
    }

    public List<Duck> getAllWithFeatureSet() {
        Query query = entityManager.createQuery("select d from Duck d join fetch d.featureSet", Duck.class);
        return query.getResultList();
    }

    public Duck getByIdWithFeatureSet(Long id) {
        Query query = entityManager.createQuery("select d from Duck d join fetch d.featureSet where d.id = " + id, Duck.class);
        return (Duck) query.getSingleResult();
    }

    public List<Duck> getAllWithRequests() {
        Query query = entityManager.createQuery("select d from Duck d join fetch d.requests", Duck.class);
        return query.getResultList();
    }

    public Duck getByIdWithRequests(Long id) {
        Query query = entityManager.createQuery("select d from Duck d join fetch d.requests where d.id = " + id, Duck.class);
        return (Duck) query.getSingleResult();
    }

    public List<Duck> getAllWithOwner() {
        Query query = entityManager.createQuery("select d from Duck d join fetch d.owner", Duck.class);
        return query.getResultList();
    }

    public Duck getByIdWithOwner(Long id) {
        Query query = entityManager.createQuery("select d from Duck d join fetch d.owner where d.id = " + id, Duck.class);
        return (Duck) query.getSingleResult();
    }
}
