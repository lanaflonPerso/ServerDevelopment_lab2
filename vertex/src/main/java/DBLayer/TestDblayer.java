package DBLayer;

/**
 * Created by cj on 2016-12-13.
 */
public class TestDblayer {
    public static void main(String[] args) {


        System.out.println(DBFacade.getGroupById(1));
        System.out.println(DBFacade.getGroupById(2));
        System.out.println(DBFacade.getGroups());

        System.out.println("Messages mellan 1 och 2: \n"+DBFacade.getMessagesBetweenUsers(1,3));
        System.out.println("Messages mellan 2 och 1: \n"+DBFacade.getMessagesBetweenUsers(3,1));

        System.out.println("Messages grupp 1: "+DBFacade.getMessagesByGroup(1));
        System.out.println("Messages grupp 2: "+DBFacade.getMessagesByGroup(2));











    }




}
