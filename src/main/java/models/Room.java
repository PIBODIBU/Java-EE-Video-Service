package models;

public class Room {
    private String name;
    private String description;
    private String ownerUID;
    private User owner;
    private String color;
    private String UID;

    public Room(String name, String description, String ownerUID, User owner, String color, String UID) {
        this.name = name;
        this.description = description;
        this.ownerUID = ownerUID;
        this.owner = owner;
        this.color = color;
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOwnerUID() {
        return ownerUID;
    }

    public User getOwner() {
        return owner;
    }

    public String getColor() {
        return color;
    }

    public String getUID() {
        return UID;
    }
}
