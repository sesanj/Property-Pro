package Dao;

import com.example.propertypro.Pojo.CityPOJO;
import com.example.propertypro.Pojo.ClientPOJO;

import java.util.ArrayList;

public interface ClientDAO {

        public ArrayList<ClientPOJO> getAllClient();
        public ClientPOJO getClientId(int user_id );
        public ClientPOJO getClientByFirstName(String f_name);
        public ClientPOJO getClientByLastName(String l_name);
        public ClientPOJO getClientByPhoneNumber(String clientNumber);
        public ClientPOJO getClientByEmail(String clientEmail);
        public void deleteClient(int user_id);
        public void updateClient(ClientPOJO client);
        public void createClient(ClientPOJO client);

    }
