package marat.DAO.impl;

import marat.DAO.*;
import marat.models.*;
import marat.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
@SpringBootTest
class OperationDAOImplTest {




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

    @Autowired
    OperationDAO operationDAO;

    @BeforeEach
    void setUp() {
        List<Client> clients = new ArrayList<>();

        for (int i = 1; i <= 3; ++i) {
            clients.add(new Client("name" + i, "890987324" + i, "890987371" + i, "ajsdfhlkasdfh@gmaol.com", "Moscow d" + i));
        }

        clientDAO.saveCollection(clients);
        System.out.println("1111111111111111");
        assert clientDAO.getAll().size() == 3;

        List<Office> offices = new ArrayList<>();

        for (int i = 1; i <= 3; ++i) {
            offices.add(new Office("name" + i, "890987324" + i, "890987371" + i, "ajsdfhlkasdfh@gmaol.com", "Moscow d" + i));
        }

        officeDAO.saveCollection(offices);
        System.out.println("222222222222");
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
        System.out.println("333333333333333333");
        assert clientofficeDAO.getAll().size() == 6;

        List<BankAccType> baclist = new ArrayList<>();
        baclist.add(new BankAccType("Rare", 0.15, 1L, 1L, 1L, 1L, 1L));
        baclist.add(new BankAccType("Often", 0.05, 1L, 1L, 1L, 1L, 1L));
        bankacctypeDAO.saveCollection((baclist));
        System.out.println("44444444444");
        assert bankacctypeDAO.getAll().size() == 2;

        List<Account> accs = new ArrayList<>();

        Account acc_1= new Account(cfix_1, ofix_1, bankacctypeDAO.getById(1L), 0L, 0L, 10000L);
        Account acc_2 = new Account(cfix_1, ofix_1, bankacctypeDAO.getById(2L), 0L, 0L, 10000L);
        Account acc_3 = new Account(cfix_1, ofix_1, bankacctypeDAO.getById(2L), 0L, 0L, 18000L);
        Account acc_4 = new Account(cfix_1, ofix_1, bankacctypeDAO.getById(2L), 0L, 0L, 20000L);
        accs.add(acc_1);
        accs.add(acc_2);
        accs.add(acc_3);
        accs.add(acc_4);



        accountDAO.saveCollection(accs);
        System.out.println("555555555555555");
        assert accountDAO.getAll().size() == 4;


        Timestamp t = new Timestamp(System.currentTimeMillis());
        List<Operation> opers = new ArrayList<>();
        opers.add(new Operation(acc_1, acc_2, 1000L, new Timestamp(System.currentTimeMillis())));
        opers.add(new Operation(acc_2, acc_3, 2000L, new Timestamp(System.currentTimeMillis())));
        opers.add(new Operation(acc_2, acc_1, 4000L, new Timestamp(System.currentTimeMillis())));

        operationDAO.saveCollection(opers);
        System.out.println("66666666666");
        assert operationDAO.getAll().size() == 3;


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
        session.createNativeQuery("TRUNCATE operation RESTART IDENTITY CASCADE ", Object.class).executeUpdate();
        session.getTransaction().commit();
    }

    @Test
    void getWithFilterRangeSum() {
        List<Operation> opl = operationDAO.GetWithFilterRangeSum(1000, 3000);
        assert opl.size() == 2;

    }

    @Test
    void GetByTo() {
        List<Operation> opl = operationDAO.GetByTo(accountDAO.getById(2L));
        assert opl.size() ==2;
    }

    @Test
    void GetByFrom() {
        List<Operation> opl = operationDAO.GetByFrom(accountDAO.getById(2L));
        assert opl.size() == 1;
    }

    @Test
    void getWithFilterFree() {
        List<Operation> opl = operationDAO.GetWithFilterFree("=", 4000);
        assert opl.size() ==1;

    }
    @Test
    void getWithFilterFreeTime() {
        List<Operation> opl = operationDAO.GetWithFilterFreeTime("<", new Timestamp(System.currentTimeMillis()));
        assert opl.size() == 3 ;
    }

    @Test
    void getWithFilterRangeTime() {
        List<Operation> opl = operationDAO.GetWithFilterRangeTime(new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        assert opl.isEmpty();

    }



}