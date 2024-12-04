package TableQuery;

import Dao.PropertyTypeDAO;
import Database.Database;
import com.example.propertypro.Pojo.PropertyTypePOJO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;

/**
 * The PropertyTypeTable class implements the PropertyTypeDAO interface.
 * It provides methods to interact with the "Property Type" table in the database.
 */
public class PropertyTypeTable implements PropertyTypeDAO {

    // Database connection object
    Database db = Database.getNewDatabase();

    /**
     * Retrieves all property types from the database.
     *
     * @return an ArrayList of PropertyTypePOJO objects representing all property types.
     */
    @Override
    public ArrayList<PropertyTypePOJO> getAllPropertyTypes() {
        ArrayList<PropertyTypePOJO> propertyTypes = new ArrayList<>();
        String query = "SELECT * FROM " + PROPERTY_TYPE_TABLE;

        try{
            Statement getPropertyType = db.getConnection().createStatement();
            ResultSet PropertyTypeData = getPropertyType.executeQuery(query);

            // Iterate through the result set and add each property type to the list
            while (PropertyTypeData.next()) {
                propertyTypes.add(new PropertyTypePOJO(PropertyTypeData.getInt(PROPERTY_TYPE_ID), PropertyTypeData.getString(PROPERTY_TYPE_NAME)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return propertyTypes;
    }

    /**
     * Retrieves a property type based on its ID.
     *
     * @param property_type_id the ID of the property type to retrieve.
     * @return a PropertyTypePOJO object representing the property type, or null if not found.
     */
    @Override
    public PropertyTypePOJO getPropertyType(int property_type_id) {
        String query = "SELECT * FROM " + PROPERTY_TYPE_TABLE + " WHERE " + PROPERTY_TYPE_ID + "= " + property_type_id;
        PropertyTypePOJO propertyType = null;

        try{
            Statement getPropertyTypeById = db.getConnection().createStatement();
            ResultSet PropertyTypeData = getPropertyTypeById.executeQuery(query);

            // If a property type is found, create and return the PropertyTypePOJO object
            if (PropertyTypeData.next()) {
                propertyType = new PropertyTypePOJO(PropertyTypeData.getInt(PROPERTY_TYPE_ID), PropertyTypeData.getString(PROPERTY_TYPE_NAME));
                return propertyType;
            }
        } catch (Exception e) {
            throw new RuntimeException(e); // Throw a runtime exception if an error occurs
        }
        return null;
    }

    /**
     * Deletes a property type from the database based on its ID.
     *
     * @param property_type_id the ID of the property type to delete.
     */
    @Override
    public void deletePropertyType(int property_type_id) {
        String query = "DELETE FROM " + PROPERTY_TYPE_TABLE + " WHERE " + PROPERTY_TYPE_ID + " = ?";

        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {
            st.setInt(1, property_type_id); // Set the property type ID to delete
            int rowsAffected = st.executeUpdate();

            // Print message depending on whether the deletion was successful
            if (rowsAffected > 0) {
                System.out.println("Property type with ID " + property_type_id + " was deleted.");
            } else {
                System.out.println("No property type found with ID " + property_type_id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting property type", e); // Handle any SQL exceptions
        }
    }

    /**
     * Updates the name of a property type in the database.
     *
     * @param propertyType the PropertyTypePOJO object containing the updated property type information.
     */
    @Override
    public void updatePropertyType(PropertyTypePOJO propertyType) {
        String query = "UPDATE " + PROPERTY_TYPE_TABLE + " SET " + PROPERTY_TYPE_NAME + " = ? WHERE " + PROPERTY_TYPE_ID + " = ?";

        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {
            st.setString(1, propertyType.getProperty_type());
            st.setInt(2, propertyType.getPropertyType_id());
            int rowsAffected = st.executeUpdate();

            // Print message depending on whether the update was successful
            if (rowsAffected > 0) {
                System.out.println("Property type with ID " + propertyType.getPropertyType_id() + " was updated.");
            } else {
                System.out.println("No property type found with ID " + propertyType.getPropertyType_id());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating property type", e); // Handle any SQL exceptions
        }
    }

    /**
     * Creates a new property type in the database.
     *
     * @param propertyType the PropertyTypePOJO object containing the new property type information.
     */
    @Override
    public void createPropertyType(PropertyTypePOJO propertyType) {  String query = "INSERT INTO " + PROPERTY_TYPE_TABLE + " (" + PROPERTY_TYPE_NAME + ") VALUES (?)";

        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {
            st.setString(1, propertyType.getProperty_type());
            int rowsAffected = st.executeUpdate();

            // Print message depending on whether the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Property type created: " + propertyType.getProperty_type());
            } else {
                System.out.println("Failed to create property type.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating property type", e); // Handle any SQL exceptions
        }
    }
}
