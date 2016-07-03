package models;

public class Room {
    private String name;
    private String description;
    private String ownerUID;
    private User owner;
    private String color;
    private String UID;

    private String value_time;

    public Room(String name, String description, String ownerUID, User owner, String color, String UID, String value_time) {
        this.name = name;
        this.description = description;
        this.ownerUID = ownerUID;
        this.owner = owner;
        this.color = color;
        this.UID = UID;

        this.value_time = value_time;
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

    public String getValue_time() {
        return value_time;
    }
}
