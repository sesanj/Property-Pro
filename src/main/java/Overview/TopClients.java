package Overview;

/**
 * Represents a top client in the system, including details such as name, contact information,
 * total transactions, and total amount spent.
 * This class is used to store and retrieve information about clients who have made significant transactions.
 */
public class TopClients {

    private int id;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String email;

    private int total_transactions;
    private double amount;

    /**
     * Constructs a TopClients object with the specified values.
     *
     * @param id The unique identifier for the client.
     * @param first_name The first name of the client.
     * @param last_name The last name of the client.
     * @param total_transactions The total number of transactions made by the client.
     * @param phone_number The phone number of the client.
     * @param amount The total amount spent by the client.
     * @param email The email address of the client.
     */
    public TopClients(int id, String first_name, String last_name, int total_transactions, String phone_number, double amount, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.total_transactions = total_transactions;
        this.amount = amount;
        this.email = email;
        this.id = id;
    }

    /**
     * Gets the first name of the client.
     *
     * @return The first name of the client.
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Sets the first name of the client.
     *
     * @param first_name The first name of the client.
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Gets the last name of the client.
     *
     * @return The last name of the client.
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Sets the last name of the client.
     *
     * @param last_name The last name of the client.
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Gets the phone number of the client.
     *
     * @return The phone number of the client.
     */
    public String getPhone_number() {
        return phone_number;
    }

    /**
     * Sets the phone number of the client.
     *
     * @param phone_number The phone number of the client.
     */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    /**
     * Gets the total amount spent by the client.
     *
     * @return The total amount spent by the client.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the total amount spent by the client.
     *
     * @param amount The total amount spent by the client.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets the total number of transactions made by the client.
     *
     * @return The total number of transactions.
     */
    public int getTotal_transactions() {
        return total_transactions;
    }

    /**
     * Sets the total number of transactions made by the client.
     *
     * @param total_transactions The total number of transactions.
     */
    public void setTotal_transactions(int total_transactions) {
        this.total_transactions = total_transactions;
    }

    /**
     * Gets the email address of the client.
     *
     * @return The email address of the client.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the client.
     *
     * @param email The email address of the client.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the unique identifier of the client.
     *
     * @return The unique identifier of the client.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the client.
     *
     * @param id The unique identifier of the client.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns a string representation of the client, showing their first and last name.
     *
     * @return A string representation of the client's name.
     */
    @Override
    public String toString() {
        return first_name + " " + last_name;
    }
}
