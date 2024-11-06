package com.example.propertypro.Pojo;

import java.sql.Timestamp;

public class TransactionPOJO {

    private int id;
    private double amount;
    private int client_id;
    private int property_id;
    private Timestamp timestamp;

    public TransactionPOJO(int id, double amount, int client_id, int property_id, Timestamp timestamp){
        this.id=id;
        this.amount=amount;
        this.client_id = client_id;
        this.property_id = property_id;
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

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString(){
        return amount + "";
    }
}
