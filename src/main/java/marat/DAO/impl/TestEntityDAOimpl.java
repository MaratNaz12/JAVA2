package marat.DAO.impl;

import marat.DAO.TestEntityDAO;
import marat.models.TestEntity;
import org.springframework.stereotype.Repository;

@Repository
public class TestEntityDAOimpl extends CommonDAOImpl<TestEntity> implements TestEntityDAO {}