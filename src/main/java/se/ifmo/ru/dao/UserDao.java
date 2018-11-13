package se.ifmo.ru.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.ifmo.ru.model.Duck;
import se.ifmo.ru.util.HibernateSessionFactoryUtil;
import se.ifmo.ru.model.User;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDao {

    private Session session;
    private Transaction transaction;

    public User getById(long id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    public User getByIdWithDucksAndRequests(long id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Query query = session.createQuery("from User u left join fetch u.ducks d left join fetch u.requests r where d.owner.id = u.id or r.user.id = u.id and u.id = " + id);
//        Query query = session.createQuery("from User u inner join u.ducks d where d.owner.id = u.id and u.id = " + id);
//        User user = (User) session.createCriteria(User.class)
//                .add(Restrictions.eq("id", id))
//                .uniqueResult();
//        session.close();
//        return user;

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.where(criteriaBuilder.equal(userRoot.get("id"), id));
        User user = session.createQuery(criteriaQuery).getSingleResult();
        Hibernate.initialize(user.getDucks().size());
        Hibernate.initialize(user.getRequests().size());
        session.close();
        return user;
//        return this.handleJoinResult(query).get(0);
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

    private List<User> handleJoinResult(Query query) {
        List<User> users = new ArrayList<>();
        List<Object[]> objects = ((org.hibernate.query.Query) query).list();
        Iterator iterator = objects.iterator();
        while (iterator.hasNext()) {
            Object[] obj = (Object[]) iterator.next();
            User user = (User) obj[0];
            Duck duck = (Duck) obj[1];
            users.add(user);
        }
        session.close();
        return users;
    }

}