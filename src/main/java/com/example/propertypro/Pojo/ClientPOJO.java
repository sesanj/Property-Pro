package com.example.propertypro.Pojo;

public class ClientPOJO {
    private int client_id;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String email;

    public ClientPOJO(int client_id, String first_name, String last_name, String phone_number, String email) {
        this.client_id = client_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email = email;
    }

    public ClientPOJO() {

    }


    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return first_name + " " + last_name;
    }
}

