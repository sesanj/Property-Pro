package Dao;

import com.example.propertypro.Pojo.ClientPOJO;
import com.example.propertypro.Pojo.PropertyPOJO;
import com.example.propertypro.Pojo.PropertyPOJORefined;

import java.util.ArrayList;

public interface PropertyDAO {

    public ArrayList<PropertyPOJORefined> getAllProperty();
    public ArrayList<PropertyPOJO> getAllPropertyRaw();
    public PropertyPOJORefined getPropertyByID(int property_Id);
    public PropertyPOJORefined getPropertyByName(String name);
    public PropertyPOJORefined getPropertyByPropertyType(int property_type_id);
    public PropertyPOJORefined getPropertyByProvince(int province_id );
    public PropertyPOJORefined getPropertyByCity(int city_id);
    public PropertyPOJORefined getPropertyByAvailability(int availability);
    public PropertyPOJORefined getPropertyByPostalCode(String postal_code);

    public void deleteProperty(int property_Id );
    public void updateProperty(PropertyPOJO property);
    public void createProperty(PropertyPOJO property);

}
