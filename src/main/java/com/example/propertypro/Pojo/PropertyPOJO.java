package com.example.propertypro.Pojo;

public class PropertyPOJO {
    private int property_id;
    private String name;
    private int property_type_id;
    private int province_id;
    private int city_id;
    private String street;
    private String postal_code;
    private int availability;


    public PropertyPOJO(int property_id, String name, int property_type_id, int province_id, int city_id, String street, String postal_code, int availability){
        this.property_id = property_id;
        this.name = name;
        this.property_type_id = property_type_id;
        this.province_id = province_id;
        this.city_id = city_id;
        this.street = street;
        this.availability = availability;
        this.postal_code = postal_code;
    }


    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProperty_type_id() {
        return property_type_id;
    }

    public void setProperty_type_id(int property_type_id) {
        this.property_type_id = property_type_id;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String toString(){
        return name;
    }
}
