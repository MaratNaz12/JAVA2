package marat.utils;

import marat.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();
            try {
                sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClasses(Account.class,
                                BankAccType.class,
                                Client.class,
                                ClientOffice.class,
                                Office.class,
                                Operation.class,
                                TestEntity.class)
                        .buildMetadata()
                        .buildSessionFactory();
            } catch (Exception e) {
                throw e;
            }
        }
        return sessionFactory;
    }
}
