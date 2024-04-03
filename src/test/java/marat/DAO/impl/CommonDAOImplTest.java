package marat.DAO.impl;

import marat.models.TestEntity;
import marat.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CommonDAOImplTest {

//   private CommonDAOImpl TestDao = CommonDAOImpl();
    private CommonDAOImpl testDAO;


    @BeforeEach
    void setUp() {
//        List<TestEntity> entities = new ArrayList<>();
//        for (int i = 1; i <= 5; ++i) {
//            entities.add(new TestEntity("dummy " + i));
//        }
//
//        testDAO.saveCollection(entities);


    }

    @AfterEach
    void tearDown() {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        session.createNativeQuery("TRUNCATE testentity RESTART IDENTITY", Object.class).executeUpdate();
//        session.getTransaction().commit();

    }



    @Test
    void update() {
//        TestEntity entity_1 = testDAO.getAll().getFirst();
//        entity_1.setName("abc");
//        testDao.update(entity_1);
//        long id = entity_1.getId();
//        TestEntity entity_2 = (TestEntity) testDAO.getById(entity_1_id);
//        assert entity_2.getName() =="abc";

    }

    @Test
    void delete() {
//        List<TestEntity> entity_1 = testDAO.getAll();
//        long int_1 = entity_1.size();
//        TestEntity entity_2 = entity_1.getFirst();
//        testDAO.delete(entity_2);
//        long int_2 = testDAO.getAll().size();
//        assert int_1 - int_2 == 1;

    }
    @Test
    void getById(){
//        TestEntity entity_1 = testDAO.getAll().getFirst();
//        long id = entity_1.getId();
//        TestEntity entity_2 = testDAO.getById(id);
//        assert entity_1 == entity_2;
    }


    @Test
    void deleteById() {
//        TestEntity entity_1 = testDAO.getAll().getFirst();
//        long id = entity_1.getId();
//        testDAO.deleteById(id);
//        assert null ==testDAO.getById(id);
    }
}