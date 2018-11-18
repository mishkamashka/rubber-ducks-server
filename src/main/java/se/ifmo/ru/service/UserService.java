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

    /**
     * Returns a User object contained in the database, or null if it does not exist
     * @param id - id of the required User
     * @return - required User if exists, null if does not exist
     */
    public User getById(long id) {
        return userDao.getById(id);
    }

    /**
     * Returns a User object contained in the database, list of Ducks of which can be accessed, or null if it does not exist
     * @param id - id of the required User
     * @return - required User if exists, null if does not exist
     */
    public User getByIdWithDucks(long id) {
        return userDao.getByIdWithDucks(id);
    }

    /**
     * Returns a User object contained in the database, list of Ducks and Requests of which can be accessed, or null if it does not exist
     * @param id - id of the required User
     * @return - required User if exists, null if does not exist
     */
    public User getByIdWithDucksAndRequests(long id) {
        return userDao.getByIdWithDucksAndRequests(id);
    }

    /**
     * Returns a User object contained in the database, list of Attending Events of which can be accessed, or null if it does not exist
     * @param id - id of the required User
     * @return - required User if exists, null if does not exist
     */
    public User getByIdWithAttendingEvents(long id) {
        return userDao.getByIdWithAttendingEvents(id);
    }

    /**
     * Returns a User object contained in the database, list of Organized Events of which can be accessed, or null if it does not exist
     * @param id - id of the required User
     * @return - required User if exists, null if does not exist
     */
    public User getByIdWithOrganizedEvents(long id) {
        return userDao.getByIdWithOrganizedEvents(id);
    }

    /**
     * Saves a User object to the database
     * @param user - user to save
     */
    public void save(User user) {
        userDao.save(user);
    }

    /**
     * Updates a User object in the database
     * @param user - user to update
     */
    public void update(User user) {
        userDao.update(user);
    }

    /**
     * Deletes a User object from the database
     * @param user - user to delete
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
