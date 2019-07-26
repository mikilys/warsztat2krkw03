package pl.coderslab.warsztat2krkw03.model;

public class User_Group {

    private int id;
    private String name;

    public User_Group() {
    }

    public User_Group(String name) {
        this.name = name;
    }

    public User_Group(int id, String name) {
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
}
