package Dao;

import com.example.propertypro.Pojo.ProvincePOJO;
import java.util.ArrayList;

/**
 * Interface for Province Data Access Object (DAO).
 * Provides methods for performing CRUD operations (Create, Read, Update, Delete) on Province objects.
 */
public interface ProvinceDAO {

    /**
     * Retrieves all provinces.
     *
     * @return An ArrayList of ProvincePOJO objects representing all provinces.
     */
    public ArrayList<ProvincePOJO> getAllProvinces();

    /**
     * Retrieves a province by its unique ID.
     *
     * @param province_id The ID of the province to retrieve.
     * @return A ProvincePOJO object representing the province with the given ID.
     */
    public ProvincePOJO getProvince(int province_id);

    /**
     * Deletes a province from the data source.
     *
     * @param province_id The ID of the province to delete.
     * @return A ProvincePOJO object representing the deleted province.
     */
    public ProvincePOJO deleteProvince(int province_id);

    /**
     * Updates an existing province in the data source.
     *
     * @param province A ProvincePOJO object containing the updated province data.
     */
    public void updateProvince(ProvincePOJO province);

    /**
     * Creates a new province in the data source.
     *
     * @param province A ProvincePOJO object representing the province to create.
     */
    public void createProvince(ProvincePOJO province);
}
