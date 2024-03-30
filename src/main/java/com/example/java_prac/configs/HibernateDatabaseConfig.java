package com.example.java_prac.configs;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("file:/home/marat/JAVA2/src/main/resources/application.properties")
public class HibernateDatabaseConfig {
    @Value("${driver}")
    private String DB_DRIVER;
    @Value("${url}")
    private String DB_URL;
    @Value("${username}")
    private String DB_USERNAME;
    @Value("${password}")
    private String DB_PASSWORD;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        System.out.println(DB_URL);
        System.out.println(DB_DRIVER);
        System.out.println(DB_PASSWORD);
        System.out.println(DB_USERNAME);
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        System.out.println(1);
        sessionFactory.setDataSource(oraDataSource());
        System.out.println(2);
        sessionFactory.setPackagesToScan("com.example.java_prac.models");
        System.out.println(3);

        Properties hibernateProperties = new Properties();
        System.out.println(4);
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        System.out.println(5);
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        System.out.println(6);
        hibernateProperties.setProperty("connection_pool_size", "1");
        System.out.println(7);

        sessionFactory.setHibernateProperties(hibernateProperties);
        System.out.println(8);

        return sessionFactory;
    }

    @Bean
    public DataSource oraDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);

        return dataSource;
    }
}