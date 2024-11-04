package dao;

import java.util.ArrayList;

public interface ClientDataDAO {

        public ArrayList<ClientData> getAllClientDatas();
        public ClientData getId(int user_id );
        public ClientData getFirstName(String f_name);
        public ClientData getLastName(String l_name);
        public ClientData getPhNumber(int clientNumber);
        public ClientData getEmail(String clientEmail);

    }
