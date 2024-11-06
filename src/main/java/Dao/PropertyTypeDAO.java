package Dao;

import com.example.propertypro.Pojo.PropertyTypePOJO;

import java.util.ArrayList;

public interface PropertyTypeDAO {
    public ArrayList<PropertyTypePOJO> getAllPropertyTypes();
    public PropertyTypePOJO getPropertyType(int property_type_id);

}
