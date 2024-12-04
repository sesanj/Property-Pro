package TableQuery;

import Dao.ClientDAO;
import Database.Database;
import com.example.propertypro.Pojo.ClientPOJO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;

/**
 * The ClientTable class implements the ClientDAO interface and provides methods for interacting with
 * client data in the database. It allows performing CRUD operations such as retrieving all clients,
 * fetching a client by various fields (ID, first name, last name, phone number, email), creating, updating,
 * and deleting clients.
 */
public class ClientTable implements ClientDAO {
    public Database db = Database.getNewDatabase();

    /**
     * Retrieves all clients from the database.
     *
     * @return An ArrayList containing all ClientPOJO objects representing the clients in the database.
     */
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

    /**
     * Retrieves a client by their unique ID.
     *
     * @param user_id The ID of the client to be retrieved.
     * @return A ClientPOJO object representing the client with the specified ID, or null if not found.
     */
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

    /**
     * Retrieves a client by their first name.
     *
     * @param f_name The first name of the client to be retrieved.
     * @return A ClientPOJO object representing the client with the specified first name, or null if not found.
     */
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

    /**
     * Retrieves a client by their last name.
     *
     * @param l_name The last name of the client to be retrieved.
     * @return A ClientPOJO object representing the client with the specified last name, or null if not found.
     */
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

    /**
     * Retrieves a client by their phone number.
     *
     * @param clientNumber The phone number of the client to be retrieved.
     * @return A ClientPOJO object representing the client with the specified phone number, or null if not found.
     */
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

    /**
     * Retrieves a client by their email address.
     *
     * @param clientEmail The email address of the client to be retrieved.
     * @return A ClientPOJO object representing the client with the specified email, or null if not found.
     */
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

    /**
     * Deletes a client from the database based on their unique ID.
     *
     * @param user_id The ID of the client to be deleted.
     */
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
            e.printStackTrace();
        }
    }

    /**
     * Updates the information of an existing client in the database.
     *
     * @param client The ClientPOJO object containing the updated client information.
     */
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

    /**
     * Creates a new client in the database.
     *
     * @param client The ClientPOJO object containing the client information to be inserted.
     */
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
            e.printStackTrace();
        }
    }
}
