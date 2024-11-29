package TableQuery;

import Dao.ClientDAO;
import Database.Database;
import com.example.propertypro.Pojo.CityPOJO;
import com.example.propertypro.Pojo.ClientPOJO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;

public class ClientTable implements ClientDAO {
    public Database db = Database.getNewDatabase();
    @Override
    public ArrayList<ClientPOJO> getAllClient() {

        ArrayList<ClientPOJO> clients = new ArrayList<>();

        String query = "SELECT * FROM " + CLIENT_TABLE;

        try{
            Statement getClients = db.getConnection().createStatement();
            ResultSet clientData = getClients.executeQuery(query);

            while(clientData.next()){
                clients.add(new ClientPOJO(clientData.getInt(CLIENT_ID), clientData.getString(CLIENT_FIRST_NAME), clientData.getString(CLIENT_LAST_NAME), clientData.getString(CLIENT_PHONE_NUMBER), clientData.getString(CLIENT_EMAIL)));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return clients;
    }

    @Override
    public ClientPOJO getClientId(int user_id) {

        String query = "SELECT * FROM " + CLIENT_TABLE + " WHERE " + CLIENT_ID + " = " + user_id;

        try{
            Statement getClientById = db.getConnection().createStatement();
            ResultSet clientData = getClientById.executeQuery(query);

            if(clientData.next()){

                ClientPOJO  client = new ClientPOJO(clientData.getInt(CLIENT_ID), clientData.getString(CLIENT_FIRST_NAME), clientData.getString(CLIENT_LAST_NAME), clientData.getString(CLIENT_PHONE_NUMBER), clientData.getString(CLIENT_EMAIL));

                return client;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ClientPOJO getClientByFirstName(String f_name) {

        String query = "SELECT * FROM " + CLIENT_TABLE + " WHERE " + CLIENT_FIRST_NAME + " = " + f_name;

        try{
            Statement getClientByFirstName = db.getConnection().createStatement();
            ResultSet clientData = getClientByFirstName.executeQuery(query);

            if(clientData.next()){

                ClientPOJO  client = new ClientPOJO(clientData.getInt(CLIENT_ID), clientData.getString(CLIENT_FIRST_NAME), clientData.getString(CLIENT_LAST_NAME), clientData.getString(CLIENT_PHONE_NUMBER), clientData.getString(CLIENT_EMAIL));

                return client;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ClientPOJO getClientByLastName(String l_name) {

        String query = "SELECT * FROM " + CLIENT_TABLE + " WHERE " + CLIENT_LAST_NAME + " = " + l_name;

        try{
            Statement getClientByLastName = db.getConnection().createStatement();
            ResultSet clientData = getClientByLastName.executeQuery(query);

            if(clientData.next()){

                ClientPOJO  client = new ClientPOJO(clientData.getInt(CLIENT_ID), clientData.getString(CLIENT_FIRST_NAME), clientData.getString(CLIENT_LAST_NAME), clientData.getString(CLIENT_PHONE_NUMBER), clientData.getString(CLIENT_EMAIL));

                return client;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ClientPOJO getClientByPhoneNumber(String clientNumber) {

        String query = "SELECT * FROM " + CLIENT_TABLE + " WHERE " + CLIENT_PHONE_NUMBER + " = " + clientNumber;

        try{
            Statement getClientByPhone = db.getConnection().createStatement();
            ResultSet clientData = getClientByPhone.executeQuery(query);

            if(clientData.next()){

                ClientPOJO  client = new ClientPOJO(clientData.getInt(CLIENT_ID), clientData.getString(CLIENT_FIRST_NAME), clientData.getString(CLIENT_LAST_NAME), clientData.getString(CLIENT_PHONE_NUMBER), clientData.getString(CLIENT_EMAIL));

                return client;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ClientPOJO getClientByEmail(String clientEmail) {

        String query = "SELECT * FROM " + CLIENT_TABLE + " WHERE " + CLIENT_EMAIL + " = " + clientEmail;

        try{
            Statement getClientByEmail = db.getConnection().createStatement();
            ResultSet clientData = getClientByEmail.executeQuery(query);

            if(clientData.next()){

                ClientPOJO  client = new ClientPOJO(clientData.getInt(CLIENT_ID), clientData.getString(CLIENT_FIRST_NAME), clientData.getString(CLIENT_LAST_NAME), clientData.getString(CLIENT_PHONE_NUMBER), clientData.getString(CLIENT_EMAIL));

                return client;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteClient(int user_id) {
        String query = "DELETE FROM " + CLIENT_TABLE + " WHERE " + CLIENT_ID + " = ?";
        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {
            st.setInt(1, user_id);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Client with ID " + user_id + " deleted successfully.");
            } else {
                System.out.println("No client found with ID " + user_id);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging this
        }

    }

    @Override
    public void updateClient(ClientPOJO client) {String query = "UPDATE " + CLIENT_TABLE + " SET " + CLIENT_FIRST_NAME + " = ?, " + CLIENT_LAST_NAME + " = ?, " + CLIENT_PHONE_NUMBER + " = ?, " + CLIENT_EMAIL + " = ? " +
            "WHERE " + CLIENT_ID + " = ?";
        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {
            st.setString(1, client.getFirst_name());
            st.setString(2, client.getLast_name());
            st.setString(3, client.getPhone_number());
            st.setString(4, client.getEmail());
            st.setInt(5, client.getClient_id());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Client with ID " + client.getClient_id() + " updated successfully.");
            } else {
                System.out.println("No client found with ID " + client.getClient_id());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void createClient(ClientPOJO client) {

        String query = "INSERT INTO " + CLIENT_TABLE + " (" +
            CLIENT_FIRST_NAME + ", " + CLIENT_LAST_NAME + ", " + CLIENT_PHONE_NUMBER + ", " + CLIENT_EMAIL + ") " +
            "VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {
            st.setString(1, client.getFirst_name());
            st.setString(2, client.getLast_name());
            st.setString(3, client.getPhone_number());
            st.setString(4, client.getEmail());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Client created successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging this
        }


    }
}
