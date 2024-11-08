package Dao;

import com.example.propertypro.Pojo.ClientPOJO;
import com.example.propertypro.Pojo.PropertyTypePOJO;

import java.util.ArrayList;

public interface PropertyTypeDAO {
    public ArrayList<PropertyTypePOJO> getAllPropertyTypes();
    public PropertyTypePOJO getPropertyType(int property_type_id);
    public void deletePropertyType(int property_type_id);
    public void updatePropertyType(PropertyTypePOJO propertyType);
    public void createPropertyType(PropertyTypePOJO propertyType);

}
