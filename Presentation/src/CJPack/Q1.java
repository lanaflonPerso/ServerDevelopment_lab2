package CJPack;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by cj on 2016-11-02.
 */
@ManagedBean
@SessionScoped
public class Q1 {
    private  String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
