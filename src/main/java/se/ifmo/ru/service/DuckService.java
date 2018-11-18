package se.ifmo.ru.service;

import se.ifmo.ru.dao.DuckDao;
import se.ifmo.ru.model.Duck;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DuckService {

    private DuckDao duckDao = new DuckDao();

    /**
     * Returns a Duck object contained in the database, or null if it does not exist
     * @param id - id of the required Duck
     * @return - required Duck if exists, null if does not exist
     */
    public Duck getById(long id) {
        return duckDao.getById(id);
    }

    /**
     * Returns a Duck object contained in the database, fields owner and featureSet of which can be accessed, or null if it does not exist
     * @param id - id of the required Duck
     * @return - required Duck if exists, null if does not exist
     */
    public Duck getByIdWithOwnerAndFeatureSet(long id) {
        return duckDao.getByIdWithOwnerAndFeatureSet(id);
    }

    /**
     * Returns a Duck object contained in the database, list of Requests of which can be accessed, or null if it does not exist
     * @param id - id of the required Duck
     * @return - required Duck if exists, null if does not exist
     */
    public Duck getByIdWithRequests(long id) {
        return duckDao.getByIdWithRequests(id);
    }

    public void save(Duck duck) {
        duckDao.save(duck);
    }

    public void delete(Duck duck) {
        duckDao.delete(duck);
    }

    public void update(Duck duck) {
        duckDao.update(duck);
    }

    public List<Duck> getAll() {
        return duckDao.getAll();
    }

    public List<Duck> getAllWithOwnerAndFeatureSet() {
        return duckDao.getAllWithOwnerAndFeatureSet();
    }

    public List<Duck> getByName(String name) {
        return duckDao.getByName(name);
    }

    public List<Duck> getByNameWithOwnerAndFeatureSet(String name) {
        return duckDao.getByNameWithOwnerAndFeatureSet(name);
    }

    public List<Duck> getByOwnerId(long ownerId) {
        return duckDao.getByOwnerId(ownerId);
    }

    //TODO: test
    public List<Duck> getAccessibleWithOwnerAndFeatureSet() {
        List<Duck> allDucks = this.getAllWithOwnerAndFeatureSet();
        List<Duck> accessibleDucks = new LinkedList<>();
        for (Duck duck : allDucks) {
            if (duck.isAccessible())
                accessibleDucks.add(duck);
        }
        return accessibleDucks;
    }

}
