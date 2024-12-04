package com.example.propertypro.Pojo;

import java.sql.Timestamp;

/**
 * Represents a transaction in the system with details about the amount,
 * client, property, and timestamp.
 */
public class TransactionPOJO {

    private int id;
    private double amount;
    private int client_id;
    private int property_id;
    private Timestamp timestamp;

    /**
     * Constructs a new {@code TransactionPOJO} with the specified details.
     *
     * @param id          the unique identifier for the transaction
     * @param amount      the transaction amount
     * @param client_id   the ID of the client associated with the transaction
     * @param property_id the ID of the property associated with the transaction
     * @param timestamp   the timestamp when the transaction occurred
     */
    public TransactionPOJO(int id, double amount, int client_id, int property_id, Timestamp timestamp){
        this.id = id;
        this.amount = amount;
        this.client_id = client_id;
        this.property_id = property_id;
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
     * Returns the ID of the client associated with the transaction.
     *
     * @return the client ID
     */
    public int getClient_id() {
        return client_id;
    }

    /**
     * Sets the ID of the client associated with the transaction.
     *
     * @param client_id the client ID to set
     */
    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    /**
     * Returns the ID of the property associated with the transaction.
     *
     * @return the property ID
     */
    public int getProperty_id() {
        return property_id;
    }

    /**
     * Sets the ID of the property associated with the transaction.
     *
     * @param property_id the property ID to set
     */
    public void setProperty_id(int property_id) {
        this.property_id = property_id;
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

    /**
     * Returns a string representation of the transaction.
     * This representation only includes the transaction amount.
     *
     * @return the transaction amount as a {@code String}
     */
    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}
