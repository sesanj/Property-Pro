package com.example.propertypro.Pojo;

import java.sql.Timestamp;

public class TransactionPOJORefined {


    private int id;
    private double amount;
    private String client;
    private String property;
    private Timestamp timestamp;

    public TransactionPOJORefined(int id, String client, String property, double amount, Timestamp timestamp){
        this.id=id;
        this.amount=amount;
        this.client = client;
        this.property = property;
        this.timestamp=timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getClient_id() {
        return client;
    }

    public void setClient_id(String client) {
        this.client = client;
    }

    public String getProperty_id() {
        return property;
    }

    public void setProperty_id(String property) {
        this.property = property;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
