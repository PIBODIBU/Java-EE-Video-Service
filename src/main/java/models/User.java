package models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

    private String name;
    private String uid;

    public User(String name, String uid) {
        this.name = name;
        this.uid = uid;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    @XmlElement(name = "uid")
    public String getUid() {
        return uid;
    }
}
