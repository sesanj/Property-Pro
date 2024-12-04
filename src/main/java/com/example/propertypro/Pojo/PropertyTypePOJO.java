package com.example.propertypro.Pojo;

/**
 * Represents a property type with an ID and a descriptive name.
 * This class is a Plain Old Java Object (POJO) used for storing property type data.
 */
public class PropertyTypePOJO {
    private int property_type_id;
    private String property_type;

    /**
     * Constructs a new {@code PropertyTypePOJO} with the specified property type details.
     *
     * @param property_type_id the unique identifier for the property type
     * @param property_type    the name or description of the property type
     */
    public PropertyTypePOJO(int property_type_id, String property_type) {
        this.property_type_id = property_type_id;
        this.property_type = property_type;
    }

    /**
     * Returns the unique identifier for the property type.
     *
     * @return the property type ID
     */
    public int getPropertyType_id() {
        return property_type_id;
    }

    /**
     * Sets the unique identifier for the property type.
     *
     * @param propertyType_id the property type ID to set
     */
    public void setPropertyType_id(int propertyType_id) {
        this.property_type_id = propertyType_id;
    }

    /**
     * Returns the name or description of the property type.
     *
     * @return the property type name
     */
    public String getProperty_type() {
        return property_type;
    }

    /**
     * Sets the name or description of the property type.
     *
     * @param property_type the property type name to set
     */
    public void setProperty_type(String property_type) {
        this.property_type = property_type;
    }

    /**
     * Returns a string representation of the property type, which is the property type name.
     *
     * @return the property type as a {@code String}
     */
    @Override
    public String toString() {
        return property_type;
    }
}
