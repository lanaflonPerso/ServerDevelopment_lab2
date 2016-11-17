package com.serverutvlab.database.DBLayer;

import com.serverutvlab.database.DBModels.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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



    public boolean authenticateUser(String e, String p){
        //List<UserEntity> resultList = entityManager.createQuery("from UserEntity where email=" + e + " and password ="+ p).getResultList();
        EntityManager entityManager = DBManager.getInstance().createEntityManager();
        Query q = entityManager.createQuery("from UserEntity user where user.email = ?1 and user.password = ?2");
        q.setParameter(1, e);
        q.setParameter(2, p);
        List<UserEntity> resultList = q.getResultList();


        System.out.println("UserLogic::authenticateUser list = " + resultList);
        System.out.println("UserLogic::authenticateUser list count = " + resultList.size());

        return resultList.size() == 1;
    }

    public UserEntity getUserById(int id) {
        EntityManager entityManager = DBManager.getInstance().createEntityManager();
        Query q = entityManager.createQuery("from UserEntity user where user.id = ?1");
        q.setParameter(1, id);

        return (UserEntity) q.getSingleResult();
    }
}
