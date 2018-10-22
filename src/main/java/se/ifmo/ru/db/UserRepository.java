package se.ifmo.ru.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import se.ifmo.ru.User;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class UserRepository implements DBInteraction<User>{
    private static SessionFactory sessionFactory;
    private Session session;

    UserRepository() {
        session = sessionFactory.openSession();
    }

    @Override
    public void create() {

    }

    @Override
    public void drop() {

    }

    @Override
    public void remove() {

    }

    @Override
    public void add(User element) {

    }

    @Override
    public List<User> getById() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        List <User> list
        return null;
    }


}