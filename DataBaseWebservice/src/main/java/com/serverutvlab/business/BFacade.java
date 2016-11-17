package com.serverutvlab.business;

import com.serverutvlab.business.BModels.BUser;

import java.util.List;

/**
 * Created by cj on 2016-11-17.
 */
public class BFacade {

    public static List<BUser> getAllUsers(){ return new BUserLogic().getAllUsers();}
}
