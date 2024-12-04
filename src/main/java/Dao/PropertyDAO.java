package Dao;

import com.example.propertypro.Pojo.PropertyPOJO;
import com.example.propertypro.Pojo.PropertyPOJORefined;
import java.util.ArrayList;

/**
 * Interface for Property Data Access Object (DAO).
 * Provides methods for performing CRUD operations (Create, Read, Update, Delete) on Property objects.
 */
public interface PropertyDAO {

    /**
     * Retrieves all properties, refined to include additional details (e.g., property type, province, city).
     *
     * @return An ArrayList of PropertyPOJORefined objects representing all properties.
     */
    public ArrayList<PropertyPOJORefined> getAllProperty();

    /**
     * Retrieves all properties in their raw format (e.g., property ID, name, etc.).
     *
     * @return An ArrayList of PropertyPOJO objects representing all properties in raw form.
     */
    public ArrayList<PropertyPOJO> getAllPropertyRaw();

    /**
     * Retrieves a property by its unique ID.
     *
     * @param property_Id The ID of the property to retrieve.
     * @return A PropertyPOJORefined object representing the property with the given ID.
     */
    public PropertyPOJORefined getPropertyByID(int property_Id);

    /**
     * Retrieves a property by its name.
     *
     * @param name The name of the property to retrieve.
     * @return A PropertyPOJORefined object representing the property with the given name.
     */
    public PropertyPOJORefined getPropertyByName(String name);

    /**
     * Retrieves properties by their type ID.
     *
     * @param property_type_id The ID of the property type to filter by.
     * @return An ArrayList of PropertyPOJORefined objects representing the properties with the given type ID.
     */
    public ArrayList<PropertyPOJORefined> getPropertyByPropertyType(int property_type_id);

    /**
     * Retrieves properties by their province ID.
     *
     * @param province_id The ID of the province to filter properties by.
     * @return An ArrayList of PropertyPOJORefined objects representing the properties in the given province.
     */
    public ArrayList<PropertyPOJORefined> getPropertyByProvince(int province_id);

    /**
     * Retrieves properties by their city ID.
     *
     * @param city_id The ID of the city to filter properties by.
     * @return An ArrayList of PropertyPOJORefined objects representing the properties in the given city.
     */
    public ArrayList<PropertyPOJORefined> getPropertyByCity(int city_id);

    /**
     * Retrieves properties by their availability status.
     *
     * @param availability The availability status to filter properties by (e.g., available or not).
     * @return An ArrayList of PropertyPOJORefined objects representing the properties with the given availability status.
     */
    public ArrayList<PropertyPOJORefined> getPropertyByAvailability(int availability);

    /**
     * Retrieves a property by its postal code.
     *
     * @param postal_code The postal code of the property to retrieve.
     * @return A PropertyPOJORefined object representing the property with the given postal code.
     */
    public PropertyPOJORefined getPropertyByPostalCode(String postal_code);

    /**
     * Deletes a property from the data source.
     *
     * @param property_Id The ID of the property to delete.
     */
    public void deleteProperty(int property_Id);

    /**
     * Updates an existing property in the data source.
     *
     * @param property A PropertyPOJO object containing the updated property data.
     */
    public void updateProperty(PropertyPOJO property);

    /**
     * Creates a new property in the data source.
     *
     * @param property A PropertyPOJO object representing the property to create.
     */
    public void createProperty(PropertyPOJO property);
}
