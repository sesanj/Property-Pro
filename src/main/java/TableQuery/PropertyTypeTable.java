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

public class PropertyTypeTable implements PropertyTypeDAO {

    Database db = Database.getNewDatabase();
    @Override
    public ArrayList<PropertyTypePOJO> getAllPropertyTypes() {
        ArrayList<PropertyTypePOJO> propertyTypes = new ArrayList<>();
        String query = "SELECT * FROM " + PROPERTY_TYPE_TABLE;

        try{
            Statement getPropertyType = db.getConnection().createStatement();
            ResultSet PropertyTypeData = getPropertyType.executeQuery(query);

            while (PropertyTypeData.next()) {
                propertyTypes.add(new PropertyTypePOJO(PropertyTypeData.getInt(PROPERTY_TYPE_ID), PropertyTypeData.getString(PROPERTY_TYPE_NAME)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return propertyTypes;
    }

    @Override
    public PropertyTypePOJO getPropertyType(int property_type_id) {
        String query = "SELECT * FROM " + PROPERTY_TYPE_TABLE + " WHERE " + PROPERTY_TYPE_ID + "= " + property_type_id;
        PropertyTypePOJO propertyType = null;

        try{
            Statement getPropertyTypeById = db.getConnection().createStatement();
            ResultSet PropertyTypeData = getPropertyTypeById.executeQuery(query);
            if (PropertyTypeData.next()) {
                propertyType = new PropertyTypePOJO(PropertyTypeData.getInt(PROPERTY_TYPE_ID), PropertyTypeData.getString(PROPERTY_TYPE_NAME));
                return propertyType;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deletePropertyType(int property_type_id) {  String query = "DELETE FROM " + PROPERTY_TYPE_TABLE + " WHERE " + PROPERTY_TYPE_ID + " = ?";

        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {
            st.setInt(1, property_type_id);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Property type with ID " + property_type_id + " was deleted.");
            } else {
                System.out.println("No property type found with ID " + property_type_id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting property type", e);
        }


    }

    @Override
    public void updatePropertyType(PropertyTypePOJO propertyType) {
        String query = "UPDATE " + PROPERTY_TYPE_TABLE + " SET " + PROPERTY_TYPE_NAME + " = ? WHERE " + PROPERTY_TYPE_ID + " = ?";

        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {
            st.setString(1, propertyType.getProperty_type());
            st.setInt(2, propertyType.getPropertyType_id());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Property type with ID " + propertyType.getPropertyType_id() + " was updated.");
            } else {
                System.out.println("No property type found with ID " + propertyType.getPropertyType_id());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating property type", e);
        }

    }

    @Override
    public void createPropertyType(PropertyTypePOJO propertyType) {  String query = "INSERT INTO " + PROPERTY_TYPE_TABLE + " (" + PROPERTY_TYPE_NAME + ") VALUES (?)";

        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {
            st.setString(1, propertyType.getProperty_type());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Property type created: " + propertyType.getProperty_type());
            } else {
                System.out.println("Failed to create property type.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating property type", e);
        }


    }
}
