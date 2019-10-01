package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String id;
    private String name;
    private String email;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("modified_at")
    private String modifiedAt;

    public User(String id, String name, String email, String createdAt, String modifiedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Account)) return false;

        User other = (User) o;

        if (id != other.id) return false;
        if (name != other.name) return false;
        return email == other.email;
    }

    @Override
    public int hashCode() {
        int result = Integer.parseInt(id);
        result = 31 * result + Integer.parseInt(name);
        result = 31 * result + Integer.parseInt(email);
        return result;
    }
}
