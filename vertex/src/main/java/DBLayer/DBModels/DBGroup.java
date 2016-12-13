package DBLayer.DBModels;

/**
 * Created by cj on 2016-12-13.
 */
public class DBGroup {
    private int id;
    private String name;

    public DBGroup() {
    }

    public DBGroup(String name) {
        this.name = name;
    }

    public DBGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DBLayer.DBModels.DBGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "}\n";
    }

    @Override
    public boolean equals(Object obj) {
        DBGroup g = (DBGroup) obj;

        return this.id == g.getId();
    }


}
