package se.ifmo.ru.dao;

import se.ifmo.ru.model.FeatureSet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class FeatureSetDao {

    @PersistenceContext(unitName = "persistence")
    private EntityManager entityManager;

    public FeatureSet getById(long id) {
        return entityManager.find(FeatureSet.class, id);
    }

    public void save(FeatureSet featureSet) {
        entityManager.getTransaction().begin();
        entityManager.persist(featureSet);
        entityManager.getTransaction().commit();
    }

    public void delete(FeatureSet featureSet) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(featureSet) ? featureSet : entityManager.merge(featureSet));
        entityManager.getTransaction().commit();
    }

    public void update(FeatureSet featureSet) {
        entityManager.getTransaction().begin();
        entityManager.merge(featureSet);
        entityManager.getTransaction().commit();
    }

    public List<FeatureSet> getAll() {
        Query query = entityManager.createQuery("from FeatureSet");
        return query.getResultList();
    }
}
