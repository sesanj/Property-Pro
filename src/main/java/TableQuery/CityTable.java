package TableQuery;

import Dao.CityDAO;
import Database.Database;
import com.example.propertypro.Pojo.CityPOJO;

import java.sql.ResultSet;
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
    public void deleteCity(int cityId) {

    }

    @Override
    public void updateCity(CityPOJO city) {

    }

    @Override
    public void createCity(CityPOJO city) {

    }
}
