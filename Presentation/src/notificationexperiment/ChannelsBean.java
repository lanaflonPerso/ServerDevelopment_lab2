package notificationexperiment;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cj on 2016-11-03.
 */
@ManagedBean
@ApplicationScoped
public class ChannelsBean {

    Map<String, String> channels = new HashMap<String, String>();

    public void addChannel(String user, String channel){
        channels.put(user,channel);
    }

    public String getChannel(String user){
        return channels.get(user);
    }


}
