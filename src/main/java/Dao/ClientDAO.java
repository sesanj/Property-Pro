package Dao;

import com.example.propertypro.Pojo.ClientPOJO;
import java.util.ArrayList;

/**
 * Interface for Client Data Access Object (DAO).
 * Provides methods for performing CRUD operations (Create, Read, Update, Delete) on Client objects.
 */
public interface ClientDAO {

        /**
         * Retrieves all clients from the data source.
         *
         * @return An ArrayList of ClientPOJO objects representing all clients.
         */
        public ArrayList<ClientPOJO> getAllClient();

        /**
         * Retrieves a client by their unique ID.
         *
         * @param user_id The ID of the client to retrieve.
         * @return A ClientPOJO object representing the client with the given ID.
         */
        public ClientPOJO getClientId(int user_id);

        /**
         * Retrieves a client by their first name.
         *
         * @param f_name The first name of the client to retrieve.
         * @return A ClientPOJO object representing the client with the given first name.
         */
        public ClientPOJO getClientByFirstName(String f_name);

        /**
         * Retrieves a client by their last name.
         *
         * @param l_name The last name of the client to retrieve.
         * @return A ClientPOJO object representing the client with the given last name.
         */
        public ClientPOJO getClientByLastName(String l_name);

        /**
         * Retrieves a client by their phone number.
         *
         * @param clientNumber The phone number of the client to retrieve.
         * @return A ClientPOJO object representing the client with the given phone number.
         */
        public ClientPOJO getClientByPhoneNumber(String clientNumber);

        /**
         * Retrieves a client by their email.
         *
         * @param clientEmail The email of the client to retrieve.
         * @return A ClientPOJO object representing the client with the given email.
         */
        public ClientPOJO getClientByEmail(String clientEmail);

        /**
         * Deletes a client from the data source.
         *
         * @param user_id The ID of the client to delete.
         */
        public void deleteClient(int user_id);

        /**
         * Updates an existing client in the data source.
         *
         * @param client A ClientPOJO object containing the updated client data.
         */
        public void updateClient(ClientPOJO client);

        /**
         * Creates a new client in the data source.
         *
         * @param client A ClientPOJO object representing the client to create.
         */
        public void createClient(ClientPOJO client);
}
