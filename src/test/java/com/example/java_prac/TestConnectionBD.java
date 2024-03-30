package com.example.java_prac;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class TestConnectionBD {


    @Autowired
    private LocalSessionFactoryBean sessionFactory;


    @Test
    public void test() {
        SessionFactory sessionFactoryObject = sessionFactory.getObject();
        assertNotNull(sessionFactoryObject);
        Session session = sessionFactoryObject.openSession();
        assertNotNull(session);
    }

}

