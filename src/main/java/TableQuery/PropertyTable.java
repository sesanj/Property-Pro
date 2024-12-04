package TableQuery;

import Dao.PropertyDAO;
import Database.Database;
import com.example.propertypro.Pojo.PropertyPOJO;
import com.example.propertypro.Pojo.PropertyPOJORefined;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;

/**
 * This class implements the PropertyDAO interface and provides methods for
 * interacting with the property-related data in the database.
 */
public class PropertyTable implements PropertyDAO {

    public Database db = Database.getNewDatabase();

    /**
     * Retrieves all properties with refined data.
     *
     * @return an ArrayList of PropertyPOJORefined objects containing detailed property information.
     */
    @Override
    public ArrayList<PropertyPOJORefined> getAllProperty() {

        ArrayList<PropertyPOJORefined> properties = new ArrayList<>();

        String query = "SELECT p." + PROPERTY_ID + ", p." + PROPERTY_NAME + ", t." + PROPERTY_TYPE_NAME + ", v." + PROVINCE_NAME + ", c." + CITY_NAME +
                ", p." + PROPERTY_STREET + ", p." + PROPERTY_POSTAL_CODE + ", p." + PROPERTY_AVAILABILITY +
                " FROM " + PROPERTY_TABLE + " p " +
                "JOIN " + PROPERTY_TYPE_TABLE + " t ON p." + PROPERTY_PROPERTY_TYPE_ID + " = t." + PROPERTY_TYPE_ID +
                " JOIN " + PROVINCE_TABLE + " v ON p." + PROPERTY_PROVINCE_ID + " = v." + PROVINCE_ID +
                " JOIN " + CITY_TABLE + " c ON p." + PROPERTY_CITY_ID + " = c." + CITY_ID ;

        try{
            Statement getProperties = db.getConnection().createStatement();
            ResultSet propertyData = getProperties.executeQuery(query);

            while(propertyData.next()){

                properties.add(new PropertyPOJORefined(propertyData.getInt(PROPERTY_ID), propertyData.getString(PROPERTY_NAME), propertyData.getString(PROPERTY_TYPE_NAME), propertyData.getString(PROVINCE_NAME), propertyData.getString(CITY_NAME), propertyData.getString(PROPERTY_STREET),
                        propertyData.getString(PROPERTY_POSTAL_CODE), propertyData.getInt(PROPERTY_AVAILABILITY)));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return properties;
    }

    /**
     * Retrieves all properties with raw data (basic fields).
     *
     * @return an ArrayList of PropertyPOJO objects containing basic property information.
     */
    @Override
    public ArrayList<PropertyPOJO> getAllPropertyRaw() {

        ArrayList<PropertyPOJO> properties = new ArrayList<>();

        String query = "SELECT * FROM " + PROPERTY_TABLE;

        try{
            Statement getProperties = db.getConnection().createStatement();
            ResultSet propertyData = getProperties.executeQuery(query);

            while(propertyData.next()){
                properties.add(new PropertyPOJO(propertyData.getInt(PROPERTY_ID), propertyData.getString(PROPERTY_NAME), propertyData.getInt(PROPERTY_PROPERTY_TYPE_ID), propertyData.getInt(PROPERTY_PROVINCE_ID), propertyData.getInt(PROPERTY_CITY_ID), propertyData.getString(PROPERTY_STREET),
                        propertyData.getString(PROPERTY_POSTAL_CODE), propertyData.getInt(PROPERTY_AVAILABILITY)));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return properties;
    }

    /**
     * Retrieves a property by its ID with refined data.
     *
     * @param property_Id the ID of the property.
     * @return a PropertyPOJORefined object containing the property details, or null if not found.
     */
    @Override
    public PropertyPOJORefined getPropertyByID(int property_Id) {

        String query = "SELECT p." + PROPERTY_ID + ", p." + PROPERTY_NAME + ", t." + PROPERTY_TYPE_NAME + ", v." + PROVINCE_NAME + ", c." + CITY_NAME +
                ", p." + PROPERTY_STREET + ", p." + PROPERTY_POSTAL_CODE + ", p." + PROPERTY_AVAILABILITY + " FROM " + PROPERTY_TABLE + " p " +
                "JOIN " + PROPERTY_TYPE_TABLE + " t ON p." + PROPERTY_PROPERTY_TYPE_ID + " = t." + PROPERTY_TYPE_ID +
                " JOIN " + PROVINCE_TABLE + " v ON p." + PROPERTY_PROVINCE_ID + " = v." + PROVINCE_ID +
                " JOIN " + CITY_TABLE + " c ON p." + PROPERTY_CITY_ID + " = c." + CITY_ID +
                " WHERE " + PROPERTY_ID + " = " + property_Id;

        try{
            Statement getPropertyById = db.getConnection().createStatement();
            ResultSet propertyData = getPropertyById.executeQuery(query);

            if(propertyData.next()){

                PropertyPOJORefined  property = new PropertyPOJORefined(propertyData.getInt(PROPERTY_ID), propertyData.getString(PROPERTY_NAME), propertyData.getString(PROPERTY_TYPE_NAME), propertyData.getString(PROVINCE_NAME), propertyData.getString(CITY_NAME), propertyData.getString(PROPERTY_STREET),
                        propertyData.getString(PROPERTY_POSTAL_CODE), propertyData.getInt(PROPERTY_AVAILABILITY));

                return property;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Retrieves a property based on the given property ID.
     *
     * @param propertyId the ID of the property to be retrieved
     * @return a PropertyPOJO object representing the property, or null if no property was found
     */
    public PropertyPOJO getPropertyRaw(int propertyId) {

        String query = "SELECT * FROM " + PROPERTY_TABLE + " WHERE " + PROPERTY_ID + " = " + propertyId;

        try {
            Statement getProperties = db.getConnection().createStatement();
            ResultSet propertyData = getProperties.executeQuery(query);

            if(propertyData.next()){

                PropertyPOJO  property = new PropertyPOJO(propertyData.getInt(PROPERTY_ID), propertyData.getString(PROPERTY_NAME), propertyData.getInt(PROPERTY_PROPERTY_TYPE_ID), propertyData.getInt(PROPERTY_PROVINCE_ID), propertyData.getInt(PROPERTY_CITY_ID), propertyData.getString(PROPERTY_STREET),
                        propertyData.getString(PROPERTY_POSTAL_CODE), propertyData.getInt(PROPERTY_AVAILABILITY));

                return property;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Retrieves a property based on the given name. This method performs a join with property type, province, and city.
     *
     * @param name the name of the property to be retrieved
     * @return a PropertyPOJORefined object representing the property, or null if no property was found
     */
    @Override
    public PropertyPOJORefined getPropertyByName(String name) {

        String query = "SELECT p." + PROPERTY_ID + ", p." + PROPERTY_NAME + ", t." + PROPERTY_TYPE_NAME + ", v." + PROVINCE_NAME + ", c." + CITY_NAME +
                ", p." + PROPERTY_STREET + ", p." + PROPERTY_POSTAL_CODE + ", p." + PROPERTY_AVAILABILITY + " FROM " + PROPERTY_TABLE + " p " +
                "JOIN " + PROPERTY_TYPE_TABLE + " t ON p." + PROPERTY_PROPERTY_TYPE_ID + " = t." + PROPERTY_TYPE_ID +
                " JOIN " + PROVINCE_TABLE + " v ON p." + PROPERTY_PROVINCE_ID + " = v." + PROVINCE_ID +
                " JOIN " + CITY_TABLE + " c ON p." + PROPERTY_CITY_ID + " = c." + CITY_ID +
                " WHERE " + PROPERTY_NAME + " = " + name;

        try {
            Statement getPropertyByName = db.getConnection().createStatement();
            ResultSet propertyData = getPropertyByName.executeQuery(query);

            if(propertyData.next()){

                PropertyPOJORefined  property = new PropertyPOJORefined(propertyData.getInt(PROPERTY_ID), propertyData.getString(PROPERTY_NAME), propertyData.getString(PROPERTY_TYPE_NAME), propertyData.getString(PROVINCE_NAME), propertyData.getString(CITY_NAME), propertyData.getString(PROPERTY_STREET),
                        propertyData.getString(PROPERTY_POSTAL_CODE), propertyData.getInt(PROPERTY_AVAILABILITY));

                return property;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves a list of properties based on the property type ID. This method performs a join with property type, province, and city.
     *
     * @param property_type_id the ID of the property type to filter properties by
     * @return a list of PropertyPOJORefined objects representing the properties matching the given property type ID
     */
    @Override
    public ArrayList<PropertyPOJORefined> getPropertyByPropertyType(int property_type_id) {

        ArrayList<PropertyPOJORefined> allProperties = new ArrayList<>();

        String query = "SELECT p." + PROPERTY_ID + ", p." + PROPERTY_NAME + ", t." + PROPERTY_TYPE_NAME + ", v." + PROVINCE_NAME + ", c." + CITY_NAME +
                ", p." + PROPERTY_STREET + ", p." + PROPERTY_POSTAL_CODE + ", p." + PROPERTY_AVAILABILITY + " FROM " + PROPERTY_TABLE + " p " +
                "JOIN " + PROPERTY_TYPE_TABLE + " t ON p." + PROPERTY_PROPERTY_TYPE_ID + " = t." + PROPERTY_TYPE_ID +
                " JOIN " + PROVINCE_TABLE + " v ON p." + PROPERTY_PROVINCE_ID + " = v." + PROVINCE_ID +
                " JOIN " + CITY_TABLE + " c ON p." + PROPERTY_CITY_ID + " = c." + CITY_ID +
                " WHERE p." + PROPERTY_PROPERTY_TYPE_ID + " = " + property_type_id;

        try {
            Statement getProperties = db.getConnection().createStatement();
            ResultSet propertyData = getProperties.executeQuery(query);

            while(propertyData.next()){

                allProperties.add(new PropertyPOJORefined(propertyData.getInt(PROPERTY_ID), propertyData.getString(PROPERTY_NAME), propertyData.getString(PROPERTY_TYPE_NAME), propertyData.getString(PROVINCE_NAME), propertyData.getString(CITY_NAME), propertyData.getString(PROPERTY_STREET),
                        propertyData.getString(PROPERTY_POSTAL_CODE), propertyData.getInt(PROPERTY_AVAILABILITY)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return allProperties;
    }

    /**
     * Retrieves a list of properties based on the province ID. This method performs a join with property type, province, and city.
     *
     * @param province_id the ID of the province to filter properties by
     * @return a list of PropertyPOJORefined objects representing the properties matching the given province ID
     */
    @Override
    public ArrayList<PropertyPOJORefined> getPropertyByProvince(int province_id) {

        ArrayList<PropertyPOJORefined> allProperties = new ArrayList<>();

        String query = "SELECT p." + PROPERTY_ID + ", p." + PROPERTY_NAME + ", t." + PROPERTY_TYPE_NAME + ", v." + PROVINCE_NAME + ", c." + CITY_NAME +
                ", p." + PROPERTY_STREET + ", p." + PROPERTY_POSTAL_CODE + ", p." + PROPERTY_AVAILABILITY + " FROM " + PROPERTY_TABLE + " p " +
                "JOIN " + PROPERTY_TYPE_TABLE + " t ON p." + PROPERTY_PROPERTY_TYPE_ID + " = t." + PROPERTY_TYPE_ID +
                " JOIN " + PROVINCE_TABLE + " v ON p." + PROPERTY_PROVINCE_ID + " = v." + PROVINCE_ID +
                " JOIN " + CITY_TABLE + " c ON p." + PROPERTY_CITY_ID + " = c." + CITY_ID +
                " WHERE p." + PROPERTY_PROVINCE_ID + " = " + province_id;

        try {
            Statement getProperties = db.getConnection().createStatement();
            ResultSet propertyData = getProperties.executeQuery(query);

            while(propertyData.next()){

                allProperties.add(new PropertyPOJORefined(propertyData.getInt(PROPERTY_ID), propertyData.getString(PROPERTY_NAME), propertyData.getString(PROPERTY_TYPE_NAME), propertyData.getString(PROVINCE_NAME), propertyData.getString(CITY_NAME), propertyData.getString(PROPERTY_STREET),
                        propertyData.getString(PROPERTY_POSTAL_CODE), propertyData.getInt(PROPERTY_AVAILABILITY)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return allProperties;
    }

    /**
     * Retrieves a list of properties based on the city ID. This method performs a join with property type, province, and city.
     *
     * @param city_id the ID of the city to filter properties by
     * @return a list of PropertyPOJORefined objects representing the properties matching the given city ID
     */
    @Override
    public ArrayList<PropertyPOJORefined> getPropertyByCity(int city_id) {

        ArrayList<PropertyPOJORefined> allProperties = new ArrayList<>();

        String query = "SELECT p." + PROPERTY_ID + ", p." + PROPERTY_NAME + ", t." + PROPERTY_TYPE_NAME + ", v." + PROVINCE_NAME + ", c." + CITY_NAME +
                ", p." + PROPERTY_STREET + ", p." + PROPERTY_POSTAL_CODE + ", p." + PROPERTY_AVAILABILITY + " FROM " + PROPERTY_TABLE + " p " +
                "JOIN " + PROPERTY_TYPE_TABLE + " t ON p." + PROPERTY_PROPERTY_TYPE_ID + " = t." + PROPERTY_TYPE_ID +
                " JOIN " + PROVINCE_TABLE + " v ON p." + PROPERTY_PROVINCE_ID + " = v." + PROVINCE_ID +
                " JOIN " + CITY_TABLE + " c ON p." + PROPERTY_CITY_ID + " = c." + CITY_ID +
                " WHERE p." + PROPERTY_CITY_ID + " = " + city_id;

        try{
            Statement getProperties = db.getConnection().createStatement();
            ResultSet propertyData = getProperties.executeQuery(query);

            while(propertyData.next()){

                allProperties.add(new PropertyPOJORefined(propertyData.getInt(PROPERTY_ID), propertyData.getString(PROPERTY_NAME), propertyData.getString(PROPERTY_TYPE_NAME), propertyData.getString(PROVINCE_NAME), propertyData.getString(CITY_NAME), propertyData.getString(PROPERTY_STREET),
                        propertyData.getString(PROPERTY_POSTAL_CODE), propertyData.getInt(PROPERTY_AVAILABILITY)));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return allProperties;
    }

    @Override
    /**
     * Retrieves a list of properties filtered by their availability status.
     *
     * @param availability the availability status to filter properties by.
     * @return an ArrayList of PropertyPOJORefined objects that match the availability status.
     */
    public ArrayList<PropertyPOJORefined> getPropertyByAvailability(int availability) {
        // Initialize an empty list to hold the properties that match the availability status
        ArrayList<PropertyPOJORefined> allProperties = new ArrayList<>();

        // SQL query to select properties based on availability status
        String query = "SELECT p." + PROPERTY_ID + ", p." + PROPERTY_NAME + ", t." + PROPERTY_TYPE_NAME + ", v." + PROVINCE_NAME + ", c." + CITY_NAME +
                ", p." + PROPERTY_STREET + ", p." + PROPERTY_POSTAL_CODE + ", p." + PROPERTY_AVAILABILITY + " FROM " + PROPERTY_TABLE + " p " +
                "JOIN " + PROPERTY_TYPE_TABLE + " t ON p." + PROPERTY_PROPERTY_TYPE_ID + " = t." + PROPERTY_TYPE_ID +
                " JOIN " + PROVINCE_TABLE + " v ON p." + PROPERTY_PROVINCE_ID + " = v." + PROVINCE_ID +
                " JOIN " + CITY_TABLE + " c ON p." + PROPERTY_CITY_ID + " = c." + CITY_ID +
                " WHERE " + PROPERTY_AVAILABILITY + " = " + availability;

        try {
            // Create a statement and execute the query to retrieve properties
            Statement getPropertyByAvailability = db.getConnection().createStatement();
            ResultSet propertyData = getPropertyByAvailability.executeQuery(query);

            while(propertyData.next()){

                allProperties.add(new PropertyPOJORefined(propertyData.getInt(PROPERTY_ID), propertyData.getString(PROPERTY_NAME), propertyData.getString(PROPERTY_TYPE_NAME), propertyData.getString(PROVINCE_NAME), propertyData.getString(CITY_NAME), propertyData.getString(PROPERTY_STREET),
                        propertyData.getString(PROPERTY_POSTAL_CODE), propertyData.getInt(PROPERTY_AVAILABILITY)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return the list of properties found
        return allProperties;
    }

    /**
     * Retrieves a property by its postal code.
     *
     * @param postal_code the postal code of the property to retrieve.
     * @return a PropertyPOJORefined object containing property details, or null if no property is found.
     */
    @Override
    public PropertyPOJORefined getPropertyByPostalCode(String postal_code) {
        // SQL query to select a property by postal code
        String query = "SELECT p." + PROPERTY_ID + ", p." + PROPERTY_NAME + ", t." + PROPERTY_TYPE_NAME + ", v." + PROVINCE_NAME + ", c." + CITY_NAME +
                ", p." + PROPERTY_STREET + ", p." + PROPERTY_POSTAL_CODE + ", p." + PROPERTY_AVAILABILITY + " FROM " + PROPERTY_TABLE + " p " +
                "JOIN " + PROPERTY_TYPE_TABLE + " t ON p." + PROPERTY_PROPERTY_TYPE_ID + " = t." + PROPERTY_TYPE_ID +
                "JOIN " + PROVINCE_TABLE + " v ON p." + PROPERTY_PROVINCE_ID + " = v." + PROVINCE_ID +
                "JOIN " + CITY_TABLE + " c ON p." + PROPERTY_CITY_ID + " = c." + CITY_ID +
                " WHERE " + PROPERTY_POSTAL_CODE + " = " + postal_code;

        try {
            // Create a statement and execute the query to retrieve a property by postal code
            Statement getPropertyByPostalCode = db.getConnection().createStatement();
            ResultSet propertyData = getPropertyByPostalCode.executeQuery(query);

            if(propertyData.next()){

                PropertyPOJORefined  property = new PropertyPOJORefined(propertyData.getInt(PROPERTY_ID), propertyData.getString(PROPERTY_NAME), propertyData.getString(PROPERTY_TYPE_NAME), propertyData.getString(PROVINCE_NAME), propertyData.getString(CITY_NAME), propertyData.getString(PROPERTY_STREET),
                        propertyData.getString(PROPERTY_POSTAL_CODE), propertyData.getInt(PROPERTY_AVAILABILITY));

                return property;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return null if no property is found with the specified postal code
        return null;
    }


    /**
     * Deletes a property by its ID.
     *
     * @param property_Id the ID of the property to delete.
     */
    @Override
    public void deleteProperty(int property_Id) {String query = "DELETE FROM " + PROPERTY_TABLE + " WHERE " + PROPERTY_ID + " = ?";
        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {
            st.setInt(1, property_Id);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Property with ID " + property_Id + " deleted successfully.");
            } else {
                System.out.println("No property found with ID " + property_Id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * Updates an existing property in the database.
     *
     * @param property the PropertyPOJO object containing the updated property details.
     */
    @Override
    public void updateProperty(PropertyPOJO property) {  String query = "UPDATE " + PROPERTY_TABLE + " SET " +
            PROPERTY_NAME + " = ?, " + PROPERTY_PROPERTY_TYPE_ID + " = ?, " + PROPERTY_PROVINCE_ID + " = ?, " + PROPERTY_CITY_ID + " = ?, " + PROPERTY_STREET + " = ?, " + PROPERTY_POSTAL_CODE + " = ?, " + PROPERTY_AVAILABILITY + " = ? " + "WHERE " + PROPERTY_ID + " = ?";
        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {
            st.setString(1, property.getName());
            st.setInt(2, property.getProperty_type_id());
            st.setInt(3, property.getProvince_id());
            st.setInt(4, property.getCity_id());
            st.setString(5, property.getStreet());
            st.setString(6, property.getPostal_code());
            st.setInt(7, property.getAvailability());
            st.setInt(8, property.getProperty_id());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Property with ID " + property.getProperty_id() + " updated successfully.");
            } else {
                System.out.println("No property found with ID " + property.getProperty_id());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    /**
     * Updates the availability status of a property in the database.
     *
     * @param availability the new availability status to set for the property.
     * @param property_id the ID of the property to update.
     */
    public void updateAvailability(int availability, int property_id) {
        // SQL query to update the availability of a property based on its ID
        String query = "UPDATE " + PROPERTY_TABLE + " SET " +
            PROPERTY_AVAILABILITY + " = ? " + "WHERE " + PROPERTY_ID + " = ?";
        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {
            st.setInt(1, availability);
            st.setInt(2, property_id);

            // Execute the update and get the number of rows affected
            int rowsAffected = st.executeUpdate();

            // Check if the update was successful
            if (rowsAffected > 0) {
                System.out.println("Property with ID " + property_id + " updated successfully.");
            } else {
                System.out.println("No property found with ID " + property_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Creates a new property in the database.
     *
     * @param property the PropertyPOJO object containing the new property details.
     */
    @Override
    public void createProperty(PropertyPOJO property) {
        String query = "INSERT INTO " + PROPERTY_TABLE + " (" + PROPERTY_NAME + ", " + PROPERTY_PROPERTY_TYPE_ID + ", " + PROPERTY_PROVINCE_ID + ", " +
                PROPERTY_CITY_ID + ", " + PROPERTY_STREET + ", " + PROPERTY_POSTAL_CODE + ", " + PROPERTY_AVAILABILITY + ") " + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {
            st.setString(1, property.getName());
            st.setInt(2, property.getProperty_type_id());
            st.setInt(3, property.getProvince_id());
            st.setInt(4, property.getCity_id());
            st.setString(5, property.getStreet());
            st.setString(6, property.getPostal_code());
            st.setInt(7, property.getAvailability());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Property created successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
