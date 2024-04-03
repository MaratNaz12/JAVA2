package marat.DAO.impl;

import marat.DAO.CommonDAO;
import marat.models.CommonEntity;
import marat.utils.HibernateSessionFactoryUtil;
import marat.utils.ReflectionTools;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@Repository
public abstract class CommonDAOImpl<T extends CommonEntity> implements CommonDAO<T> {
//    @Autowired
//    SessionFactory sessionFactory;
//
//    @Override
//    public Session getSession() {
//        return sessionFactory.getCurrentSession();
//    }


    protected Class<T> persistentClass = ReflectionTools.getGeneric(getClass(), 0);;


    @Override
    public T getById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        T b = session.get(persistentClass, id);
        t.commit();
        return b;
    }


    @Override
    public List<T> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        List<T> b = session.createQuery("from " + persistentClass.getSimpleName(), persistentClass).getResultList();
        t.commit();
        return b;

    }

    @Override
    public void save(T entity){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        session.persist(entity);
        session.flush();
        t.commit();
        session.close();
    }
    @Override
    public void saveCollection(List<T> entities){
        HibernateSessionFactoryUtil.getSessionFactory()
                .inTransaction(session -> {
                    for (T obj : entities) {
                        session.persist(obj);
                    }
                    session.flush();
                });
    }


    @Override
    public void update(T entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        session.update(entity);
        t.commit();
    }


    @Override
    public void delete(T entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        session.delete(entity);
        t.commit();
    }


    @Override
    public void deleteById(Long id) {
        T entity = getById(id);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        session.delete(entity);
        t.commit();
    }

}