package se.ifmo.ru.service;

import se.ifmo.ru.dao.FeatureSetDao;
import se.ifmo.ru.model.FeatureSet;

import java.util.List;

public class FeatureSetService {

    private FeatureSetDao featureSetDao = new FeatureSetDao();

    /**
     * Returns a FeatureSet object contained in the database, or null if it does not exist
     * @param id - id of the required FeatureSet
     * @return - required FeatureSet if exists, null if does not exist
     */
    public FeatureSet getById(long id) {
        return featureSetDao.getById(id);
    }

    /**
     * Saves a FeatureSet object to the database
     * @param featureSet - featureSet to save
     */
    public void save(FeatureSet featureSet) {
        featureSetDao.save(featureSet);
    }

    /**
     * Updates a FeatureSet object in the database
     * @param featureSet - featureSet to update
     */
    public void update(FeatureSet featureSet) {
        featureSetDao.update(featureSet);
    }

    /**
     * Deletes a FeatureSet object from the database
     * @param featureSet - featureSet to delete
     */
    public void delete(FeatureSet featureSet) {
        featureSetDao.delete(featureSet);
    }

    /**
     * Returns list of all FeatureSet objects contained in the database
     * @return - list of all featureSets
     */
    public List<FeatureSet> getAll() {
        return featureSetDao.getAll();
    }
}
