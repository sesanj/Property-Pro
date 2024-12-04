package com.example.propertypro.Pojo;

/**
 * Represents a refined property with details such as ID, name, type, location, and availability.
 * This class is a Plain Old Java Object (POJO) used to store property data with descriptive fields.
 */
public class PropertyPOJORefined {
    private int property_id;
    private String name;
    private String property_type;
    private String province;
    private String city;
    private String street;
    private String postal_code;
    private int availability;

    /**
     * Constructs a new {@code PropertyPOJORefined} with the specified property details.
     *
     * @param property_id     the unique identifier for the property
     * @param name            the name of the property
     * @param property_type   the type of the property as a descriptive string
     * @param province        the province where the property is located
     * @param city            the city where the property is located
     * @param street          the street address of the property
     * @param postal_code     the postal code of the property's location
     * @param availability    the availability status of the property (e.g., 1 for available, 0 for unavailable)
     */
    public PropertyPOJORefined(int property_id, String name, String property_type, String province,
                               String city, String street, String postal_code, int availability) {
        this.property_id = property_id;
        this.name = name;
        this.property_type = property_type;
        this.province = province;
        this.city = city;
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
     * Returns the type of the property.
     *
     * @return the property type
     */
    public String getProperty_type() {
        return property_type;
    }

    /**
     * Sets the type of the property.
     *
     * @param property_type the property type to set
     */
    public void setProperty_type_id(String property_type) {
        this.property_type = property_type;
    }

    /**
     * Returns the province where the property is located.
     *
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * Sets the province where the property is located.
     *
     * @param province the province to set
     */
    public void setProvince_id(String province) {
        this.province = province;
    }

    /**
     * Returns the city where the property is located.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city where the property is located.
     *
     * @param city the city to set
     */
    public void setCity_id(String city) {
        this.city = city;
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
