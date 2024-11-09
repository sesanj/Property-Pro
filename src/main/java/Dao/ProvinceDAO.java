package Dao;

import com.example.propertypro.Pojo.PropertyTypePOJO;
import com.example.propertypro.Pojo.ProvincePOJO;

import java.util.ArrayList;

public interface ProvinceDAO{
    public ArrayList<ProvincePOJO> getAllProvinces();
    public ProvincePOJO getProvince(int province_id );
    public void deleteProvince(int province_id);
    public void updateProvince(ProvincePOJO province);
    public void createProvince(ProvincePOJO province);

}
