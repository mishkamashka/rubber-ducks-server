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
     * Returns a User object contained in the database, list of ducks of which can be accessed, or null if it does not exist
     * @param id - id of the required User
     * @return - required User if exists, null if does not exist
     */
    public User getByIdWithDucks(long id) {
        return userDao.getById(id);
    }

    /**
     * Returns a User object contained in the database, list of ducks and requests of which can be accessed, or null if it does not exist
     * @param id - id of the required User
     * @return - required User if exists, null if does not exist
     */
    public User getByIdWithDucksAndRequests(long id) {
        return userDao.getById(id);
    }

    /**
     * Returns a User object contained in the database, list of attendingEvents of which can be accessed, or null if it does not exist
     * @param id - id of the required User
     * @return - required User if exists, null if does not exist
     */
    public User getByIdWithAttendingEvents(long id) {
        return userDao.getById(id);
    }

    /**
     * Returns a User object contained in the database, list of organizedEvents of which can be accessed, or null if it does not exist
     * @param id - id of the required User
     * @return - required User if exists, null if does not exist
     */
    public User getByIdWithOrganizedEvents(long id) {
        return userDao.getById(id);
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
     * Deletes a User object from the database (will cascade to Duck only if was loaded by id from the db)
     * @param user - user to delete
     */
    public void delete(User user) {
        userDao.delete(user);
    }

    /**
     * Returns list of all User objects contained in the database
     * @return - list of all users
     */
    public List<User> getAll() {
        return userDao.getAll();
    }

    /**
     * Returns list of Duck objects owned by the User
     * @param user - user whose ducks will be returned
     * @return - list of Ducks
     */
    public List<Duck> getDucks(User user) {
        return duckDao.getByOwnerId(user.getId());
    }

    /**
     * Returns User with certain nickname and email fields
     * @param nickname - nickname of the required user
     * @param email - email of the required user
     * @return - User with certain nickname and email, if exists, or null if does not exist
     */
    public User getByNicknameAndEmail(String nickname, String email) {
        return userDao.getByNicknameAndEmail(nickname, email);
    }

    /**
     * Returns User with certain nickname and email fields with the list of requests and ducks can accessed
     * @param nickname - nickname of the required user
     * @param email - email of the required user
     * @return - User with certain nickname and email, if exists, or null if does not exist
     */
    public User getByNicknameAndEmailWithDucksAndRequests(String nickname, String email) {
        return userDao.getByNicknameAndEmail(nickname, email);
    }

    /**
     * Returns list of users whose first and last names contain a substring equal to the parameters, ordered from the best match to the worst
     * @param firstName - substring to find among firstNames
     * @param lastName - substring to find among lastNames
     * @return - list of users or null, if nothing found
     */
    public List<User> getByFirstNameAndLastName(String firstName, String lastName) {
        return userDao.getByFirstNameAndLastName(firstName, lastName);
    }

    /**
     * Returns list of users whose nickname contains a substring equal to the parameter, ordered from the best match to the worst
     * @param nickname - substring to find among firstNames
     * @return - list of users or null, if nothing found
     */
    public List<User> getByNickname(String nickname) {
        return userDao.getByNickname(nickname);
    }

    /**
     * Returns list of users whose first name contains a substring equal to the parameter, ordered from the best match to the worst
     * @param firstName - substring to find among firstNames
     * @return - list of users or null, if nothing found
     */
    public List<User> getByFirstName(String firstName) {
        return userDao.getByFirstName(firstName);
    }

    /**
     * Returns list of users whose last name contains a substring equal to the parameter, ordered from the best match to the worst
     * @param lastName - substring to find among lastNames
     * @return - list of users or null, if nothing found
     */
    public List<User> getByLastName(String lastName) {
        return userDao.getByLastName(lastName);
    }

    /**
     * Returns list of users with certain gender
     * @param gender - required user's gender
     * @return - list of users or null
     */
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
