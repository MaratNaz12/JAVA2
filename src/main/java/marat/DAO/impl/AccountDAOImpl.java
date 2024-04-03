package marat.DAO.impl;

import marat.DAO.AccountDAO;
import marat.models.Account;
import marat.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class AccountDAOImpl extends CommonDAOImpl<Account, Long> implements AccountDAO {

    public AccountDAOImpl() {
        super(Account.class);
    }

    @Override
    public List<Account> GetWithFilter(String fieldName, int value_) {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        String query = "from Account  where " + fieldName + "= :value_";
        Transaction t = session.beginTransaction();
        List<Account> listOperations = session
                .createQuery(query, Account.class)
                .setParameter("value_", value_)
                .getResultList();
        t.commit();
        return listOperations;

    }

    @Override
    public List<Account> GetWithFilterBalanceRange(int lo, int hi) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        String query = "from Account  where curbalance" + " between :lo and :hi";
        Transaction t = session.beginTransaction();
        List<Account> listOperations = session
                .createQuery(query, Account.class)
                .setParameter("lo", lo)
                .setParameter("hi", hi)
                .getResultList();
        t.commit();
        return listOperations;

    }

    @Override
    public List<Account> GetWithFilterBalanceFree(String sign_, int value_) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        String query = "from Account  where curbalance" + sign_ + " :value_";
        Transaction t = session.beginTransaction();
        List<Account> listOperations = session
                .createQuery(query, Account.class)
                .setParameter("value_", value_)
                .getResultList();
        t.commit();
        return listOperations;

    }
}

