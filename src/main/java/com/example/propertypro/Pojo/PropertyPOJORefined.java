package com.example.propertypro.Pojo;

public class PropertyPOJORefined {
    private int property_id;
    private String name;
    private String property_type;
    private String province;
    private String city;
    private String street;
    private String postal_code;
    private int availability;


    public PropertyPOJORefined(int property_id, String name, String property_type_id, String province_id, String city_id, String street, String postal_code, int availability){
        this.property_id = property_id;
        this.name = name;
        this.property_type = property_type_id;
        this.province = province_id;
        this.city = city_id;
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

    public String getProperty_type() {
        return property_type;
    }

    public void setProperty_type_id(String property_type_id) {
        this.property_type = property_type_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince_id(String province_id) {
        this.province = province_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity_id(String city_id) {
        this.city = city_id;
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
