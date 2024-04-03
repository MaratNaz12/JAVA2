package marat.DAO.impl;

import marat.models.TestEntity;
import marat.DAO.TestEntityDAO;
import marat.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommonDAOTest {

//   private CommonDAOImpl TestDao = CommonDAOImpl();
    @Autowired
    TestEntityDAO testEntityDAO;


    @BeforeEach
    void setUp() {
        List<TestEntity> entities = new ArrayList<>();

        for (int i = 1; i <= 5; ++i) {
            entities.add(new TestEntity("dummy " + i));
        }

        testEntityDAO.saveCollection(entities);
        assert testEntityDAO.getAll().size() == 5;
    }

    @AfterEach
    void tearDown() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery("TRUNCATE testentity RESTART IDENTITY", Object.class).executeUpdate();
        session.getTransaction().commit();

    }

    @Test
    void save(){
        TestEntity testEntity = new TestEntity("hello");
        long ent_num = testEntityDAO.getAll().size();
        testEntityDAO.save(testEntity);
        assert testEntityDAO.getAll().size() - ent_num == 1;
    }

    @Test
    void update() {
        TestEntity entity_1 = testEntityDAO.getAll().getFirst();
        String name_f = entity_1.getName();
        entity_1.setName("abc");
        testEntityDAO.update(entity_1);
        long id = entity_1.getId();
        TestEntity entity_2 = testEntityDAO.getById(id);
        assert entity_2.getName().equals("abc");

    }

    @Test
    void delete() {
        List<TestEntity> entity_1 = testEntityDAO.getAll();
        long int_1 = entity_1.size();
        TestEntity entity_2 = entity_1.getFirst();
        testEntityDAO.delete(entity_2);
        long int_2 = testEntityDAO.getAll().size();
        assert int_1 - int_2 == 1;

    }
    @Test
    void getById(){
        TestEntity entity_1 = testEntityDAO.getAll().getFirst();
        long id = entity_1.getId();
        System.out.println(id);
        TestEntity entity_2 = testEntityDAO.getById(id);

        assert entity_1.getId() == entity_2.getId();
        assert entity_1.getName().equals(entity_2.getName());

    }


    @Test
    void deleteById() {
        TestEntity entity_1 = (TestEntity) testEntityDAO.getAll().getFirst();
        long id = entity_1.getId();
        testEntityDAO.deleteById(id);
        assert testEntityDAO.getById(id) == null;
    }
}