package marat.DAO.impl;


import marat.DAO.OperationDAO;
import marat.models.Account;
import marat.models.Operation;
import marat.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;



@Repository
public class OperationDAOImpl extends CommonDAOImpl<Operation> implements OperationDAO {

    @Override
    public List<Operation> GetByTo(Account account) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        String query = "from Operation  where toacc = :account";
        Transaction t = session.beginTransaction();
        List<Operation> listOperations = session
                .createQuery(query, Operation.class)
                .setParameter("account", account)
                .getResultList();
        t.commit();
        return listOperations;
    }

    @Override
    public List<Operation> GetByFrom(Account account) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        String query = "from Operation  where fromacc = :account";
        Transaction t = session.beginTransaction();
        List<Operation> listOperations = session
                .createQuery(query, Operation.class)
                .setParameter("account", account)
                .getResultList();
        t.commit();
        return listOperations;

    }

    @Override
    public List<Operation> GetWithFilterRangeSum(int lo, int hi) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        String query = "from Operation  where sum" + " between :lo and :hi";
        Transaction t = session.beginTransaction();
        List<Operation> listOperations = session
                .createQuery(query, Operation.class)
                .setParameter("lo", lo)
                .setParameter("hi", hi)
                .getResultList();
        t.commit();
        return listOperations;

    }

    @Override
    public List<Operation> GetWithFilterFree(String sign_, int value_) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        String query = "from Operation  where " + "sum" + sign_ + " :value_";
        Transaction t = session.beginTransaction();
        List<Operation> listOperations = session
                .createQuery(query, Operation.class)
                .setParameter("value_", value_)
                .getResultList();
        t.commit();
        return listOperations;
    }

    public List<Operation> GetWithFilterRangeTime(Timestamp t1, Timestamp t2) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        String query = "from Operation  where dayperiod  between :lo and :hi";
        Transaction t = session.beginTransaction();
        List<Operation> listOperations = session
                .createQuery(query, Operation.class)
                .setParameter("lo", t1)
                .setParameter("hi", t2)
                .getResultList();
        t.commit();
        return listOperations;
    }

    public List<Operation> GetWithFilterFreeTime(String sign, Timestamp t2) {


    Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
    String query = "from Operation  where dayperiod" + sign + " :value_";
    Transaction t = session.beginTransaction();
    List<Operation> listOperations = session
            .createQuery(query, Operation.class)
            .setParameter("value_", t2)
            .getResultList();
            t.commit();
            return listOperations;
}

}
