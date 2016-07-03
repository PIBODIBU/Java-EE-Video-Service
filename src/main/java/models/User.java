package models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

    private String name;
    private String surName;
    private String uid;

    public User(String name, String surName, String uid) {
        this.name = name;
        this.surName = surName;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getUid() {
        return uid;
    }
}
