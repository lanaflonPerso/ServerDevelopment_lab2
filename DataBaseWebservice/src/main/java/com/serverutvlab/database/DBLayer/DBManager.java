package com.serverutvlab.database.DBLayer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by o_0 on 2016-11-10.
 */
public class DBManager {
    private static volatile DBManager db = null;
    private EntityManagerFactory emf;
    private DBManager(){
        //this.dataSource = newMysqlDataSource();
        this.emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    }
    public EntityManager createEntityManager() {
        return this.emf.createEntityManager();
    }
    public void close() {
        this.emf.close();
    }
    /**
     * This is based on the recommendation for the singleton pattern from :
     * https://github.com/iluwatar/java-design-patterns/blob/master/singleton/src/main/java/com/iluwatar/singleton/ThreadSafeDoubleCheckLocking.java
     * Double checking singleton getInstance method.
     * @return the new database managern
     */
    public static DBManager getInstance() {
        DBManager current = db;
        // fast check to see if already created
        if (current == null) {
            // sync so not more then one creates it
            synchronized (DBManager.class) {
                current = db;
                // check if between first check and sync if someone has created it
                if (current == null) {
                    //create it
                    db = current = new DBManager();
                }
            }
        }
        return current;
    }
}
