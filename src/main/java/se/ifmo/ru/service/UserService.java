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

    public UserService() {}

    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public List<Duck> getUsersDucks(User user) {
        List<Duck> allDucks = duckDao.getAllDucks();
        LinkedList<Duck> ducks = new LinkedList<>();
        for (int i = 0; i < allDucks.size(); i++) {
            if (allDucks.get(i).getOwner().equals(user)) {
                ducks.addLast(allDucks.get(i));
            }
        }
        return ducks;
    }
}
