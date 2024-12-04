package com.example.propertypro.Pojo;

import java.sql.Timestamp;

/**
 * Represents a refined transaction with details such as the client, property,
 * transaction amount, and timestamp.
 */
public class TransactionPOJORefined {

    private int id;
    private double amount;
    private String client;
    private String property;
    private Timestamp timestamp;

    /**
     * Constructs a new {@code TransactionPOJORefined} with the specified details.
     *
     * @param id        the unique identifier for the transaction
     * @param client    the name or identifier of the client involved in the transaction
     * @param property  the name or identifier of the property involved in the transaction
     * @param amount    the transaction amount
     * @param timestamp the timestamp when the transaction occurred
     */
    public TransactionPOJORefined(int id, String client, String property, double amount, Timestamp timestamp) {
        this.id = id;
        this.amount = amount;
        this.client = client;
        this.property = property;
        this.timestamp = timestamp;
    }

    /**
     * Returns the unique identifier for the transaction.
     *
     * @return the transaction ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the transaction.
     *
     * @param id the transaction ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the transaction amount.
     *
     * @return the transaction amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the transaction amount.
     *
     * @param amount the transaction amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Returns the client involved in the transaction.
     *
     * @return the client name or identifier
     */
    public String getClient_id() {
        return client;
    }

    /**
     * Sets the client involved in the transaction.
     *
     * @param client the client name or identifier to set
     */
    public void setClient_id(String client) {
        this.client = client;
    }

    /**
     * Returns the property involved in the transaction.
     *
     * @return the property name or identifier
     */
    public String getProperty_id() {
        return property;
    }

    /**
     * Sets the property involved in the transaction.
     *
     * @param property the property name or identifier to set
     */
    public void setProperty_id(String property) {
        this.property = property;
    }

    /**
     * Returns the timestamp of when the transaction occurred.
     *
     * @return the transaction timestamp
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of when the transaction occurred.
     *
     * @param timestamp the transaction timestamp to set
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
