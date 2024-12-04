package TableQuery;

import Dao.ProvinceDAO;
import Database.Database;
import com.example.propertypro.Pojo.ProvincePOJO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;

/**
 * The ProvinceTable class implements the ProvinceDAO interface.
 * It provides methods to interact with the "Province" table in the database.
 */
public class ProvinceTable implements ProvinceDAO {
    // Database connection object
    Database db = Database.getNewDatabase();

    /**
     * Retrieves all provinces from the database.
     *
     * @return an ArrayList of ProvincePOJO objects representing all provinces.
     */
    @Override
    public ArrayList<ProvincePOJO> getAllProvinces() {
        ArrayList<ProvincePOJO> provinces = new ArrayList<>();
        String query = "SELECT * FROM " + PROVINCE_TABLE;

        try {
            Statement getProvince = db.getConnection().createStatement();
            ResultSet ProvinceData = getProvince.executeQuery(query);

            // Iterate through the result set and add each province to the list
            while (ProvinceData.next()) {
                provinces.add(new ProvincePOJO(ProvinceData.getInt(PROVINCE_ID), ProvinceData.getString(PROVINCE_NAME)));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return provinces;
    }

    /**
     * Retrieves a province based on its ID.
     *
     * @param province_id the ID of the province to retrieve.
     * @return a ProvincePOJO object representing the province, or null if not found.
     */
    @Override
    public ProvincePOJO getProvince(int province_id) {
        String query = "SELECT * FROM " + PROVINCE_TABLE + " WHERE " + PROVINCE_ID + "= " + province_id;
        ProvincePOJO province = null;

        try {
            Statement getProvinceById = db.getConnection().createStatement();
            ResultSet ProvinceData = getProvinceById.executeQuery(query);

            // If a province is found, create and return the ProvincePOJO object
            if (ProvinceData.next()) {
                province = new ProvincePOJO(ProvinceData.getInt(PROVINCE_ID), ProvinceData.getString(PROVINCE_NAME));
                return province;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Deletes a province from the database based on its ID.
     *
     * @param province_id the ID of the province to delete.
     * @return the deleted ProvincePOJO object if found, or null if not found.
     */
    @Override
    public ProvincePOJO deleteProvince(int province_id) {
        String query = "SELECT * FROM " + PROVINCE_TABLE + " WHERE " + PROVINCE_ID + "= " + province_id;
        ProvincePOJO province = null;

        try {
            Statement getProvinceById = db.getConnection().createStatement();
            ResultSet provinceData = getProvinceById.executeQuery(query);

            // If a province is found, delete it from the database
            if (provinceData.next()) {
                province = new ProvincePOJO(provinceData.getInt(PROVINCE_ID), provinceData.getString(PROVINCE_NAME));

                String deleteQuery = "DELETE FROM " + PROVINCE_TABLE + " WHERE " + PROVINCE_ID + "= " + province_id;
                getProvinceById.executeUpdate(deleteQuery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting province", e);
        }
        return province;
    }

    /**
     * Updates a province's name in the database.
     *
     * @param province the ProvincePOJO object containing the updated province information.
     */
    @Override
    public void updateProvince(ProvincePOJO province) {
        String query = "UPDATE " + PROVINCE_TABLE + " SET " + PROVINCE_NAME + " = ? WHERE " + PROVINCE_ID + " = ?";

        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {
            st.setString(1, province.getProvince());
            st.setInt(2, province.getProvince_id());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating province", e);
        }
    }

    /**
     * Creates a new province in the database.
     *
     * @param province the ProvincePOJO object containing the new province information.
     */
    @Override
    public void createProvince(ProvincePOJO province) {
        String query = "INSERT INTO " + PROVINCE_TABLE + " (" + PROVINCE_NAME + ") VALUES (?)";

        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {
            st.setString(1, province.getProvince());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating province", e);
        }
    }
}
