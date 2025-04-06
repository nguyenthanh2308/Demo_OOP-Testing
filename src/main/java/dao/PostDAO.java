package dao;

import model.Post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import utils.HibernateUtil;

import java.util.List;

public class PostDAO {

    private EntityManager entityManager;

    public PostDAO() {
        this.entityManager = HibernateUtil.getEntityManager();
    }

    public void addPost(Post post) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(post);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Post> getAllPosts() {
        try {
            TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post p", Post.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
