package se.ifmo.ru.service;

import se.ifmo.ru.dao.RequestDao;
import se.ifmo.ru.model.Request;

import java.util.List;

public class RequestService {


    private RequestDao requestDao = new RequestDao();

    /**
     * Returns a Request object contained in the database, or null if it does not exist
     * @param id - id of the required Request
     * @return - required Request if exists, null if does not exist
     */
    public Request getById(long id) {
        return requestDao.getById(id);
    }

    /**
     * Returns a Request object contained in the database, fields ducks and user of which can be accessed, or null if it does not exist
     * @param id - id of the required Request
     * @return - required Request if exists, null if does not exist
     */
    public Request getByIdWithUserAndDuck(long id) {
        return requestDao.getByIdWithUserAndDuck(id);
    }

    /**
     * Saves a Request object to the database
     * @param request - request to save
     */
    public void save(Request request) {
        requestDao.save(request);
    }

    /**
     * Updates a Request object in the database
     * @param request - request to update
     */
    public void update(Request request) {
        requestDao.update(request);
    }

    /**
     * Deletes a Request object from the database
     * @param request - request to delete
     */
    public void delete(Request request) {
        requestDao.delete(request);
    }

    /**
     * Returns list of all Request objects contained in the database
     * @return - list of all requests
     */
    public List<Request> getAll() {
        return requestDao.getAll();
    }

    public List<Request> getByUserId(long userId) {
        return requestDao.getByUserId(userId);
    }

    public List<Request> getByDuckId(long duckId) {
        return requestDao.getByDuckId(duckId);
    }

    public List<Request> getByIsApproved(boolean isApproved) {
        return requestDao.getByIsApproved(isApproved);
    }
}
