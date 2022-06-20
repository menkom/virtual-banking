package info.mastera.model;

public class BaseEntity {

    private String id;

    private boolean active = true;

    public String getId() {
        return id;
    }

    public BaseEntity setId(String id) {
        this.id = id;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public BaseEntity setActive(boolean active) {
        this.active = active;
        return this;
    }
}
