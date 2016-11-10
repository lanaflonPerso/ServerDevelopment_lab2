import DBLayer.DBFacade;
import EntityORM.MyTestEntity;
import EntityORM.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Random;

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

    public static void createUser() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager entityManager = emf.createEntityManager();

        UserEntity u = new UserEntity();
        //u.setId(0);
        u.setEmail("email63");
        u.setPassword("pass2");

        entityManager.getTransaction().begin();
        entityManager.persist(u);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        emf.close();
    }

    public static void main(String[] args) {
        UserEntity newUser = new UserEntity();
        //u.setId(0);
        newUser.setEmail("mail" + new Random().nextInt());
        newUser.setPassword("pass2");
        if(DBFacade.createNewUser(newUser)) {
            System.out.println("new user created");
        }else {

            System.out.println("failed to create new user");
        }

        List<UserEntity> users = DBFacade.getAllUsers();
        for (UserEntity u : users) {
            System.out.println("User: " + u.toString());
        }

    }
}
