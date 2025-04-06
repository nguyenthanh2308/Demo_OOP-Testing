package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

public class UserDAO {
    @PersistenceContext(unitName = "blogPU")
    private EntityManager entityManager;

    public User findUserByUsername(String username) {
        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username", User.class
            );
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE username = :u AND password = :p", User.class);
            query.setParameter("u", username);
            query.setParameter("p", password);
            return query.uniqueResult();
        }
    }

    public boolean registerUser(String username, String password) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            session.save(user);

            transaction.commit();
            return true;

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            return false;
        }
    }


    public User getUserByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE username = :u", User.class);
            query.setParameter("u", username);
            return query.uniqueResult();
        }

    }
}
