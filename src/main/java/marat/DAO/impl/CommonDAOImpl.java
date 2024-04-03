package marat.DAO.impl;

import marat.DAO.CommonDAO;
import marat.models.CommonEntity;
import marat.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;



public class CommonDAOImpl<T extends CommonEntity<ID>, ID extends Serializable> implements CommonDAO<T, ID> {


    protected Class<T> persistentClass;

    public CommonDAOImpl(Class<T> entityClass) {
        this.persistentClass = entityClass;
    }


    @Override
    public T getById(ID id) {
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
    public void save(T entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        if (entity.getId() != null) {
            entity.setId(null);
        }
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();

    }

    @Override
    public void saveCollection(List<T> entities) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        for (T entity : entities) {
            this.save(entity);
        }
        t.commit();
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
    public void deleteById(ID id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        T entity = getById(id);
        session.delete(entity);
        t.commit();
    }

}