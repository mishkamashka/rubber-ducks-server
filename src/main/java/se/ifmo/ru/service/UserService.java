package se.ifmo.ru.service;

import se.ifmo.ru.dao.DuckDao;
import se.ifmo.ru.dao.UserDao;
import se.ifmo.ru.model.Duck;
import se.ifmo.ru.model.User;

import java.util.LinkedList;
import java.util.List;

public class UserService {

    private UserDao userDao = new UserDao();
    private DuckDao duckDao = new DuckDao();

    public User getById(long id) {
        return userDao.getById(id);
    }

    public void save(User user) {
        userDao.save(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(User user) {
        userDao.delete(user);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public List<Duck> getDucks(User user) {
        List<Duck> allDucks = duckDao.getAll();
        LinkedList<Duck> ducks = new LinkedList<>();
        for (int i = 0; i < allDucks.size(); i++) {
            if (allDucks.get(i).getOwner().equals(user)) {
                ducks.addLast(allDucks.get(i));
            }
        }
        return ducks;
    }
}
