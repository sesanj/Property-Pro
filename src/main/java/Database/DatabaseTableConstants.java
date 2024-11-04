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

    public static final String PROPERTY_TYPE = "property_type";
    public static final String PROPERTY_TYPE_ID = "property_type_id";
    public static final String PROPERTY_TYPE_NAME = "property_type";
}
