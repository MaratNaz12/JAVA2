package marat.DAO.impl;


import marat.DAO.OperationDAO;
import marat.models.Operation;
import marat.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;



@Repository
public class OperationDAOImpl extends CommonDAOImpl<Operation, Long> implements OperationDAO {

    public OperationDAOImpl() {
        super(Operation.class);
    }


    @Override
    public List<Operation> GetWithFilterRange(String fieldName, int lo, int hi) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        String query = "from Operation  where " + fieldName + " between :lo and :hi";
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
    public List<Operation> GetWithFilterFree(String fieldName, String sign_, int value_) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        String query = "from Operation  where " + fieldName + sign_ + " :value_";
        Transaction t = session.beginTransaction();
        List<Operation> listOperations = session
                .createQuery(query, Operation.class)
                .setParameter("value_", value_)
                .getResultList();
        t.commit();
        return listOperations;

    }
}
