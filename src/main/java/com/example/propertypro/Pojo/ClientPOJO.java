package com.example.propertypro.Pojo;

/**
 * Represents a client with personal details such as ID, name, phone number, and email.
 * This class is a Plain Old Java Object (POJO) used to store client data.
 */
public class ClientPOJO {
    private int client_id;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String email;

    /**
     * Constructs a new {@code ClientPOJO} with the specified client details.
     *
     * @param client_id    the unique identifier for the client
     * @param first_name   the first name of the client
     * @param last_name    the last name of the client
     * @param phone_number the phone number of the client
     * @param email        the email address of the client
     */
    public ClientPOJO(int client_id, String first_name, String last_name, String phone_number, String email) {
        this.client_id = client_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email = email;
    }

    /**
     * Constructs a new {@code ClientPOJO} with no initial values.
     */
    public ClientPOJO() {}

    /**
     * Returns the unique identifier for the client.
     *
     * @return the client ID
     */
    public int getClient_id() {
        return client_id;
    }

    /**
     * Sets the unique identifier for the client.
     *
     * @param client_id the client ID to set
     */
    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    /**
     * Returns the first name of the client.
     *
     * @return the client's first name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Sets the first name of the client.
     *
     * @param first_name the client's first name to set
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Returns the last name of the client.
     *
     * @return the client's last name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Sets the last name of the client.
     *
     * @param last_name the client's last name to set
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Returns the phone number of the client.
     *
     * @return the client's phone number
     */
    public String getPhone_number() {
        return phone_number;
    }

    /**
     * Sets the phone number of the client.
     *
     * @param phone_number the client's phone number to set
     */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    /**
     * Returns the email address of the client.
     *
     * @return the client's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the client.
     *
     * @param email the client's email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the client, displaying the full name.
     *
     * @return the full name of the client as {@code first_name + " " + last_name}
     */
    @Override
    public String toString() {
        return first_name + " " + last_name;
    }
}
