package se.ifmo.ru.service;

import se.ifmo.ru.dao.RequestDao;
import se.ifmo.ru.model.Request;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@Stateless
public class RequestService {

    @EJB
    private RequestDao requestDao;

    /**
     * Returns a Request object contained in the database, or null if it does not exist
     *
     * @param id - id of the required Request
     * @return - required Request if exists, null if does not exist
     */
    public Request getById(long id) {
        return requestDao.getById(id);
    }

    /**
     * Saves a Request object to the database
     *
     * @param request - request to save
     */
    public void save(Request request) {
        requestDao.save(request);
    }

    /**
     * Deletes a Request object from the database
     *
     * @param request - request to delete
     */
    public void delete(Request request) {
        requestDao.delete(request);
    }

    /**
     * Returns list of all Request objects contained in the database
     *
     * @return - list of all requests
     */
    public List<Request> getAll() {
        return requestDao.getAll();
    }

    /**
     * Returns list of all requests, owned by a certain user
     *
     * @param userId - id of the user, whose requests are required
     * @return - list of requests or null, if nothing found
     */
    public List<Request> getByUserId(long userId) {
        return requestDao.getByUserId(userId);
    }

    /**
     * Returns list of all requests on a certain duck
     *
     * @param duckId - id of the duck, requests on which are required
     * @return - list of requests or null, if nothing found
     */
    public List<Request> getByDuckId(long duckId) {
        return requestDao.getByDuckId(duckId);
    }

    /**
     * Returns list of requests approved or not, according to the parameter
     *
     * @param isApproved - boolean value to find among isApproved fields
     * @return - list of requests or null, if nothing found
     */
    public List<Request> getByIsApproved(boolean isApproved) {
        return requestDao.getByIsApproved(isApproved);
    }

    public List<Request> getAllWithDuckAndUser() {
        return requestDao.getAllWithDuckAndUser();
    }

    public Request getByIdWithDuckAndUser(Long id) {
        return requestDao.getByIdWithDuckAndUser(id);
    }
}
