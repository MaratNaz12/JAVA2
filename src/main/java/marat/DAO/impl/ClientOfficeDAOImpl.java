package marat.DAO.impl;


import marat.DAO.ClientOfficeDAO;
import marat.models.Client;
import marat.models.ClientOffice;
import marat.models.Office;
import marat.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientOfficeDAOImpl extends CommonDAOImpl<ClientOffice, Long> implements ClientOfficeDAO {

    public ClientOfficeDAOImpl() {
        super(ClientOffice.class);
    }

    public List<Client> GetClient(int office_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        String query = "SELECT client from ClientOffice as a where a.office.id = :id_";
        Transaction t = session.beginTransaction();
        List<Client> listOperations = session
                .createQuery(query, Client.class)
                .setParameter("id_", office_id)
                .getResultList();
        t.commit();
        return listOperations;
    }


    public List<Office> GetOffice(int client_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        String query = "Select office from ClientOffice as a where a.client.id = :id_";
        Transaction t = session.beginTransaction();
        List<Office> listOperations = session
                .createQuery(query, Office.class)
                .setParameter("id_", client_id)
                .getResultList();
        t.commit();
        return listOperations;
    }


}







