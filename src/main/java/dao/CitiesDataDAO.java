package dao;

import java.util.ArrayList;

public interface CitiesDataDAO {
    public ArrayList<Cities> getAllCities();
    public Cities getCityId(int CityId);

}
