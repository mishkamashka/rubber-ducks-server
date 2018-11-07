package se.ifmo.ru.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import se.ifmo.ru.util.HibernateSessionFactoryUtil;
import se.ifmo.ru.model.User;

import javax.persistence.Query;
import java.util.List;

public class UserDao {

    private Session session;
    private Transaction transaction;
    private User user;

    public User getById(long id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        user = session.get(User.class, id);
        session.close();
        return user;
    }

    public void save(User user) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public void delete(User user) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    public void update(User user) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    public List<User> getAll() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User");
        List<User> users = ((org.hibernate.query.Query) query).list();
        session.close();
        return users;
    }

    public User getByNicknameAndEmail(String nickname, String email) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User where nickname = :nickname and email = :email");
        query.setParameter("nickname", nickname);
        query.setParameter("email", email);
        List<User> users = ((org.hibernate.query.Query) query).list();
        session.close();
        if (users != null && users.size() > 0)
            return users.get(0);
        else
            return null;
    }

    /**
     * @param nickname substring to look for
     * @return users whose nicknames contain "nickname" parameter, first - most similar
     */
    public List<User> getByNickname(String nickname) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User where nickname like '%" + nickname + "%' order by length(nickname)");
        List<User> users = ((org.hibernate.query.Query) query).list();
        session.close();
        return users;
    }

    public List<User> getByFirstName(String firstName) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User where first_name like '%" + firstName + "%' order by length(first_name)");
        List<User> users = ((org.hibernate.query.Query) query).list();
        session.close();
        return users;
    }

    public List<User> getByLastName(String lastName) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User where last_name like '%" + lastName + "%' order by length(last_name)");
        List<User> users = ((org.hibernate.query.Query) query).list();
        session.close();
        return users;
    }

    public List<User> getByFirstNameAndLastName(String firstName, String lastName) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User where first_name = :first_name and last_name = :last_name");
        query.setParameter("first_name", firstName);
        query.setParameter("last_name", lastName);
        List<User> users = ((org.hibernate.query.Query) query).list();
        session.close();
        return users;
    }

    public List<User> getByGender(char gender) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User where gender = :gender");
        query.setParameter("gender", gender);
        List<User> users = ((org.hibernate.query.Query) query).list();
        session.close();
        return users;
    }

    //TODO: get by nickname, name+lastname, name, lastname

}