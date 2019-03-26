package se.ifmo.ru.dao;

import se.ifmo.ru.model.Duck;
import se.ifmo.ru.model.Request;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class DuckDao {

    private EntityManager entityManager = Persistence.createEntityManagerFactory("persistence").createEntityManager();
    private RequestDao requestDao = new RequestDao();

    public Duck getById(long id) {
        return entityManager.find(Duck.class, id);
    }

    public void save(Duck duck) {
        entityManager.getTransaction().begin();
        entityManager.persist(duck);
        entityManager.getTransaction().commit();
    }

    public void delete(Duck duck) {
        entityManager.getTransaction().begin();
        List<Request> requests = requestDao.getByDuckId(duck.getId());
        requests.forEach(request -> requestDao.delete(request));
        duck.setRequests(new ArrayList<>());
        entityManager.remove(entityManager.contains(duck) ? duck : entityManager.merge(duck));
        entityManager.getTransaction().commit();
    }

    public void update(Duck duck) {
        entityManager.getTransaction().begin();
        entityManager.merge(duck);
        entityManager.getTransaction().commit();
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
}
