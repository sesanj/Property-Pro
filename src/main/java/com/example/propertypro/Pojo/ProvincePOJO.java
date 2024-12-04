package com.example.propertypro.Pojo;

/**
 * Represents a province with a unique ID and name.
 * This class is a Plain Old Java Object (POJO) used to store province data.
 */
public class ProvincePOJO {
    private int province_id;
    private String province;

    /**
     * Constructs a new {@code ProvincePOJO} with the specified province details.
     *
     * @param province_id the unique identifier for the province
     * @param province    the name of the province
     */
    public ProvincePOJO(int province_id, String province) {
        this.province_id = province_id;
        this.province = province;
    }

    /**
     * Returns the unique identifier for the province.
     *
     * @return the province ID
     */
    public int getProvince_id() {
        return province_id;
    }

    /**
     * Sets the unique identifier for the province.
     *
     * @param province_id the province ID to set
     */
    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    /**
     * Returns the name of the province.
     *
     * @return the province name
     */
    public String getProvince() {
        return province;
    }

    /**
     * Sets the name of the province.
     *
     * @param province the province name to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Returns a string representation of the province, which is the province name.
     *
     * @return the province name as a {@code String}
     */
    @Override
    public String toString() {
        return province;
    }
}
