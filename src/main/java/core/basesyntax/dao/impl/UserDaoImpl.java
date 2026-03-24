package core.basesyntax.dao.impl;

import core.basesyntax.dao.UserDao;
import core.basesyntax.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDaoImpl extends AbstractDao implements UserDao {
    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public User create(User entity) {
        return null;

    }

    @Override
    public User get(Long id) {
        try (Session session = factory.openSession();) {
            return session.get(User.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Can't get user by id " + id, e);
        }
    }

    @Override
    public List<User> getAll() {
        try (Session session = factory.openSession();) {
            Query<User> commentQuery = session.createQuery("from User", User.class);
            return commentQuery.getResultList();
        }
    }

    @Override
    public void remove(User entity) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = factory.openSession();
            transaction = session.beginTransaction();

            User user = session.get(User.class, entity.getId());
            if (user != null) {
                session.remove(entity);
            }

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't remove User from db with id" + entity.getId(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
