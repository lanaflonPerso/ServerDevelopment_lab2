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
        return "newHome";
    }

    public String moveToPage() {
        return "newHome";
    }

    public String moveToSelection(String id) {
        if(id.equals("1")) {
            return "newHome";
        }else if(id.equals("2")){
            return "GetNewFriends";
        }else if(id.equals("4")){
            return "chatwindow";
        }else if(id.equals("0")){
            return "index";
        }
        return "index";
    }


}
