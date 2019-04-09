package se.ifmo.ru.dao;

import se.ifmo.ru.model.Request;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class RequestDao {

    @PersistenceContext(unitName = "persistence")
    private EntityManager entityManager;

    public Request getById(long id) {
        return entityManager.find(Request.class, id);
    }

    public void save(Request request) {
        entityManager.persist(request);
    }

    public void delete(Request request) {
        entityManager.remove(entityManager.contains(request) ? request : entityManager.merge(request));
    }

    public List<Request> getAll() {
        Query query = entityManager.createQuery("from Request");
        return query.getResultList();
    }

    public List<Request> getByUserId(long userId) {
        Query query = entityManager.createQuery("from Request where user_id =" + userId);
        return ((org.hibernate.query.Query) query).list();
    }

    public List<Request> getByDuckId(long duckId) {
        Query query = entityManager.createQuery("from Request where duck_id =" + duckId);
        return ((org.hibernate.query.Query) query).list();
    }

    public List<Request> getByIsApproved(boolean isApproved) {
        Query query = entityManager.createQuery("from Request where is_approved =" + isApproved);
        return ((org.hibernate.query.Query) query).list();
    }

    public List<Request> getAllWithDuckAndUser() {
        Query query = entityManager.createQuery("select d from Request d join fetch d.duck join fetch d.user", Request.class);
        return query.getResultList();
    }

    public Request getByIdWithDuckAndUser(Long id) {
        Query query = entityManager.createQuery("select d from Request d join fetch d.duck join fetch d.user " +
                "where d.id = " + id, Request.class);
        return (Request) query.getSingleResult();
    }

}
