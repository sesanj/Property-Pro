package TableQuery;

import Dao.TransactionDAO;
import Database.Database;
import com.example.propertypro.Pojo.TransactionPOJO;
import com.example.propertypro.Pojo.TransactionPOJORefined;

import java.sql.*;
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
                " ORDER BY " + TRANSACTION_TIMESTAMP + " DESC";

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
    public ArrayList<TransactionPOJORefined> getTransactionByUser(int user_id) {

        ArrayList<TransactionPOJORefined> allTransactions = new ArrayList<>();

        String query = "SELECT t." + TRANSACTION_ID + ", t." + TRANSACTION_AMOUNT + ", c." + CLIENT_FIRST_NAME + ", c." + CLIENT_LAST_NAME + ", p." + PROPERTY_NAME +
                ", t." + TRANSACTION_TIMESTAMP +
                " FROM " + TRANSACTION_TABLE + " t " +
                "JOIN " + CLIENT_TABLE + " c ON t." + TRANSACTION_CLIENT_ID + " = c." + CLIENT_ID +
                " JOIN " + PROPERTY_TABLE + " p ON t." + TRANSACTION_PROPERTY_ID + " = p." + PROPERTY_ID +
                " WHERE t." + TRANSACTION_CLIENT_ID + " = " + user_id +
                " ORDER BY " + TRANSACTION_TIMESTAMP + " DESC";

        try {
            Statement getTransaction = db.getConnection().createStatement();
            ResultSet TransactionData = getTransaction.executeQuery(query);


            while (TransactionData.next()) {

                allTransactions.add(new TransactionPOJORefined(TransactionData.getInt(TRANSACTION_ID),TransactionData.getString(CLIENT_FIRST_NAME) + " " + TransactionData.getString(CLIENT_LAST_NAME),TransactionData.getString(PROPERTY_NAME), TransactionData.getDouble(TRANSACTION_AMOUNT), TransactionData.getTimestamp(TRANSACTION_TIMESTAMP)));

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return allTransactions;
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
    public ArrayList<TransactionPOJORefined> getTransactionByDate(Timestamp startDate, Timestamp endDate) {
        String query = "SELECT t." + TRANSACTION_ID + ", t." + TRANSACTION_AMOUNT + ", c." + CLIENT_FIRST_NAME + ", c." + CLIENT_LAST_NAME + ", p." + PROPERTY_NAME +
                ", t." + TRANSACTION_TIMESTAMP +
                " FROM " + TRANSACTION_TABLE + " t " +
                "JOIN " + CLIENT_TABLE + " c ON t." + TRANSACTION_CLIENT_ID + " = c." + CLIENT_ID +
                " JOIN " + PROPERTY_TABLE + " p ON t." + TRANSACTION_PROPERTY_ID + " = p." + PROPERTY_ID +
                " WHERE " + TRANSACTION_TIMESTAMP + " BETWEEN ? AND ? " +
                " ORDER BY " + TRANSACTION_TIMESTAMP + " DESC";

        ArrayList<TransactionPOJORefined> transactions = new ArrayList<>();

        try {
            PreparedStatement statement = db.getConnection().prepareStatement(query);

            statement.setTimestamp(1, startDate);
            statement.setTimestamp(2, endDate);
            //Statement getTransactionByDate = db.getConnection().createStatement();
            ResultSet TransactionData = statement.executeQuery();

            while(TransactionData.next()) {

                transactions.add(new TransactionPOJORefined(TransactionData.getInt(TRANSACTION_ID),TransactionData.getString(CLIENT_FIRST_NAME) + " " + TransactionData.getString(CLIENT_LAST_NAME),TransactionData.getString(PROPERTY_NAME), TransactionData.getDouble(TRANSACTION_AMOUNT), TransactionData.getTimestamp(TRANSACTION_TIMESTAMP)));

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(transactions.size());
        return transactions;

    }

    @Override
    public void deleteTransaction(int TransactionId) {
        String query = "DELETE FROM " + TRANSACTION_TABLE + " WHERE " + TRANSACTION_ID + " = ?";

        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {

            st.setInt(1, TransactionId);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Transaction with ID " + TransactionId + " was deleted.");
            } else {
                System.out.println("No transaction found with ID " + TransactionId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting transaction", e);
        }

    }

    @Override
    public void updateTransaction(TransactionPOJO transaction) {
        String query = "UPDATE " + TRANSACTION_TABLE +
                " SET " + TRANSACTION_AMOUNT + " = ?, " + TRANSACTION_CLIENT_ID + " = ?, " + TRANSACTION_PROPERTY_ID + " = ?" +
                " WHERE " + TRANSACTION_ID + " = ?";

        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {
            st.setDouble(1, transaction.getAmount());
            st.setInt(2, transaction.getClient_id());
            st.setInt(3, transaction.getProperty_id());
            st.setInt(4, transaction.getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Transaction with ID " + transaction.getId() + " was updated.");
            } else {
                System.out.println("No transaction found with ID " + transaction.getId());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error updating transaction", e);
        }

    }

    @Override
    public void createTransaction(TransactionPOJO transaction) {

            String query = "INSERT INTO " + TRANSACTION_TABLE + " (" +
            TRANSACTION_AMOUNT + ", " + TRANSACTION_CLIENT_ID + ", " + TRANSACTION_PROPERTY_ID + ") " +
            "VALUES (?, ?, ?)";

        try (PreparedStatement st = db.getConnection().prepareStatement(query)) {
            st.setDouble(1, transaction.getAmount());
            st.setInt(2, transaction.getClient_id());
            st.setInt(3, transaction.getProperty_id());
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Transaction with ID " + transaction.getId() + " was created.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating transaction", e);
        }


    }

    @Override
    public ArrayList<TransactionPOJO> getAllTransactions2() {
        ArrayList<TransactionPOJO> transactions = new ArrayList<>();

        String query = "SELECT t." + TRANSACTION_ID + ", t." + TRANSACTION_AMOUNT + ", c." + TRANSACTION_CLIENT_ID + ", c." + TRANSACTION_PROPERTY_ID + ", p." + TRANSACTION_TIMESTAMP +
                " FROM " + TRANSACTION_TABLE + " t " +
                " ORDER BY " + TRANSACTION_ID;

        try{
            Statement getTransaction = db.getConnection().createStatement();
            ResultSet TransactionData = getTransaction.executeQuery(query);

            while (TransactionData.next()) {

                transactions.add(new TransactionPOJO(TransactionData.getInt(TRANSACTION_ID),TransactionData.getDouble(TRANSACTION_AMOUNT),TransactionData.getInt(TRANSACTION_CLIENT_ID),TransactionData.getInt(TRANSACTION_PROPERTY_ID),TransactionData.getTimestamp(TRANSACTION_TIMESTAMP)));

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return transactions;


    }
}
