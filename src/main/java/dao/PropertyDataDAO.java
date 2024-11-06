package dao;

import java.util.ArrayList;

public interface PropertyDataDAO {
    public ArrayList<PropertyData> getAllPropertyDatas();
    public PropertyData getPropertyID(int property_Id);
    public PropertyData getPropertyName(String name);
    public PropertyData getTypeId(int type_id);
    public PropertyData getProvinceID(int province_id );
    public PropertyData getCityId(int city_id);
    public PropertyData getStreetName(String street_name);
    public PropertyData getAvailability(int availability);
    public PropertyData getPostalCode(String postal_code);



}
