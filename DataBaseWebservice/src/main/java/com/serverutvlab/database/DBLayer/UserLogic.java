package com.serverutvlab.database.DBLayer;

import com.serverutvlab.business.BModels.BUser;
import com.serverutvlab.database.DBModels.ProfileEntity;
import com.serverutvlab.database.DBModels.UserEntity;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by o_0 on 2016-11-10.
 */
public class UserLogic {
    //private EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    public UserEntity createNewAccount(String email, String password) {
        UserEntity user = new UserEntity();
        ProfileEntity profile = new ProfileEntity();

        user.setEmail(email);
        user.setPassword(password);

        EntityManager entityManager = DBManager.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.flush();

            profile.setUserId(user.getId());
            entityManager.persist(profile);

            entityManager.getTransaction().commit();

        }catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        }finally {
            entityManager.close();
        }

        return user;
    }

    public List<UserEntity> getAllUsers() {

        EntityManager entityManager = DBManager.getInstance().createEntityManager();
        List<UserEntity> resultList = entityManager.createQuery("from UserEntity ").getResultList();
        System.out.println("UserLogic::getAllUsers list count = " + resultList.size());
        entityManager.close();
        return resultList;
    }



    public BUser authenticateUser(String e, String p){
        //List<UserEntity> resultList = entityManager.createQuery("from UserEntity where email=" + e + " and password ="+ p).getResultList();
        EntityManager entityManager = DBManager.getInstance().createEntityManager();
        Query q = entityManager.createQuery("from UserEntity user where user.email = ?1 and user.password = ?2");
        q.setParameter(1, e);
        q.setParameter(2, p);
        List<UserEntity> resultList = q.getResultList();


        System.out.println("UserLogic::authenticateUser list = " + resultList);
        System.out.println("UserLogic::authenticateUser list count = " + resultList.size());
        BUser user = null;
        if (resultList.size() == 1){
            UserEntity userEntity = resultList.get(0);
            user = new BUser(userEntity.getId(),userEntity.getEmail(),"");
        }

        return user;
    }

    public UserEntity getUserById(int id) {
        EntityManager entityManager = DBManager.getInstance().createEntityManager();
        Query q = entityManager.createQuery("from UserEntity user where user.id = ?1");
        q.setParameter(1, id);

        return (UserEntity) q.getSingleResult();
    }

    public boolean addFriendToUser(int userId, int friendId){
        EntityManager entityManager = DBManager.getInstance().createEntityManager();
            System.out.println("Adding friend to user, uId = "+userId+ ", fId = "+ friendId);
        try {
            entityManager.getTransaction().begin();

            Query q = entityManager.createQuery("from UserEntity user where user.id = ?1");
            q.setParameter(1, userId);

            UserEntity user = (UserEntity) q.getSingleResult();

            Query q2 = entityManager.createQuery("from UserEntity user where user.id = ?1");
            q2.setParameter(1,friendId);

            UserEntity friend = (UserEntity) q2.getSingleResult();

            System.out.println("User: " + user);
            System.out.println("Friend: " + friend);


            if (friend == null){
                System.out.println("friend not found, returning false");
                return false;
            }


            user.getFriends().add(friend);
            friend.getFriends().add(user);
            entityManager.persist(user);
            entityManager.persist(friend);

            entityManager.getTransaction().commit();

        }catch (Exception e) {
            System.out.println("Exeption: " + e.getMessage());
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        }finally {
            entityManager.close();
        }
        return true;
    }

    public List<UserEntity> getFriendsByUserId(int id) {
        EntityManager entityManager = DBManager.getInstance().createEntityManager();
        Query q = entityManager.createQuery("from UserEntity user where user.id = ?1");
        q.setParameter(1, id);
        UserEntity user = (UserEntity) q.getSingleResult();

        return user.getFriends();
    }

    public boolean removeFriend(int userId, int friendId) {
        EntityManager entityManager = DBManager.getInstance().createEntityManager();
        System.out.println("Removing friend from user ");
        try {
            entityManager.getTransaction().begin();

            Query q = entityManager.createQuery("from UserEntity user where user.id = ?1");
            q.setParameter(1, userId);

            UserEntity user = (UserEntity) q.getSingleResult();

            Query q2 = entityManager.createQuery("from UserEntity user where user.id = ?1");
            q2.setParameter(1,friendId);


            UserEntity friend = (UserEntity) q2.getSingleResult();
            System.out.println("User: " + user);
            System.out.println("Friend: " + friend);


            if (friend == null){
                System.out.println("friend not found, returning false");
                return false;
            }

            // Removing the friend from the current user friends
            LinkedList<UserEntity> friends = (LinkedList<UserEntity>) user.getFriends();
            for (UserEntity u : friends){
                if (u.getId() == friendId)
                    friends.remove();
            }
            user.setFriends(friends);
            entityManager.persist(user);


            // Removing current user from the friends list of friends
            friends = (LinkedList<UserEntity>) friend.getFriends();
            for (UserEntity u : friends){
                if (u.getId() == userId)
                    friends.remove();
            }
            friend.setFriends(friends);
            entityManager.persist(friend);

            entityManager.getTransaction().commit();

        }catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        }finally {
            entityManager.close();
        }
        return true;
    }
}
