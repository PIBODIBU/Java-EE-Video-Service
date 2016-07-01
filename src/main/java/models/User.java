package models;

public class User {

    private String name;
    private String uid;

    public User(String name, String uid) {
        this.name = name;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }
}
