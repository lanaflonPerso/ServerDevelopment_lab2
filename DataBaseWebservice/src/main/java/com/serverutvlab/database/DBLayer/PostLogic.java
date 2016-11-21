package com.serverutvlab.database.DBLayer;

import com.serverutvlab.database.DBModels.PostEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by o_0 on 2016-11-10.
 */
public class PostLogic {

    public boolean createNewPost(String subject, String messageBody,int authorId,int recipientId) {
        boolean flag = false;

        PostEntity post = new PostEntity();
        post.setSubject(subject);
        post.setMessageBody(messageBody);
        post.setAuthorId(authorId);
        post.setRecipientId(recipientId);

        EntityManager entityManager = DBManager.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(post);
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


    public List<PostEntity> getPostsByProfileId(int id){
        EntityManager entityManager = DBManager.getInstance().createEntityManager();
        Query q = entityManager.createQuery("from PostEntity post where  post.recipientId = ?1");
        q.setParameter(1, id);
        List<PostEntity> resultList = q.getResultList();

        System.out.println("PostLogic::getPostsByProfileId list = " + resultList);
        System.out.println("PostLogic::getPostsByProfileId list count = " + resultList.size());

        return resultList == null? new ArrayList<PostEntity>() : resultList;
    }

}
