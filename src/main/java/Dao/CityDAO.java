package Dao;

import com.example.propertypro.Pojo.CityPOJO;

import java.util.ArrayList;

public interface CityDAO {
    public ArrayList<CityPOJO> getAllCities();
    public CityPOJO getCityByID(int CityId);

}
