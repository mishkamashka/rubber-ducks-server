package se.ifmo.ru.service;

import se.ifmo.ru.dao.DuckDao;
import se.ifmo.ru.model.Duck;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class DuckService {

    @EJB
    private DuckDao duckDao;

    /**
     * Returns a Duck object contained in the database, or null if it does not exist
     *
     * @param id - id of the required Duck
     * @return - required Duck if exists, null if does not exist
     */
    public Duck getById(long id) {
        return duckDao.getById(id);
    }

    /**
     * Saves a Duck object to the database
     *
     * @param duck - duck to save
     */
    public void save(Duck duck) {
        duckDao.save(duck);
    }

    /**
     * Updates a Duck object in the database
     *
     * @param duck - duck to update
     */
    public void update(Duck duck) {
        duckDao.update(duck);
    }

    /**
     * Deletes a Duck object from the database
     *
     * @param duck - duck to delete
     */
    public void delete(Duck duck) {
        duckDao.delete(duck);
    }

    /**
     * Returns list of all Duck objects contained in the database
     *
     * @return - list of all ducks
     */
    public List<Duck> getAll() {
        return duckDao.getAll();
    }

    /**
     * Returns list of ducks, names of which contain a substring equal to the parameter, ordered from the best match to the worst
     *
     * @param name - substring to find among names
     * @return - list of ducks or null, if nothing found
     */
    public List<Duck> getByName(String name) {
        return duckDao.getByName(name);
    }

    /**
     * Returns list of ducks owned by a certain user
     *
     * @param ownerId - id of the user, whose ducks are required
     * @return - list of ducks or null, if nothing found
     */
    public List<Duck> getByOwnerId(long ownerId) {
        return duckDao.getByOwnerId(ownerId);
    }

    /**
     * Returns list of accessible ducks, fields owner and featureSet of which can be accessed
     *
     * @return - list of ducks or null, if nothing found
     */
    public List<Duck> getAccessibleWithOwnerAndFeatureSet() {
        List<Duck> allDucks = this.getAll();
        List<Duck> accessibleDucks = new LinkedList<>();
        for (Duck duck : allDucks) {
            if (duck.isAccessible())
                accessibleDucks.add(duck);
        }
        return accessibleDucks;
    }

    public List<Duck> getAllWithFeatureSet() {
        return duckDao.getAllWithFeatureSet();
    }

    public Duck getByIdWithFeatureSet(Long id) {
       return duckDao.getByIdWithFeatureSet(id);
    }

    public List<Duck> getAllWithRequests() {
        return duckDao.getAllWithRequests();
    }

    public Duck getByIdWithRequests(Long id) {
        return duckDao.getByIdWithRequests(id);
    }

    public List<Duck> getAllWithOwner() {
        return duckDao.getAllWithOwner();
    }

    public Duck getByIdWithOwner(Long id) {
        return duckDao.getByIdWithOwner(id);
    }

}
