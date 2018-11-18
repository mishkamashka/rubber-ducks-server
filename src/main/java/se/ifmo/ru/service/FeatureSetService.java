package se.ifmo.ru.service;

import se.ifmo.ru.dao.FeatureSetDao;
import se.ifmo.ru.model.FeatureSet;

import java.util.List;

public class FeatureSetService {

    private FeatureSetDao featureSetDao = new FeatureSetDao();

    /**
     * Returns a FeatureSet object contained in the database
     * @param id - id of the required FeatureSet
     * @return - FeatureSet with the id
     */
    public FeatureSet getById(long id) {
        return featureSetDao.getById(id);
    }
    
    public void save(FeatureSet featureSet) {
        featureSetDao.save(featureSet);
    }

    public void update(FeatureSet featureSet) {
        featureSetDao.update(featureSet);
    }

    public void delete(FeatureSet featureSet) {
        featureSetDao.delete(featureSet);
    }

    public List<FeatureSet> getAll() {
        return featureSetDao.getAll();
    }
}
