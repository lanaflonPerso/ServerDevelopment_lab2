package EntityORM;

import javax.persistence.*;

/**
 * Created by o_0 on 2016-11-06.
 */
@Entity
public class MyTestEntity {


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    private int id;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
