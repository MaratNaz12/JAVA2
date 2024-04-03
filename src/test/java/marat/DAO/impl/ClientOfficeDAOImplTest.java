package marat.DAO.impl;

import marat.DAO.ClientDAO;
import marat.DAO.ClientOfficeDAO;
import marat.DAO.OfficeDAO;
import marat.DAO.TestEntityDAO;
import marat.models.Client;
import marat.models.ClientOffice;
import marat.models.Office;
import marat.models.TestEntity;
import marat.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ClientOfficeDAOImplTest {

    @Autowired
    ClientDAO clientDAO;

    @Autowired
    OfficeDAO officeDAO;

    @Autowired
    ClientOfficeDAO clientofficeDAO;


    @BeforeEach
    void setUp() {
        List<Client> clients = new ArrayList<>();

        for (int i = 1; i <= 3; ++i) {
            clients.add(new Client("name" + i, "890987324" + i, "890987371" + i, "ajsdfhlkasdfh@gmaol.com", "Moscow d" + i));
        }

        clientDAO.saveCollection(clients);
        System.out.println(clientDAO.getAll().size());
        assert clientDAO.getAll().size() == 3;

        List<Office> offices = new ArrayList<>();

        for (int i = 1; i <= 3; ++i) {
            offices.add(new Office("name" + i, "890987324" + i, "890987371" + i, "ajsdfhlkasdfh@gmaol.com", "Moscow d" + i));
        }

        officeDAO.saveCollection(offices);
        assert officeDAO.getAll().size() == 3;


        Client cfix_1 = clientDAO.getById(1L);
        Client cfix_2 = clientDAO.getById(2L);
        Client cfix_3 = clientDAO.getById(3L);

        Office ofix_1 = officeDAO.getById(1L);
        Office ofix_2 = officeDAO.getById(2L);
        Office ofix_3 = officeDAO.getById(3L);


        List<ClientOffice> cliof = new ArrayList<>();
        cliof.add(new ClientOffice(ofix_1, cfix_1));
        cliof.add(new ClientOffice(ofix_2, cfix_1));
        cliof.add(new ClientOffice(ofix_3, cfix_1));
        cliof.add(new ClientOffice(ofix_1, cfix_2));
        cliof.add(new ClientOffice(ofix_2, cfix_3));
        cliof.add(new ClientOffice(ofix_3, cfix_3));

        clientofficeDAO.saveCollection(cliof);
        assert clientofficeDAO.getAll().size() == 6;









    }

    @AfterEach
    void tearDown() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery("TRUNCATE  office RESTART IDENTITY CASCADE ", Object.class).executeUpdate();
        session.createNativeQuery("TRUNCATE client RESTART IDENTITY CASCADE ", Object.class).executeUpdate();
        session.createNativeQuery("TRUNCATE clientoffice RESTART IDENTITY CASCADE ", Object.class).executeUpdate();
        session.getTransaction().commit();

    }


    @Test
    void getClient() {
        List<Client> list_1= clientofficeDAO.GetClient(1);
        List<Client> list_2= clientofficeDAO.GetClient(2);
        List<Client> list_3= clientofficeDAO.GetClient(3);
        assert list_1.size() == 2;
        assert list_2.size() == 2;
        assert list_3.size() == 2;

    }

    @Test
    void getOffice() {
        List<Office> list_1= clientofficeDAO.GetOffice(1);
        List<Office> list_2= clientofficeDAO.GetOffice(2);
        List<Office> list_3= clientofficeDAO.GetOffice(3);
        assert list_1.size() == 3;
        assert list_2.size() == 1;
        assert list_3.size() == 2;
    }
}