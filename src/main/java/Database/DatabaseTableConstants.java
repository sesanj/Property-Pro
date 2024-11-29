package Database;

public class DatabaseTableConstants {

    // Client Table

    public static final String CLIENT_TABLE = "client";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_FIRST_NAME = "first_name";
    public static final String CLIENT_LAST_NAME = "last_name";
    public static final String CLIENT_PHONE_NUMBER = "phone_number";
    public static final String CLIENT_EMAIL = "email";


    // Transaction Table

    public static final String TRANSACTION_TABLE = "transaction";
    public static final String TRANSACTION_ID = "id";
    public static final String TRANSACTION_AMOUNT = "amount";
    public static final String TRANSACTION_CLIENT_ID = "client_id";
    public static final String TRANSACTION_PROPERTY_ID = "property_id";
    public static final String TRANSACTION_TIMESTAMP = "timestamp";


    // Property Table

    public static final String PROPERTY_TABLE = "property";
    public static final String PROPERTY_ID = "property_id";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_PROPERTY_TYPE_ID = "property_type_id";
    public static final String PROPERTY_PROVINCE_ID = "province_id";
    public static final String PROPERTY_CITY_ID = "city_id";
    public static final String PROPERTY_STREET = "street";
    public static final String PROPERTY_AVAILABILITY = "availability";
    public static final String PROPERTY_POSTAL_CODE = "postal_code";


    // Province Table

    public static final String PROVINCE_TABLE = "province";
    public static final String PROVINCE_ID = "province_id";
    public static final String PROVINCE_NAME = "province";


    // Cities Table

    public static final String CITY_TABLE = "city";
    public static final String CITY_ID = "city_id";
    public static final String CITY_NAME = "city";


    // Property Type Table

    public static final String PROPERTY_TYPE_TABLE = "property_type";
    public static final String PROPERTY_TYPE_ID = "property_type_id";
    public static final String PROPERTY_TYPE_NAME = "property_type";




    // CREATE TABLE STATEMENTS

    // CREATE CLIENTS TABLE

    public static final String CREATE_CLIENT_TABLE =
            "CREATE TABLE " + CLIENT_TABLE + " (" +
            CLIENT_ID + " INT NOT NULL AUTO_INCREMENT, " +
            CLIENT_FIRST_NAME + " VARCHAR(50), " +
            CLIENT_LAST_NAME + " VARCHAR(50), " +
            CLIENT_PHONE_NUMBER + " VARCHAR(20), " +
            CLIENT_EMAIL + " VARCHAR(100), " +
            "PRIMARY KEY(" + CLIENT_ID + "));";



    // CREATE PROPERTY TYPE TABLE

    public static final String CREATE_PROPERTY_TYPE_TABLE =
            "CREATE TABLE " + PROPERTY_TYPE_TABLE + " (" +
            PROPERTY_TYPE_ID + " INT NOT NULL AUTO_INCREMENT, " +
            PROPERTY_TYPE_NAME + " VARCHAR(50), " +
            "PRIMARY KEY(" + PROPERTY_TYPE_ID + "));";


    // CREATE PROVINCE TABLE

    public static final String CREATE_PROVINCE_TABLE =
            "CREATE TABLE " + PROVINCE_TABLE + " (" +
            PROVINCE_ID + " INT NOT NULL AUTO_INCREMENT, " +
            PROVINCE_NAME + " VARCHAR(50), " +
            "PRIMARY KEY(" + PROVINCE_ID + "));";


    // CREATE CITY TABLE

    public static final String CREATE_CITY_TABLE =
            "CREATE TABLE " + CITY_TABLE + " (" +
            CITY_ID + " INT NOT NULL AUTO_INCREMENT, " +
            CITY_NAME + " VARCHAR(25), " +
            "PRIMARY KEY (" + CITY_ID + "));";



    // CREATE TRANSACTION TABLE

    public static final String CREATE_TRANSACTION_TABLE =
            "CREATE TABLE " + TRANSACTION_TABLE + " (" +
            TRANSACTION_ID + " INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            TRANSACTION_AMOUNT + " DECIMAL(10, 2), " +
            TRANSACTION_CLIENT_ID + " INT NULL, " +
            TRANSACTION_PROPERTY_ID + " INT NULL, " +
            TRANSACTION_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            "FOREIGN KEY (" + TRANSACTION_CLIENT_ID + ")" +
                    " REFERENCES " + CLIENT_TABLE + "(" + CLIENT_ID + ") ON DELETE SET NULL, " +
            "FOREIGN KEY (" + TRANSACTION_PROPERTY_ID + ")" +
                    " REFERENCES " + PROPERTY_TABLE + "(" + PROPERTY_ID + ") ON DELETE SET NULL);";


    // CREATE PROPERTY TABLE

    public static final String CREATE_PROPERTY_TABLE =
            "CREATE TABLE " + PROPERTY_TABLE + " (" +
            PROPERTY_ID + " INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            PROPERTY_NAME + " VARCHAR(50), " +
            PROPERTY_PROPERTY_TYPE_ID + " INT NULL, " +
            PROPERTY_PROVINCE_ID + " INT NULL, " +
            PROPERTY_CITY_ID + " INT NULL, " +
            PROPERTY_STREET + " VARCHAR(100), " +
            PROPERTY_POSTAL_CODE + " VARCHAR(10), " +
            PROPERTY_AVAILABILITY + " INT(1), " +
            "FOREIGN KEY (" + PROPERTY_PROPERTY_TYPE_ID + ")" +
                    " REFERENCES " + PROPERTY_TYPE_TABLE + "(" + PROPERTY_TYPE_ID + ") ON DELETE SET NULL, " +
            "FOREIGN KEY (" + PROPERTY_CITY_ID + ")" +
                    " REFERENCES " + CITY_TABLE + "(" + CITY_ID + ") ON DELETE SET NULL, " +
            "FOREIGN KEY (" + PROPERTY_PROVINCE_ID + ")" +
                    " REFERENCES " + PROVINCE_TABLE + "(" + PROVINCE_ID + ") ON DELETE SET NULL);";
}
