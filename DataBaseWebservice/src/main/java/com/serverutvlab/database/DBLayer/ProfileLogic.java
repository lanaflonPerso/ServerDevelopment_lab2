package com.serverutvlab.database.DBLayer;

import com.serverutvlab.business.BModels.BProfile;
import com.serverutvlab.database.DBModels.ProfileEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by o_0 on 2016-11-10.
 */
public class ProfileLogic {
    public ProfileEntity getProfileByUserId(int userId) {
        EntityManager entityManager = DBManager.getInstance().createEntityManager();
        ProfileEntity profile= null;

        try {
            Query q = entityManager.createQuery("from ProfileEntity p where  p.userId = ?1");
            q.setParameter(1, userId);
            profile = (ProfileEntity) q.getSingleResult();

        } catch (NoResultException e) {
            System.out.println(" no result for query");
            return null;
        }

        return profile;
    }


    public ProfileEntity createProfile(int id) {
        ProfileEntity profile = new ProfileEntity();
        profile.setUserId(id);

        EntityManager entityManager = DBManager.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(profile);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        } finally {
            entityManager.close();
        }
        return profile;
    }

    public ProfileEntity getProfileById(int recipientId) {
        EntityManager entityManager = DBManager.getInstance().createEntityManager();
        ProfileEntity profile= null;

        try {
            Query q = entityManager.createQuery("from ProfileEntity p where  p.id = ?1");
            q.setParameter(1, recipientId);
            profile = (ProfileEntity) q.getSingleResult();

        } catch (NoResultException e) {
            System.out.println(" no result for query");
            return null;
        }

        return profile;

    }

    public boolean updateProfile(int userId, String username, String info, int relationshipStatus, int age) {
        EntityManager entityManager = DBManager.getInstance().createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Query q = entityManager.createQuery("from ProfileEntity p where  p.userId = ?1");
            q.setParameter(1, userId);
            List<ProfileEntity> profiles = q.getResultList();

            if (profiles.size() == 0) {
                System.out.println("Profile not found");
                return false;
            }

            ProfileEntity profile = profiles.get(0);
            System.out.println("Profile to be edited: " + profile);

            profile.setName(username);
            profile.setInfo(info);
            profile.setRelationshipStatus(relationshipStatus);
            profile.setAge(age);

            entityManager.persist(profile);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Exeption, " + e.getMessage());
            entityManager.getTransaction().rollback();
            return false;
        } finally {
            entityManager.close();
        }
        return true;
    }
}
