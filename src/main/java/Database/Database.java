package Database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import static Database.DatabaseCredentials.*;

public class Database {

    private static Database instance;
    private static boolean crateInstance = true;
    private Connection connection = null;

    private Database() {

        File credentials = new File("credentials.txt");

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.
                    getConnection("jdbc:mysql://localhost/" + DB_NAME + "?serverTimezone=UTC", DB_USER, DB_PASS);

            System.out.println("Created Connection!");

            if(!credentials.exists()){
                new DatabaseCredentials().createCredentials(DB_NAME, DB_USER, DB_PASS);
            }

        }catch (Exception e){

            crateInstance = false;
           e.printStackTrace();
        }
    }

    public static Database getDatabase() {

        if(instance == null){
            instance = new Database();
        }
        if (!crateInstance){
            instance = null;
        }

        return instance;
    }
}
