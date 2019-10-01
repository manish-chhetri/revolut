package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
    private String id;
    @JsonProperty("user_id")
    private String userId;
    private double balance;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("modified_at")
    private String modifiedAt;

    public Account(String id, double balance, String userId, String createdAt, String modifiedAt) {
        this.id = id;
        this.balance = balance;
        this.userId = userId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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

        Account other = (Account) o;

        if (id != other.id) return false;
        if (balance != other.balance) return false;
        return userId == other.userId;
    }

    @Override
    public int hashCode() {
        int result = Integer.parseInt(id);
        result = 31 * result + (int) balance;
        result = 31 * result + Integer.parseInt(userId);
        return result;
    }
}
