package se.ifmo.ru.dao;

import se.ifmo.ru.model.Duck;
import se.ifmo.ru.model.Event;
import se.ifmo.ru.model.Request;
import se.ifmo.ru.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserDao {

    @PersistenceContext(unitName = "persistence")
    private EntityManager entityManager;

//    TODO: how to work with this?
    private DuckDao duckDao = new DuckDao();
    private RequestDao requestDao = new RequestDao();
    private EventDao eventDao = new EventDao();

    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    public void save(User user) {
        entityManager.persist(user);
    }

    public void delete(User user) {
        entityManager.getTransaction().begin();

        List<Duck> ducks = duckDao.getByOwnerId(user.getId());
        ducks.forEach(duck -> duckDao.delete(duck));

        List<Request> requests = requestDao.getByUserId(user.getId());
        requests.forEach(request -> requestDao.delete(request));

        List<Event> organizedEvents = eventDao.getByOrganizerId(user.getId());
        organizedEvents.forEach(event -> eventDao.delete(event));

//        List<Event> attendingEvents = eventDao.getByParticipantId(user.getId());
//        attendingEvents.forEach(event -> eventDao.delete(event));

        List<Event> attendingEvents = eventDao.getByParticipantId(user.getId());
        attendingEvents.forEach(event -> event.getParticipants().remove(user));

        user.setDucks(new ArrayList<>());
        user.setRequests(new ArrayList<>());
        user.setOrganizedEvents(new ArrayList<>());
        user.setAttendingEvents(new ArrayList<>());

        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
        entityManager.getTransaction().commit();
    }

    public void update(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    public List<User> getAll() {
        Query query = entityManager.createQuery("from User");
        return query.getResultList();
    }

    public User getByEmail(String email) {
        Query query = entityManager.createQuery("from User where email = :email");
        query.setParameter("email", email);
        List<User> users = query.getResultList();
        if (users != null && users.size() > 0)
            return users.get(0);
        else
            return null;
    }

    public User getByNickname(String nickname) {
        Query query = entityManager.createQuery("from User where nickname = :nickname");
        query.setParameter("nickname", nickname);
        List<User> users = query.getResultList();
        if (users != null && users.size() > 0)
            return users.get(0);
        else
            return null;
    }

    public User getByNicknameAndEmail(String nickname, String email) {
        Query query = entityManager.createQuery("from User where nickname = :nickname and email = :email");
        query.setParameter("nickname", nickname);
        query.setParameter("email", email);
        List<User> users = query.getResultList();
        if (users != null && users.size() > 0)
            return users.get(0);
        else
            return null;
    }

    public List<User> getByFirstName(String firstName) {
        Query query = entityManager.createQuery("from User where first_name like '%" + firstName + "%' order by length(first_name)");
        return query.getResultList();
    }

    public List<User> getByLastName(String lastName) {
        Query query = entityManager.createQuery("from User where last_name like '%" + lastName + "%' order by length(last_name)");
        return query.getResultList();
    }

    public List<User> getByFirstNameAndLastName(String firstName, String lastName) {
        Query query = entityManager.createQuery("from User where first_name = :first_name and last_name = :last_name");
        query.setParameter("first_name", firstName);
        query.setParameter("last_name", lastName);
        return query.getResultList();
    }

    public List<User> getByGender(char gender) {
        Query query = entityManager.createQuery("from User where gender = :gender");
        query.setParameter("gender", gender);
        return query.getResultList();
    }

}