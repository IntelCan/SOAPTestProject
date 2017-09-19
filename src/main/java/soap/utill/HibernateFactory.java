package soap.utill;

import soap.exception.DataAccessLayerException;
import soap.model.Task;
import soap.model.UserAccount;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateFactory {
    private static SessionFactory sessionFactory;

    /**
     * Constructs a new Singleton SessionFactory
     * @return
     * @throws HibernateException
     */
    public static SessionFactory buildSessionFactory() throws HibernateException {
        if (sessionFactory != null) {
            closeFactory();
        }
        return configureSessionFactory();
    }

    /**
     * Builds a SessionFactory, if it hasn't been already.
     */
    public static SessionFactory buildIfNeeded() throws DataAccessLayerException {
        if (sessionFactory != null) {
            return sessionFactory;
        }
        try {
            return configureSessionFactory();
        } catch (HibernateException e) {
            throw new DataAccessLayerException(e);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    public static Session openSession() throws HibernateException {
        buildIfNeeded();
        return sessionFactory.openSession();
    }

    public static void closeFactory() {
        if (sessionFactory != null) {
            try {
                sessionFactory.close();
            } catch (HibernateException ignored) {

            }
        }
    }

    public static void close(Session session) {
        if (session != null) {
            try {
                session.close();
            } catch (HibernateException ignored) {

            }
        }
    }

    public static void rollback(Transaction tx) {
        try {
            if (tx != null) {
                tx.rollback();
            }
        } catch (HibernateException ignored) {

        }
    }
    /**
     *
     * @return
     * @throws HibernateException
     */
    private static SessionFactory configureSessionFactory() throws HibernateException {

        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addAnnotatedClass(UserAccount.class);
        configuration.addAnnotatedClass(Task.class);
        configuration.configure();

        sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;

    }
}

