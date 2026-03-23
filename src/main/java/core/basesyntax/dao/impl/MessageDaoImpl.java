package core.basesyntax.dao.impl;

import core.basesyntax.dao.MessageDao;
import core.basesyntax.model.Message;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MessageDaoImpl extends AbstractDao implements MessageDao {
    public MessageDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Message create(Message entity) {
        return null;
    }

    @Override
    public Message get(Long id) {
        return null;
    }

    @Override
    public List<Message> getAll() {
        return null;
    }

    @Override
    public void remove(Message entity) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = factory.openSession();
            transaction = session.beginTransaction();

            Message message = session.get(Message.class, entity.getId());
            if (message != null) {
                session.remove(entity);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                throw new RuntimeException("Can't remove message form db with id" + entity.getId(),
                        e);
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
