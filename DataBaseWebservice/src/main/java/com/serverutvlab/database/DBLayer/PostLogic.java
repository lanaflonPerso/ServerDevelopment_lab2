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
        EntityManager entityManager = null;
        List<PostEntity> resultList = null;
        try {
            entityManager = DBManager.getInstance().createEntityManager();
            Query q = entityManager.createQuery("from PostEntity post where  post.recipientId = ?1");
            q.setParameter(1, id);
            resultList = q.getResultList();

            Query q2 = entityManager.createQuery("from PostEntity post where  post.authorId = ?1");
            q2.setParameter(1, id);

            resultList.addAll(q2.getResultList());
        }finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        System.out.println("PostLogic::getPostsByProfileId list = " + resultList);
        System.out.println("PostLogic::getPostsByProfileId list count = " + resultList.size());

        return resultList == null? new ArrayList<PostEntity>() : resultList;
    }


    public List<PostEntity> getAllPosts(){
        EntityManager entityManager = null;
        List<PostEntity> resultList = null;
        try {
            entityManager = DBManager.getInstance().createEntityManager();
            Query q = entityManager.createQuery("from PostEntity");
            resultList = q.getResultList();

        }finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return resultList;
    }

    public PostEntity createPost(int auhtoridId, int recipientId, String subject, String messageBody, ProfileEntity postedTo, ProfileEntity postedFrom, boolean isPrivate) {

        PostEntity post = new PostEntity();
        post.setSubject(subject);
        post.setMessageBody(messageBody);
        post.setRecipientId(recipientId);
        post.setAuthorId(auhtoridId);
        post.setPostedTo(postedTo);
        post.setTimestamp(new Timestamp(System.currentTimeMillis()));
        post.setAuthorName(postedFrom.getName());
        post.setRecipientName(postedTo.getName());
        post.setPrivate(isPrivate);
        System.out.println("Inserting post: "+post.toString());

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
