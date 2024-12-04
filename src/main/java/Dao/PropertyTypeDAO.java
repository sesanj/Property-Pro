package Dao;

import com.example.propertypro.Pojo.PropertyTypePOJO;
import java.util.ArrayList;

/**
 * Interface for Property Type Data Access Object (DAO).
 * Provides methods for performing CRUD operations (Create, Read, Update, Delete) on Property Type objects.
 */
public interface PropertyTypeDAO {

    /**
     * Retrieves all property types.
     *
     * @return An ArrayList of PropertyTypePOJO objects representing all property types.
     */
    public ArrayList<PropertyTypePOJO> getAllPropertyTypes();

    /**
     * Retrieves a property type by its unique ID.
     *
     * @param property_type_id The ID of the property type to retrieve.
     * @return A PropertyTypePOJO object representing the property type with the given ID.
     */
    public PropertyTypePOJO getPropertyType(int property_type_id);

    /**
     * Deletes a property type from the data source.
     *
     * @param property_type_id The ID of the property type to delete.
     */
    public void deletePropertyType(int property_type_id);

    /**
     * Updates an existing property type in the data source.
     *
     * @param propertyType A PropertyTypePOJO object containing the updated property type data.
     */
    public void updatePropertyType(PropertyTypePOJO propertyType);

    /**
     * Creates a new property type in the data source.
     *
     * @param propertyType A PropertyTypePOJO object representing the property type to create.
     */
    public void createPropertyType(PropertyTypePOJO propertyType);
}
