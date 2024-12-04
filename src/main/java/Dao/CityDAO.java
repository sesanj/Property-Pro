package Dao;

import com.example.propertypro.Pojo.CityPOJO;
import java.util.ArrayList;

/**
 * Interface for City Data Access Object (DAO).
 * Provides methods for performing CRUD operations (Create, Read, Update, Delete) on City objects.
 */
public interface CityDAO {

    /**
     * Retrieves all cities from the data source.
     *
     * @return An ArrayList of CityPOJO objects representing all cities.
     */
    public ArrayList<CityPOJO> getAllCities();

    /**
     * Retrieves a city by its ID.
     *
     * @param CityId The ID of the city to retrieve.
     * @return A CityPOJO object representing the city with the given ID.
     */
    public CityPOJO getCityByID(int CityId);

    /**
     * Deletes a city from the data source.
     *
     * @param cityId The ID of the city to delete.
     */
    public void deleteCity(int cityId);

    /**
     * Updates an existing city in the data source.
     *
     * @param city A CityPOJO object containing the updated city data.
     */
    public void updateCity(CityPOJO city);

    /**
     * Creates a new city in the data source.
     *
     * @param city A CityPOJO object representing the city to create.
     */
    public void createCity(CityPOJO city);
}