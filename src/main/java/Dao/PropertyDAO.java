package Dao;

import com.example.propertypro.Pojo.ClientPOJO;
import com.example.propertypro.Pojo.PropertyPOJO;

import java.util.ArrayList;

public interface PropertyDAO {
    public ArrayList<PropertyPOJO> getAllProperty();
    public PropertyPOJO getPropertyByID(int property_Id);
    public PropertyPOJO getPropertyByName(String name);
    public PropertyPOJO getPropertyByPropertyType(int property_type_id);
    public PropertyPOJO getPropertyByProvince(int province_id );
    public PropertyPOJO getPropertyByCity(int city_id);
    public PropertyPOJO getPropertyByAvailability(int availability);
    public PropertyPOJO getPropertyByPostalCode(String postal_code);
    public void deleteProperty(int property_Id );
    public void updateProperty(PropertyPOJO property);
    public void createProperty(PropertyPOJO property);



}
