package se.ifmo.ru.service;

import se.ifmo.ru.dao.DuckDao;
import se.ifmo.ru.model.Duck;

public class DuckService {

    private DuckDao duckDao = new DuckDao();

    public Duck getDuckById(int id) {
        return duckDao.getDuckById(id);
    }

    public void saveDuck(Duck duck) {
        duckDao.saveDuck(duck);
    }

    public void deleteDuck(Duck duck) {
        duckDao.deleteDuck(duck);
    }

    public void updateDuck(Duck duck) {
        duckDao.updateDuck(duck);
    }

}
