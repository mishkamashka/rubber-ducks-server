package se.ifmo.ru.dao;

import se.ifmo.ru.model.Request;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class RequestDao {

    private EntityManager entityManager = Persistence.createEntityManagerFactory("persistence").createEntityManager();

    public Request getById(long id) {
        return entityManager.find(Request.class, id);
    }

    public void save(Request request) {
        entityManager.getTransaction().begin();
        entityManager.persist(request);
        entityManager.getTransaction().commit();
    }

    public void delete(Request request) {
        entityManager.getTransaction().begin();
        entityManager.remove(request);
        entityManager.getTransaction().commit();
    }

    public void update(Request request) {
        entityManager.getTransaction().begin();
        entityManager.merge(request);
        entityManager.getTransaction().commit();
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

}
