package se.ifmo.ru.service;

import se.ifmo.ru.dao.DuckDao;
import se.ifmo.ru.model.Duck;

import java.util.List;

public class DuckService {

    private DuckDao duckDao = new DuckDao();

    public Duck getById(long id) {
        return duckDao.getById(id);
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

}
