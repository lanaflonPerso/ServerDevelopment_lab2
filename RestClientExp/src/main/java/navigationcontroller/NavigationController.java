
package navigationcontroller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by o_0 on 2016-11-03.
 */
@ManagedBean
@SessionScoped
public class NavigationController {
    public String moveToStart() {
        return "Destination1";
    }

    public String moveToPage() {
        return "Destination2";
    }

    public String moveToSelection(String id) {
        if(id.equals("1")) {
            return "Destination1";
        } else if(id.equals("2")){
            return "index";
        } else if(id.equals("4")){
            return "getdata";
        } else if(id.equals("0")){
            return "index";
        } else if(id.equals("3")){
            return "GetNewFriends";
        }
        return "Destination2.jsf";
    }


}
