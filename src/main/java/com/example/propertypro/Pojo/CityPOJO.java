package com.example.propertypro.Pojo;

/**
 * Represents a city with an ID and a name.
 * This class is a Plain Old Java Object (POJO) used to store city data.
 */
public class CityPOJO {

    private int city_id;
    private String city;

    /**
     * Constructs a new {@code CityPOJO} with the specified city ID and name.
     *
     * @param city_id the unique identifier for the city
     * @param city    the name of the city
     */
    public CityPOJO(int city_id, String city) {
        this.city_id = city_id;
        this.city = city;
    }

    /**
     * Returns the unique identifier for the city.
     *
     * @return the city ID
     */
    public int getCity_id() {
        return city_id;
    }

    /**
     * Sets the unique identifier for the city.
     *
     * @param city_id the city ID to set
     */
    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    /**
     * Returns the name of the city.
     *
     * @return the city name
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the name of the city.
     *
     * @param city the city name to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the string representation of the city, which is the city name.
     *
     * @return the city name as a {@code String}
     */
    @Override
    public String toString() {
        return city;
    }
}
