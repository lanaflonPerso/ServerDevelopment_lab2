package com.serverutvlab.database.DBLayer;

import com.serverutvlab.database.DBModels.UserEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by o_0 on 2016-11-10.
 */
public class UserLogic {
    //private EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    public boolean createNewUser(UserEntity user) {
        boolean flag = false;
        EntityManager entityManager = DBManager.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            flag = true;
        }catch (Exception e) {
            entityManager.getTransaction().rollback();
            flag = false;
        }finally {
            entityManager.close();
        }

        return flag;
    }

    public List<UserEntity> getAllUsers() {

        EntityManager entityManager = DBManager.getInstance().createEntityManager();
        List<UserEntity> resultList = entityManager.createQuery("from UserEntity ").getResultList();
        System.out.println("UserLogic::getAllUsers list count = " + resultList.size());
        entityManager.close();
        return resultList;
    }
}
