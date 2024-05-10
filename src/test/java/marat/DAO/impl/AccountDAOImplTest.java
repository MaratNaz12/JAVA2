package marat.DAO.impl;

import marat.DAO.*;
import marat.models.*;
import marat.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Disabled
@SpringBootTest
class AccountDAOImplTest {


    @Autowired
    ClientDAO clientDAO;

    @Autowired
    OfficeDAO officeDAO;

    @Autowired
    ClientOfficeDAO clientofficeDAO;

    @Autowired
    AccountDAO accountDAO;


    @Autowired
    BankAccTypeDAO bankacctypeDAO;


    @BeforeEach
    void setUp() {
        List<Client> clients = new ArrayList<>();

        for (int i = 1; i <= 3; ++i) {
            clients.add(new Client("name" + i, "890987324" + i, "890987371" + i, "ajsdfhlkasdfh@gmaol.com", "Moscow d" + i));
        }

        clientDAO.saveCollection(clients);
        Assertions.assertEquals(clientDAO.getAll().size(),3);

        List<Office> offices = new ArrayList<>();

        for (int i = 1; i <= 3; ++i) {
            offices.add(new Office("name" + i, "890987324" + i, "890987371" + i, "ajsdfhlkasdfh@gmaol.com", "Moscow d" + i));
        }

        officeDAO.saveCollection(offices);
        Assertions.assertEquals(officeDAO.getAll().size(),3);



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
        Assertions.assertEquals(clientofficeDAO.getAll().size(),6);

        List<BankAccType> baclist = new ArrayList<>();
        baclist.add(new BankAccType("Rare", 0.15, 1L,1L,1L,1L,1L));
        baclist.add(new BankAccType("Often", 0.05, 1L,1L,1L,1L,1L));
        bankacctypeDAO.saveCollection((baclist));

        Assertions.assertEquals(bankacctypeDAO.getAll().size(),2);

        List<Account> accs = new ArrayList<>();
        accs.add(new Account(cfix_1,ofix_1, bankacctypeDAO.getById(1L),0L,0L,10000L));
        accs.add(new Account(cfix_1,ofix_1, bankacctypeDAO.getById(2L),0L,0L,10000L));
        accs.add(new Account(cfix_2,ofix_3, bankacctypeDAO.getById(1L),0L,0L,18000L));
        accs.add(new Account(cfix_3,ofix_2, bankacctypeDAO.getById(1L),0L,0L,200000L));


        accountDAO.saveCollection(accs);
        Assertions.assertEquals(accountDAO.getAll().size(),4);


    }

    @AfterEach
    void tearDown() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery("TRUNCATE  office RESTART IDENTITY CASCADE ", Object.class).executeUpdate();
        session.createNativeQuery("TRUNCATE client RESTART IDENTITY CASCADE ", Object.class).executeUpdate();
        session.createNativeQuery("TRUNCATE clientoffice RESTART IDENTITY CASCADE ", Object.class).executeUpdate();
        session.createNativeQuery("TRUNCATE account RESTART IDENTITY CASCADE ", Object.class).executeUpdate();
        session.createNativeQuery("TRUNCATE bankacctype RESTART IDENTITY CASCADE ", Object.class).executeUpdate();
        session.getTransaction().commit();    }

    @Test
    void getWithFilter() {
        List<Account>  tmp = accountDAO.GetWithFilter("curbalance", 10000);
        Assertions.assertEquals(tmp.size(),2);

    }

    @Test
    void getWithFilterBalanceRange() {
        List<Account>  tmp = accountDAO.GetWithFilterBalanceRange(9000, 19000);
        Assertions.assertEquals(tmp.size(),3);

    }

    @Test
    void getWithFilterBalanceFree() {
        List<Account>  tmp = accountDAO.GetWithFilterBalanceFree("<", 18000);

        Assertions.assertEquals(tmp.size(),2);
        List<Account>  tmp_2 = accountDAO.GetWithFilterBalanceFree("<=", 18000);
        Assertions.assertEquals(tmp_2.size(),3);
    }
}