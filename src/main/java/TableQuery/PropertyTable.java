package TableQuery;

import Dao.PropertyDAO;
import Database.Database;
import com.example.propertypro.Pojo.ClientPOJO;
import com.example.propertypro.Pojo.PropertyPOJO;
import com.example.propertypro.Pojo.PropertyPOJORefined;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;

public class PropertyTable implements PropertyDAO {

    public Database db = Database.getNewDatabase();

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

    @Override
    public PropertyPOJORefined getPropertyByName(String name) {

        String query = "SELECT p." + PROPERTY_ID + ", p." + PROPERTY_NAME + ", t." + PROPERTY_TYPE_NAME + ", v." + PROVINCE_NAME + ", c." + CITY_NAME +
                ", p." + PROPERTY_STREET + ", p." + PROPERTY_POSTAL_CODE + ", p." + PROPERTY_AVAILABILITY + " FROM " + PROPERTY_TABLE + " p " +
                "JOIN " + PROPERTY_TYPE_TABLE + " t ON p." + PROPERTY_PROPERTY_TYPE_ID + " = t." + PROPERTY_TYPE_ID +
                " JOIN " + PROVINCE_TABLE + " v ON p." + PROPERTY_PROVINCE_ID + " = v." + PROVINCE_ID +
                " JOIN " + CITY_TABLE + " c ON p." + PROPERTY_CITY_ID + " = c." + CITY_ID +
                " WHERE " + PROPERTY_NAME + " = " + name;

        try{
            Statement getPropertyByName = db.getConnection().createStatement();
            ResultSet propertyData = getPropertyByName.executeQuery(query);

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

    @Override
    public PropertyPOJORefined getPropertyByPropertyType(int property_type_id) {

        String query = "SELECT p." + PROPERTY_ID + ", p." + PROPERTY_NAME + ", t." + PROPERTY_TYPE_NAME + ", v." + PROVINCE_NAME + ", c." + CITY_NAME +
                ", p." + PROPERTY_STREET + ", p." + PROPERTY_POSTAL_CODE + ", p." + PROPERTY_AVAILABILITY + " FROM " + PROPERTY_TABLE + " p " +
                "JOIN " + PROPERTY_TYPE_TABLE + " t ON p." + PROPERTY_PROPERTY_TYPE_ID + " = t." + PROPERTY_TYPE_ID +
                " JOIN " + PROVINCE_TABLE + " v ON p." + PROPERTY_PROVINCE_ID + " = v." + PROVINCE_ID +
                " JOIN " + CITY_TABLE + " c ON p." + PROPERTY_CITY_ID + " = c." + CITY_ID +
                " WHERE " + PROPERTY_PROPERTY_TYPE_ID + " = " + property_type_id;

        try{
            Statement getPropertyByType = db.getConnection().createStatement();
            ResultSet propertyData = getPropertyByType.executeQuery(query);

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

    @Override
    public PropertyPOJORefined getPropertyByProvince(int province_id) {

        String query = "SELECT p." + PROPERTY_ID + ", p." + PROPERTY_NAME + ", t." + PROPERTY_TYPE_NAME + ", v." + PROVINCE_NAME + ", c." + CITY_NAME +
                ", p." + PROPERTY_STREET + ", p." + PROPERTY_POSTAL_CODE + ", p." + PROPERTY_AVAILABILITY + " FROM " + PROPERTY_TABLE + " p " +
                "JOIN " + PROPERTY_TYPE_TABLE + " t ON p." + PROPERTY_PROPERTY_TYPE_ID + " = t." + PROPERTY_TYPE_ID +
                " JOIN " + PROVINCE_TABLE + " v ON p." + PROPERTY_PROVINCE_ID + " = v." + PROVINCE_ID +
                " JOIN " + CITY_TABLE + " c ON p." + PROPERTY_CITY_ID + " = c." + CITY_ID +
                " WHERE " + PROPERTY_PROVINCE_ID + " = " + province_id;

        try{
            Statement getPropertyByProvince = db.getConnection().createStatement();
            ResultSet propertyData = getPropertyByProvince.executeQuery(query);

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

    @Override
    public PropertyPOJORefined getPropertyByCity(int city_id) {

        String query = "SELECT p." + PROPERTY_ID + ", p." + PROPERTY_NAME + ", t." + PROPERTY_TYPE_NAME + ", v." + PROVINCE_NAME + ", c." + CITY_NAME +
                ", p." + PROPERTY_STREET + ", p." + PROPERTY_POSTAL_CODE + ", p." + PROPERTY_AVAILABILITY + " FROM " + PROPERTY_TABLE + " p " +
                "JOIN " + PROPERTY_TYPE_TABLE + " t ON p." + PROPERTY_PROPERTY_TYPE_ID + " = t." + PROPERTY_TYPE_ID +
                " JOIN " + PROVINCE_TABLE + " v ON p." + PROPERTY_PROVINCE_ID + " = v." + PROVINCE_ID +
                " JOIN " + CITY_TABLE + " c ON p." + PROPERTY_CITY_ID + " = c." + CITY_ID +
                " WHERE " + PROPERTY_CITY_ID + " = " + city_id;

        try{
            Statement getPropertyByCity = db.getConnection().createStatement();
            ResultSet propertyData = getPropertyByCity.executeQuery(query);

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

    @Override
    public PropertyPOJORefined getPropertyByAvailability(int availability) {

        String query = "SELECT p." + PROPERTY_ID + ", p." + PROPERTY_NAME + ", t." + PROPERTY_TYPE_NAME + ", v." + PROVINCE_NAME + ", c." + CITY_NAME +
                ", p." + PROPERTY_STREET + ", p." + PROPERTY_POSTAL_CODE + ", p." + PROPERTY_AVAILABILITY + " FROM " + PROPERTY_TABLE + " p " +
                "JOIN " + PROPERTY_TYPE_TABLE + " t ON p." + PROPERTY_PROPERTY_TYPE_ID + " = t." + PROPERTY_TYPE_ID +
                " JOIN " + PROVINCE_TABLE + " v ON p." + PROPERTY_PROVINCE_ID + " = v." + PROVINCE_ID +
                " JOIN " + CITY_TABLE + " c ON p." + PROPERTY_CITY_ID + " = c." + CITY_ID +
                " WHERE " + PROPERTY_AVAILABILITY + " = " + availability;

        try{
            Statement getPropertyByAvailability = db.getConnection().createStatement();
            ResultSet propertyData = getPropertyByAvailability.executeQuery(query);

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

    @Override
    public PropertyPOJORefined getPropertyByPostalCode(String postal_code) {

        String query = "SELECT p." + PROPERTY_ID + ", p." + PROPERTY_NAME + ", t." + PROPERTY_TYPE_NAME + ", v." + PROVINCE_NAME + ", c." + CITY_NAME +
                ", p." + PROPERTY_STREET + ", p." + PROPERTY_POSTAL_CODE + ", p." + PROPERTY_AVAILABILITY + " FROM " + PROPERTY_TABLE + " p " +
                "JOIN " + PROPERTY_TYPE_TABLE + " t ON p." + PROPERTY_PROPERTY_TYPE_ID + " = t." + PROPERTY_TYPE_ID +
                "JOIN " + PROVINCE_TABLE + " v ON p." + PROPERTY_PROVINCE_ID + " = v." + PROVINCE_ID +
                "JOIN " + CITY_TABLE + " c ON p." + PROPERTY_CITY_ID + " = c." + CITY_ID +
                " WHERE " + PROPERTY_POSTAL_CODE + " = " + postal_code;

        try{
            Statement getPropertyByPostalCode = db.getConnection().createStatement();
            ResultSet propertyData = getPropertyByPostalCode.executeQuery(query);

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

    @Override
    public void deleteProperty(int property_Id) {

    }

    @Override
    public void updateProperty(PropertyPOJO property) {

    }

    @Override
    public void createProperty(PropertyPOJO property) {

    }
}
