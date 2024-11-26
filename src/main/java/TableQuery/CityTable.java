package TableQuery;

import Dao.CityDAO;
import Database.Database;
import com.example.propertypro.Pojo.CityPOJO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;

public class CityTable implements CityDAO {

    Database db = Database.getNewDatabase();
    @Override
    public ArrayList<CityPOJO> getAllCities() {
        ArrayList<CityPOJO> cities = new ArrayList<>();

        String query = "SELECT * FROM " + CITY_TABLE;

        try{
            Statement getCities = db.getConnection().createStatement();
            ResultSet cityData = getCities.executeQuery(query);

            while(cityData.next()){
                cities.add(new CityPOJO(cityData.getInt(CITY_ID), cityData.getString(CITY_NAME)));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return cities;
    }

    @Override
    public CityPOJO getCityByID(int CityId) {

        String query = "SELECT * FROM " + CITY_TABLE + " WHERE " + CITY_ID + " = " + CityId;

        try{
            Statement getCityById = db.getConnection().createStatement();
            ResultSet data = getCityById.executeQuery(query);

            if(data.next()){

               CityPOJO  city = new CityPOJO(data.getInt(CITY_ID), data.getString(CITY_NAME));

               return city;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void deleteCity(int cityId) { String query = "DELETE FROM " + CITY_TABLE + " WHERE " + CITY_ID + " = ?";
        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {
            st.setInt(1, cityId);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("City with ID " + cityId + " deleted successfully.");
            } else {
                System.out.println("No city found with ID " + cityId);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging this instead
        }


    }

    @Override
    public void updateCity(CityPOJO city) {
        String query = "UPDATE " + CITY_TABLE + " SET " + CITY_NAME + " = ? WHERE " + CITY_ID + " = ?";
        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {
            st.setString(1, city.getCity());
            st.setInt(2, city.getCity_id());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("City with ID " + city.getCity_id() + " updated successfully.");
            } else {
                System.out.println("No city found with ID " + city.getCity_id());
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging this instead
        }

    }

    @Override
    public void createCity(CityPOJO city) {
        String query = "INSERT INTO " + CITY_TABLE + " (" + CITY_NAME + ") VALUES (?)";
        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {
            st.setString(1, city.getCity());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("City created successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging this instead
        }

    }
}
