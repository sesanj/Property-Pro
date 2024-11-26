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

public class ProvinceTable implements ProvinceDAO {
    Database db = Database.getNewDatabase();
    @Override
    public ArrayList<ProvincePOJO> getAllProvinces() {
        ArrayList<ProvincePOJO> provinces = new ArrayList<>();
        String query = "SELECT * FROM " + PROVINCE_TABLE;

        try{
            Statement getProvince = db.getConnection().createStatement();
            ResultSet ProvinceData = getProvince.executeQuery(query);

            while (ProvinceData.next()) {
                provinces.add(new ProvincePOJO(ProvinceData.getInt(PROVINCE_ID), ProvinceData.getString(PROVINCE_NAME)));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return provinces;
    }

    @Override
    public ProvincePOJO getProvince(int province_id) {

        String query = "SELECT * FROM " + PROVINCE_TABLE + " WHERE " + PROVINCE_ID + "= " + province_id;
        ProvincePOJO province = null;
        try {
            Statement getProvinceById = db.getConnection().createStatement();
            ResultSet ProvinceData = getProvinceById.executeQuery(query);
            if (ProvinceData.next()) {
                province = new ProvincePOJO(ProvinceData.getInt(PROVINCE_ID), ProvinceData.getString(PROVINCE_NAME));

                return province;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ProvincePOJO deleteProvince(int province_id) {
        String query = "SELECT * FROM " + PROVINCE_TABLE + " WHERE " + PROVINCE_ID + "= " + province_id;
        ProvincePOJO province = null;
        try {
            Statement getProvinceById = db.getConnection().createStatement();
            ResultSet provinceData = getProvinceById.executeQuery(query);
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
