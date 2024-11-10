package TableQuery;

import Dao.PropertyTypeDAO;
import Database.Database;
import com.example.propertypro.Pojo.PropertyTypePOJO;

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
    public void deletePropertyType(int property_type_id) {

    }

    @Override
    public void updatePropertyType(PropertyTypePOJO propertyType) {

    }

    @Override
    public void createPropertyType(PropertyTypePOJO propertyType) {

    }
}
