package com.serverutvlab.database.DBLayer;

import com.serverutvlab.database.DBModels.PostEntity;
import com.serverutvlab.database.DBModels.ProfileEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by o_0 on 2016-11-10.
 */
public class PostLogic {

    public List<PostEntity> getPostsByProfileId(int id){
        EntityManager entityManager = DBManager.getInstance().createEntityManager();
        Query q = entityManager.createQuery("from PostEntity post where  post.recipientId = ?1");
        q.setParameter(1, id);
        List<PostEntity> resultList = q.getResultList();

        System.out.println("PostLogic::getPostsByProfileId list = " + resultList);
        System.out.println("PostLogic::getPostsByProfileId list count = " + resultList.size());

        return resultList == null? new ArrayList<PostEntity>() : resultList;
    }

    public PostEntity createPost(int autoridId, int recipientId, String subject, String messageBody, ProfileEntity postedTo) {

        PostEntity post = new PostEntity();
        post.setSubject(subject);
        post.setMessageBody(messageBody);
        post.setRecipientId(recipientId);
        post.setAuthorId(autoridId);
        post.setPostedTo(postedTo);

        EntityManager entityManager = DBManager.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(post);
            entityManager.getTransaction().commit();

        }catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            System.out.println("Failed to insert post");
            return null;

        }finally {
            entityManager.close();
        }

        return post;

    }
}
