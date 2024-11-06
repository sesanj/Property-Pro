package Dao;

import com.example.propertypro.Pojo.ClientPOJO;

import java.util.ArrayList;

public interface ClientDAO {

        public ArrayList<ClientPOJO> getAllClient();
        public ClientPOJO getClientId(int user_id );
        public ClientPOJO getClientByFirstName(String f_name);
        public ClientPOJO getClientByLastName(String l_name);
        public ClientPOJO getClientByPhoneNumber(int clientNumber);
        public ClientPOJO getClientByEmail(String clientEmail);

    }
