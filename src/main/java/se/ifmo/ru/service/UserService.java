package se.ifmo.ru.service;

import se.ifmo.ru.dao.DuckDao;
import se.ifmo.ru.dao.UserDao;
import se.ifmo.ru.model.Duck;
import se.ifmo.ru.model.User;

import javax.validation.ConstraintViolationException;
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

    /**
     *
     * @param user (must have id)
     */
    public void update(User user) {
        userDao.update(user);
    }

    /**
     *
     * @param user (must have id)
     */
    public void delete(User user) {
        userDao.delete(user);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public List<Duck> getDucks(User user) {
        List<Duck> allDucks = duckDao.getAll();
        LinkedList<Duck> ducks = new LinkedList<>();
        for (Duck allDuck : allDucks) {
            if (allDuck.getOwner().equals(user)) {
                ducks.addLast(allDuck);
            }
        }
        return ducks;
    }

    public User getByNicknameAndEmail(String nickname, String email) {
        return userDao.getByNicknameAndEmail(nickname, email);
    }

    public List<User> getByNickname(String nickname) {
        return userDao.getByNickname(nickname);
    }

    //TODO: get by duck, nickname, email, last+first name, gender
}
