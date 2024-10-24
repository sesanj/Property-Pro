package Database;

import java.sql.Connection;
import java.sql.DriverManager;

import static Database.DatabaseCredentials.*;

public class Database {

    private static Database instance;
    private Connection connection = null;

    private Database() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.
                    getConnection("jdbc:mysql://localhost/" + DB_NAME +
                                    "?serverTimezone=UTC", DB_USER, DB_PASS);

            System.out.println("Created Connection!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Database getDatabase() {
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }
}
