package Dao;

import com.example.propertypro.Pojo.ProvincePOJO;

import java.util.ArrayList;

public interface ProvinceDAO{
    public ArrayList<ProvincePOJO> getAllProvinces();
    public ProvincePOJO getProvince(int province_id );

}
