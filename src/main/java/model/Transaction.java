package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Transaction {
    private long id;
    public int sender;
    private int receiver;
    private double amount;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("modified_at")
    private String modifiedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
        if (o == null || getClass() != o.getClass()) return false;

        Transaction transaction = (Transaction) o;

        if (id != transaction.id) return false;
        if (sender != transaction.sender) return false;
        return receiver == transaction.receiver;
    }

    @Override
    public int hashCode() {
        int result = Math.toIntExact(id);
        result = 31 * result + sender;
        result = 31 * result + receiver;
        return result;
    }
}
