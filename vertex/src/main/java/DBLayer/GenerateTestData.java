package DBLayer;

import DBLayer.DBModels.DBMessage;

/**
 * Created by cj on 2016-12-13.
 */
public class GenerateTestData {

    public static void main(String[] args) {
        generate();
    }

    public static void generate(){
      try {


          DBManager.getInstance().getGroupDAO().insertGroup("grupp1");
          DBManager.getInstance().getGroupDAO().insertGroup("grupp2");
          DBManager.getInstance().getGroupDAO().insertGroup("e1");


          DBMessage msg1 = new DBMessage();
          msg1.setFromId(1);
          msg1.setToId(3);
          msg1.setText("nr1 from 1 to 3");

          DBMessage msg4 = new DBMessage();
          msg4.setFromId(3);
          msg4.setToId(1);
          msg4.setText("nr1 from 3 to 1");



          DBMessage msg2 = new DBMessage();
          msg2.setFromId(1);
          msg2.setToId(3);
          msg2.setText("nr2 from 1 to 3");

          DBMessage msg5 = new DBMessage();
          msg5.setFromId(3);
          msg5.setToId(1);
          msg5.setText("nr2 from 3 to 1");



          DBMessage msg6 = new DBMessage();
          msg6.setFromId(3);
          msg6.setToId(1);
          msg6.setText("nr3 from 3 to 1");

          DBMessage msg3 = new DBMessage();
          msg3.setFromId(1);
          msg3.setToId(3);
          msg3.setText("nr3 from 1 to 3");


          DBManager.getInstance().getMessageDAO().insertMessageToUser(msg1);
          DBManager.getInstance().getMessageDAO().insertMessageToUser(msg4);

          DBManager.getInstance().getMessageDAO().insertMessageToUser(msg2);
          DBManager.getInstance().getMessageDAO().insertMessageToUser(msg5);

          DBManager.getInstance().getMessageDAO().insertMessageToUser(msg3);
          DBManager.getInstance().getMessageDAO().insertMessageToUser(msg6);


          DBMessage gmsg1 = new DBMessage();
          gmsg1.setFromId(1);
          gmsg1.setGroupId(1);
          gmsg1.setText("nr1 from 1 to grp 1");

          DBMessage gmsg2 = new DBMessage();
          gmsg2.setFromId(2);
          gmsg2.setGroupId(1);
          gmsg2.setText("nr2 from 2 to grp 1");

          DBMessage gmsg3 = new DBMessage();
          gmsg3.setFromId(1);
          gmsg3.setGroupId(1);
          gmsg3.setText("nr3 from 1 to grp 1");

          DBMessage gmsg4 = new DBMessage();
          gmsg4.setFromId(1);
          gmsg4.setGroupId(2);
          gmsg4.setText("nr1 from 1 to grp 2");

          DBMessage gmsg5 = new DBMessage();
          gmsg5.setFromId(2);
          gmsg5.setGroupId(2);
          gmsg5.setText("nr2 from 2 to grp 2");

          DBMessage gmsg6 = new DBMessage();
          gmsg6.setFromId(1);
          gmsg6.setGroupId(2);
          gmsg6.setText("nr3 from 1 to grp 2");

          DBManager.getInstance().getMessageDAO().insertMessageToGroup(gmsg1);
          DBManager.getInstance().getMessageDAO().insertMessageToGroup(gmsg2);
          DBManager.getInstance().getMessageDAO().insertMessageToGroup(gmsg3);
          DBManager.getInstance().getMessageDAO().insertMessageToGroup(gmsg4);
          DBManager.getInstance().getMessageDAO().insertMessageToGroup(gmsg5);
          DBManager.getInstance().getMessageDAO().insertMessageToGroup(gmsg6);

      } catch (Exception e){
          e.printStackTrace();
      }
    }
}
