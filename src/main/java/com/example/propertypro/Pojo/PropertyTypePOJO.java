package com.example.propertypro.Pojo;

public class PropertyTypePOJO {
    private int property_type_id;
    private String property_type;

    public PropertyTypePOJO(int property_type_id, String property_type){
        this.property_type_id=property_type_id;
        this.property_type=property_type;
    }

    public int getPropertyType_id() {
        return property_type_id;
    }

    public void setPropertyType_id(int propertyType_id) {
        this.property_type_id = propertyType_id;
    }

    public String getProperty_type() {
        return property_type;
    }

    public void setProperty_type(String property_type) {
        this.property_type = property_type;
    }
    @Override
    public String toString() {
        return property_type;
    }
}
