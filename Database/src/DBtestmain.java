import EntityORM.MyTestEntity;
import EntityORM.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by o_0 on 2016-11-06.
 */
public class DBtestmain {
    public static void addEntity(EntityManager entityManager, MyTestEntity ent) {
        entityManager.getTransaction().begin();
        entityManager.persist(ent);
        //entityManager.flush();
        entityManager.getTransaction().commit();

    }
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager entityManager = emf.createEntityManager();
        //MyTestEntity ent = new MyTestEntity();

        //ent.setId(4);
//
//        for (int i = 0; i < 10; i++) {
//            MyTestEntity ent = new MyTestEntity();
//            ent.setId(i + 5);
//            ent.setName("hej " + i);
//            addEntity(entityManager,ent);
//        }
        UserEntity u = new UserEntity();
        //u.setId(0);
        u.setEmail("email2");
        u.setPassword("pass2");

        entityManager.getTransaction().begin();
        entityManager.persist(u);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        emf.close();


    }
}
