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

    public User getByIdWithDucks(long id) {
        return userDao.getByIdWithDucks(id);
    }

    public User getByIdWithDucksAndRequests(long id) {
        return userDao.getByIdWithDucksAndRequests(id);
    }

    public User getByIdWithAttendingEvents(long id) {
        return userDao.getByIdWithAttendingEvents(id);
    }

    public User getByIdWithOrganizedEvents(long id) {
        return userDao.getByIdWithOrganizedEvents(id);
    }

    public void save(User user) {
        userDao.save(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    /**
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

    public User getByNicknameAndEmailWithDucksAndRequests(String nickname, String email) {
        return userDao.getByNicknameAndEmailWithDucksAndRequests(nickname, email);
    }

    public List<User> getByFirstNameAndLastName(String firstName, String lastName) {
        return userDao.getByFirstNameAndLastName(firstName, lastName);
    }

    public List<User> getByNickname(String nickname) {
        return userDao.getByNickname(nickname);
    }

    public List<User> getByFirstName(String firstName) {
        return userDao.getByFirstName(firstName);
    }

    public List<User> getByLastName(String lastName) {
        return userDao.getByLastName(lastName);
    }

    //TODO: test
    public List<User> getByGender(char gender) {
        List<User> allUsers = this.getAll();
        List<User> requiredUsers = new LinkedList<>();
        for (User user : allUsers) {
            if (user.getGender() == gender)
                requiredUsers.add(user);
        }
        return requiredUsers;
    }
}
