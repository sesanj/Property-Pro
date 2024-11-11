package TableQuery;

import Dao.TransactionDAO;
import Database.Database;
import com.example.propertypro.Pojo.TransactionPOJO;
import com.example.propertypro.Pojo.TransactionPOJORefined;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;

public class TransactionTable implements TransactionDAO {
    Database db = Database.getNewDatabase();
    @Override
    public ArrayList<TransactionPOJORefined> getAllTransactions() {

        ArrayList<TransactionPOJORefined> transactions = new ArrayList<>();

        String query = "SELECT t." + TRANSACTION_ID + ", t." + TRANSACTION_AMOUNT + ", c." + CLIENT_FIRST_NAME + ", c." + CLIENT_LAST_NAME + ", p." + PROPERTY_NAME +
                ", t." + TRANSACTION_TIMESTAMP +
                " FROM " + TRANSACTION_TABLE + " t " +
                "JOIN " + CLIENT_TABLE + " c ON t." + TRANSACTION_CLIENT_ID + " = c." + CLIENT_ID +
                " JOIN " + PROPERTY_TABLE + " p ON t." + TRANSACTION_PROPERTY_ID + " = p." + PROPERTY_ID +
                " ORDER BY " + TRANSACTION_ID;

        try{
            Statement getTransaction = db.getConnection().createStatement();
            ResultSet TransactionData = getTransaction.executeQuery(query);

            while (TransactionData.next()) {

                transactions.add(new TransactionPOJORefined(TransactionData.getInt(TRANSACTION_ID),TransactionData.getString(CLIENT_FIRST_NAME) + " " + TransactionData.getString(CLIENT_LAST_NAME),TransactionData.getString(PROPERTY_NAME), TransactionData.getDouble(TRANSACTION_AMOUNT), TransactionData.getTimestamp(TRANSACTION_TIMESTAMP)));

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }

    @Override
    public TransactionPOJORefined getTransactionById(int TransactionId) {

        String query = "SELECT t." + TRANSACTION_ID + ", t." + TRANSACTION_AMOUNT + ", c." + CLIENT_FIRST_NAME + ", c." + CLIENT_LAST_NAME + ", p." + PROPERTY_NAME +
                ", t." + TRANSACTION_TIMESTAMP +
                " FROM " + TRANSACTION_TABLE + " t " +
                "JOIN " + CLIENT_TABLE + " c ON t." + TRANSACTION_CLIENT_ID + " = c." + CLIENT_ID +
                " JOIN " + PROPERTY_TABLE + " p ON t." + TRANSACTION_PROPERTY_ID + " = p." + PROPERTY_ID +
                " WHERE " + TRANSACTION_ID + " = " + TransactionId +
                " ORDER BY " + TRANSACTION_ID;

        try{
            Statement getTransactionById = db.getConnection().createStatement();
            ResultSet TransactionData = getTransactionById.executeQuery(query);

            if (TransactionData.next()) {

                TransactionPOJORefined transaction = new TransactionPOJORefined(TransactionData.getInt(TRANSACTION_ID),TransactionData.getString(CLIENT_FIRST_NAME) + " " + TransactionData.getString(CLIENT_LAST_NAME),TransactionData.getString(PROPERTY_NAME), TransactionData.getDouble(TRANSACTION_AMOUNT), TransactionData.getTimestamp(TRANSACTION_TIMESTAMP));

                return transaction;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public TransactionPOJORefined getTransactionByUser(int user_id) {

        String query = "SELECT t." + TRANSACTION_ID + ", t." + TRANSACTION_AMOUNT + ", c." + CLIENT_FIRST_NAME + ", c." + CLIENT_LAST_NAME + ", p." + PROPERTY_NAME +
                ", t." + TRANSACTION_TIMESTAMP +
                " FROM " + TRANSACTION_TABLE + " t " +
                "JOIN " + CLIENT_TABLE + " c ON t." + TRANSACTION_CLIENT_ID + " = c." + CLIENT_ID +
                " JOIN " + PROPERTY_TABLE + " p ON t." + TRANSACTION_PROPERTY_ID + " = p." + PROPERTY_ID +
                " WHERE " + TRANSACTION_CLIENT_ID + " = " + user_id +
                " ORDER BY " + TRANSACTION_ID;

        try {
            Statement getTransactionByUser = db.getConnection().createStatement();
            ResultSet TransactionData = getTransactionByUser.executeQuery(query);

            if (TransactionData.next()) {

                TransactionPOJORefined transaction = new TransactionPOJORefined(TransactionData.getInt(TRANSACTION_ID),TransactionData.getString(CLIENT_FIRST_NAME) + " " + TransactionData.getString(CLIENT_LAST_NAME),TransactionData.getString(PROPERTY_NAME), TransactionData.getDouble(TRANSACTION_AMOUNT), TransactionData.getTimestamp(TRANSACTION_TIMESTAMP));

                return transaction;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public TransactionPOJORefined getTransactionByProperty(int property_Id) {

        String query = "SELECT t." + TRANSACTION_ID + ", t." + TRANSACTION_AMOUNT + ", c." + CLIENT_FIRST_NAME + ", c." + CLIENT_LAST_NAME + ", p." + PROPERTY_NAME +
                ", t." + TRANSACTION_TIMESTAMP +
                " FROM " + TRANSACTION_TABLE + " t " +
                "JOIN " + CLIENT_TABLE + " c ON t." + TRANSACTION_CLIENT_ID + " = c." + CLIENT_ID +
                " JOIN " + PROPERTY_TABLE + " p ON t." + TRANSACTION_PROPERTY_ID + " = p." + PROPERTY_ID +
                " WHERE " + TRANSACTION_PROPERTY_ID + " = " + property_Id +
                " ORDER BY " + TRANSACTION_ID;

        try {
            Statement getTransactionByProperty = db.getConnection().createStatement();
            ResultSet TransactionData = getTransactionByProperty.executeQuery(query);

            if (TransactionData.next()) {

                TransactionPOJORefined transaction = new TransactionPOJORefined(TransactionData.getInt(TRANSACTION_ID),TransactionData.getString(CLIENT_FIRST_NAME) + " " + TransactionData.getString(CLIENT_LAST_NAME),TransactionData.getString(PROPERTY_NAME), TransactionData.getDouble(TRANSACTION_AMOUNT), TransactionData.getTimestamp(TRANSACTION_TIMESTAMP));

                return transaction;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public TransactionPOJORefined getTransactionByDate(Timestamp date) {
        String query = "SELECT t." + TRANSACTION_ID + ", t." + TRANSACTION_AMOUNT + ", c." + CLIENT_FIRST_NAME + ", c." + CLIENT_LAST_NAME + ", p." + PROPERTY_NAME +
                ", t." + TRANSACTION_TIMESTAMP +
                " FROM " + TRANSACTION_TABLE + " t " +
                "JOIN " + CLIENT_TABLE + " c ON t." + TRANSACTION_CLIENT_ID + " = c." + CLIENT_ID +
                " JOIN " + PROPERTY_TABLE + " p ON t." + TRANSACTION_PROPERTY_ID + " = p." + PROPERTY_ID +
                " WHERE " + TRANSACTION_TIMESTAMP + " = " + date +
                " ORDER BY " + TRANSACTION_ID;

        try {
            Statement getTransactionByDate = db.getConnection().createStatement();
            ResultSet TransactionData = getTransactionByDate.executeQuery(query);

            if (TransactionData.next()) {

                TransactionPOJORefined transaction = new TransactionPOJORefined(TransactionData.getInt(TRANSACTION_ID),TransactionData.getString(CLIENT_FIRST_NAME) + " " + TransactionData.getString(CLIENT_LAST_NAME),TransactionData.getString(PROPERTY_NAME), TransactionData.getDouble(TRANSACTION_AMOUNT), TransactionData.getTimestamp(TRANSACTION_TIMESTAMP));

                return transaction;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteTransaction(int TransactionId) {

    }

    @Override
    public void updateTransaction(TransactionPOJO transaction) {

    }

    @Override
    public void createTransaction(TransactionPOJO transaction) {

    }
}