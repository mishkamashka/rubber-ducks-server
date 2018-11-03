package se.ifmo.ru.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import se.ifmo.ru.model.FeatureSet;
import se.ifmo.ru.util.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import java.util.List;

public class FeatureSetDao {
    private Session session;
    private Transaction transaction;
    private FeatureSet featureSet;

    public FeatureSet getById(long id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        featureSet = session.get(FeatureSet.class, id);
        session.close();
        return featureSet;
    }

    public void save(FeatureSet featureSet) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(featureSet);
        transaction.commit();
        session.close();
    }

    public void delete(FeatureSet featureSet) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(featureSet);
        transaction.commit();
        session.close();
    }

    public void update(FeatureSet featureSet) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.update(featureSet);
        transaction.commit();
        session.close();
    }

    public List<FeatureSet> getAll() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from FeatureSet");
        List <FeatureSet> featureSets = ((org.hibernate.query.Query) query).list();
        return featureSets;
    }
}
