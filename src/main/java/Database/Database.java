package Database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import static Database.DatabaseCredentials.*;

/**
 * The Database class is a singleton responsible for creating and managing a database connection.
 * It checks for a credentials file, attempts to connect to the MySQL database using those credentials,
 * and optionally creates a credentials file if it does not exist.
 */
public class Database {

    private static Database instance;
    private static boolean createInstance = true;
    private static boolean connectionSuccessful = false;
    private Connection connection = null;

    /**
     * Private constructor to initialize the Database connection.
     * Loads the MySQL JDBC driver and attempts to establish a connection using credentials.
     * If the credentials file does not exist, it creates a new credentials file.
     */
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

            connectionSuccessful = true;

        }catch (Exception e){
            createInstance = false;
            e.printStackTrace();
        }
    }

    /**
     * The getNewDatabase() method returns a new instance of the Database if it has not been created already.
     * If an instance exists and the database connection failed, it returns null.
     *
     * @return an instance of the Database, or null if connection was unsuccessful.
     */
    public static Database getNewDatabase() {
        if(instance == null){
            instance = new Database();
        }
        if (!createInstance){
            instance = null;
        }
        return instance;
    }

    /**
     * The connectionSuccessful() method checks if the database connection was successfully established.
     *
     * @return true if the connection was successful; false otherwise.
     */
    public static boolean connectionSuccessful(){
        return connectionSuccessful;
    }

    public Connection getConnection(){
        return connection;
    }
}
