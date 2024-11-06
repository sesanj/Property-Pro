package Dao;

import java.util.ArrayList;

public interface PropertyTypeDAO {
    public ArrayList<PropertyType> getAllPropertyTypes();
    public PropertyType getTypeId(int type_id);
    public PropertyType getTypeName(int type_name);

}
