package com.example.propertypro.Pojo;

/**
 * Represents a property with details such as ID, name, type, location, and availability.
 * This class is a Plain Old Java Object (POJO) used to store property data.
 */
public class PropertyPOJO {
    private int property_id;
    private String name;
    private int property_type_id;
    private int province_id;
    private int city_id;
    private String street;
    private String postal_code;
    private int availability;

    /**
     * Constructs a new {@code PropertyPOJO} with the specified property details.
     *
     * @param property_id      the unique identifier for the property
     * @param name             the name of the property
     * @param property_type_id the identifier for the type of the property
     * @param province_id      the identifier for the province where the property is located
     * @param city_id          the identifier for the city where the property is located
     * @param street           the street address of the property
     * @param postal_code      the postal code of the property's location
     * @param availability     the availability status of the property (e.g., 1 for available, 0 for not available)
     */
    public PropertyPOJO(int property_id, String name, int property_type_id, int province_id, int city_id, String street, String postal_code, int availability){
        this.property_id = property_id;
        this.name = name;
        this.property_type_id = property_type_id;
        this.province_id = province_id;
        this.city_id = city_id;
        this.street = street;
        this.postal_code = postal_code;
        this.availability = availability;
    }

    /**
     * Returns the unique identifier for the property.
     *
     * @return the property ID
     */
    public int getProperty_id() {
        return property_id;
    }

    /**
     * Sets the unique identifier for the property.
     *
     * @param property_id the property ID to set
     */
    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }

    /**
     * Returns the name of the property.
     *
     * @return the property name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the property.
     *
     * @param name the property name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the identifier for the property type.
     *
     * @return the property type ID
     */
    public int getProperty_type_id() {
        return property_type_id;
    }

    /**
     * Sets the identifier for the property type.
     *
     * @param property_type_id the property type ID to set
     */
    public void setProperty_type_id(int property_type_id) {
        this.property_type_id = property_type_id;
    }

    /**
     * Returns the identifier for the province where the property is located.
     *
     * @return the province ID
     */
    public int getProvince_id() {
        return province_id;
    }

    /**
     * Sets the identifier for the province where the property is located.
     *
     * @param province_id the province ID to set
     */
    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    /**
     * Returns the identifier for the city where the property is located.
     *
     * @return the city ID
     */
    public int getCity_id() {
        return city_id;
    }

    /**
     * Sets the identifier for the city where the property is located.
     *
     * @param city_id the city ID to set
     */
    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    /**
     * Returns the street address of the property.
     *
     * @return the street address
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street address of the property.
     *
     * @param street the street address to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Returns the postal code of the property's location.
     *
     * @return the postal code
     */
    public String getPostal_code() {
        return postal_code;
    }

    /**
     * Sets the postal code of the property's location.
     *
     * @param postal_code the postal code to set
     */
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    /**
     * Returns the availability status of the property.
     *
     * @return the availability status (e.g., 1 for available, 0 for unavailable)
     */
    public int getAvailability() {
        return availability;
    }

    /**
     * Sets the availability status of the property.
     *
     * @param availability the availability status to set
     */
    public void setAvailability(int availability) {
        this.availability = availability;
    }

    /**
     * Returns a string representation of the property, which is the property name.
     *
     * @return the property name as a {@code String}
     */
    @Override
    public String toString() {
        return name;
    }
}
