package se.ifmo.ru.service;

import se.ifmo.ru.dao.RequestDao;
import se.ifmo.ru.model.Request;

import java.util.List;

public class RequestService {


    private RequestDao requestDao = new RequestDao();

    public Request getById(long id) {
        return requestDao.getById(id);
    }

    public Request getByIdWithUserAndDuck(long id) {
        return requestDao.getByIdWithUserAndDuck(id);
    }

    public void save(Request request) {
        requestDao.save(request);
    }

    public void update(Request request) {
        requestDao.update(request);
    }

    public void delete(Request request) {
        requestDao.delete(request);
    }

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
