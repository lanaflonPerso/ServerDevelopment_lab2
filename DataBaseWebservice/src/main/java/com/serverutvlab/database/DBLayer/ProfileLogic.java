package com.serverutvlab.database.DBLayer;

import com.serverutvlab.business.BModels.BProfile;
import com.serverutvlab.database.DBModels.ProfileEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by o_0 on 2016-11-10.
 */
public class ProfileLogic {
    public ProfileEntity getProfileByUserId(int userId) {
        EntityManager entityManager = DBManager.getInstance().createEntityManager();
        Query q = entityManager.createQuery("from ProfileEntity p where  p.userId = ?1");
        q.setParameter(1, userId);
        List<ProfileEntity> profiles = q.getResultList();

        return profiles.size() == 0? null : profiles.get(0);
    }




}
