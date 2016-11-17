package com.serverutvlab.business;

import com.serverutvlab.business.BModels.BUser;
import com.serverutvlab.database.DBLayer.DBFacade;

import java.util.List;

/**
 * Created by cj on 2016-11-17.
 */
public class BUserLogic {

    public List<BUser> getAllUsers() {
        return DBFacade.getAllUsers();
    }
}
