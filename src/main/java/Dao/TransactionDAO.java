package Dao;

import com.example.propertypro.Pojo.TransactionPOJO;
import com.example.propertypro.Pojo.TransactionPOJORefined;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Interface for Transaction Data Access Object (DAO).
 * Provides methods for performing CRUD operations (Create, Read, Update, Delete) on Transaction objects.
 */
public interface TransactionDAO {

    /**
     * Retrieves all transactions.
     *
     * @return An ArrayList of TransactionPOJORefined objects representing all transactions.
     */
    public ArrayList<TransactionPOJORefined> getAllTransactions();

    /**
     * Retrieves a transaction by its unique ID.
     *
     * @param TransactionId The ID of the transaction to retrieve.
     * @return A TransactionPOJORefined object representing the transaction with the given ID.
     */
    public TransactionPOJORefined getTransactionById(int TransactionId);

    /**
     * Retrieves all transactions associated with a specific user.
     *
     * @param user_id The ID of the user whose transactions are to be retrieved.
     * @return An ArrayList of TransactionPOJORefined objects representing the transactions of the specified user.
     */
    public ArrayList<TransactionPOJORefined> getTransactionByUser(int user_id);

    /**
     * Retrieves all transactions associated with a specific property.
     *
     * @param property_Id The ID of the property whose transactions are to be retrieved.
     * @return An ArrayList of TransactionPOJORefined objects representing the transactions of the specified property.
     */
    public TransactionPOJORefined getTransactionByProperty(int property_Id);

    /**
     * Retrieves all transactions that occurred within a specified date range.
     *
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return An ArrayList of TransactionPOJORefined objects representing the transactions that occurred within the date range.
     */
    public ArrayList<TransactionPOJORefined> getTransactionByDate(Timestamp startDate, Timestamp endDate);

    /**
     * Deletes a transaction by its unique ID.
     *
     * @param TransactionId The ID of the transaction to delete.
     */
    public void deleteTransaction(int TransactionId);

    /**
     * Updates an existing transaction in the data source.
     *
     * @param transaction A TransactionPOJO object containing the updated transaction data.
     */
    public void updateTransaction(TransactionPOJO transaction);

    /**
     * Creates a new transaction in the data source.
     *
     * @param transaction A TransactionPOJO object representing the transaction to create.
     */
    public void createTransaction(TransactionPOJO transaction);

    /**
     * Retrieves all transactions with basic details (non-refined version).
     *
     * @return An ArrayList of TransactionPOJO objects representing all transactions.
     */
    public ArrayList<TransactionPOJO> getAllTransactions2();
}
