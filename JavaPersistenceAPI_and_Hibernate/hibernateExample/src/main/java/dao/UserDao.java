package dao;

import models.Operation;
import models.User;
import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class UserDao {
    public User findById(int id) {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .get(User.class, id);
    }

    public void save(User user) {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public void update(User user) {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    public Operation findOperationById(int id) {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .get(Operation.class, id);
    }

}
