package se.ifmo.ru.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import se.ifmo.ru.model.*;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil(){}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Duck.class);
                configuration.addAnnotatedClass(Event.class);
                configuration.addAnnotatedClass(Meeting.class);
                configuration.addAnnotatedClass(Place.class);
                configuration.addAnnotatedClass(FeatureSet.class);
                configuration.addAnnotatedClass(Request.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Cannot create session factory");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
