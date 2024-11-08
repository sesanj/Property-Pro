package TableQuery;

import Dao.TransactionDAO;
import Database.Database;
import com.example.propertypro.Pojo.TransactionPOJO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;

public class TransactionTable implements TransactionDAO {
    Database db = Database.getNewDatabase();
    @Override
    public ArrayList<TransactionPOJO> getAllTransactions() {
        ArrayList<TransactionPOJO> transactions = new ArrayList<>();
        String query = "SELECT * FROM " + TRANSACTION_TABLE;

        try{
            Statement getTransaction = db.getConnection().createStatement();
            ResultSet TransactionData = getTransaction.executeQuery(query);

            while (TransactionData.next()) {
                transactions.add(new TransactionPOJO(TransactionData.getInt(TRANSACTION_ID),TransactionData.getDouble(TRANSACTION_AMOUNT),TransactionData.getInt(TRANSACTION_PROPERTY_ID),TransactionData.getInt(TRANSACTION_CLIENT_ID),TransactionData.getTimestamp(TRANSACTION_TIMESTAMP)));

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }

    @Override
    public TransactionPOJO getTransactionById(int TransactionId) {
        String query = "SELECT * FROM " + TRANSACTION_TABLE + " WHERE " + TRANSACTION_ID + "= " + TransactionId;
        TransactionPOJO transaction = null;
        try{
            Statement getTransactionById = db.getConnection().createStatement();
            ResultSet TransactionData = getTransactionById.executeQuery(query);
            if (TransactionData.next()) {
                transaction = new TransactionPOJO(TransactionData.getInt(TRANSACTION_ID),TransactionData.getDouble(TRANSACTION_AMOUNT),TransactionData.getInt(TRANSACTION_PROPERTY_ID),TransactionData.getInt(TRANSACTION_CLIENT_ID),TransactionData.getTimestamp(TRANSACTION_TIMESTAMP));
                return transaction;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public TransactionPOJO getTransactionByUser(int user_id) {
        String query = "SELECT * FROM " + TRANSACTION_TABLE + " WHERE " + TRANSACTION_CLIENT_ID + "= " + user_id;
        TransactionPOJO transaction = null;
        try {
            Statement getTransactionByUser = db.getConnection().createStatement();
            ResultSet TransactionData = getTransactionByUser.executeQuery(query);
            if (TransactionData.next()) {
                transaction = new TransactionPOJO(TransactionData.getInt(TRANSACTION_ID),TransactionData.getDouble(TRANSACTION_AMOUNT),TransactionData.getInt(TRANSACTION_PROPERTY_ID),TransactionData.getInt(TRANSACTION_CLIENT_ID),TransactionData.getTimestamp(TRANSACTION_TIMESTAMP));
                return transaction;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public TransactionPOJO getTransactionByProperty(int property_Id) {
        String query = "SELECT * FROM " + TRANSACTION_TABLE + " WHERE " + TRANSACTION_PROPERTY_ID + "= " + property_Id;
        TransactionPOJO transaction = null;

        try {
            Statement getTransactionByProperty = db.getConnection().createStatement();
            ResultSet TransactionData = getTransactionByProperty.executeQuery(query);
            if (TransactionData.next()) {
                transaction = new TransactionPOJO(TransactionData.getInt(TRANSACTION_ID),TransactionData.getDouble(TRANSACTION_AMOUNT),TransactionData.getInt(TRANSACTION_PROPERTY_ID),TransactionData.getInt(TRANSACTION_CLIENT_ID),TransactionData.getTimestamp(TRANSACTION_TIMESTAMP));
                return transaction;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public TransactionPOJO getTransactionByDate(String date) {
        String query = "SELECT * FROM " + TRANSACTION_TABLE + " WHERE " + TRANSACTION_TIMESTAMP + "= " + date;
        TransactionPOJO transaction = null;

        try {
            Statement getTransactionByDate = db.getConnection().createStatement();
            ResultSet TransactionData = getTransactionByDate.executeQuery(query);
            if (TransactionData.next()) {
                transaction = new TransactionPOJO(TransactionData.getInt(TRANSACTION_ID),TransactionData.getDouble(TRANSACTION_AMOUNT),TransactionData.getInt(TRANSACTION_PROPERTY_ID),TransactionData.getInt(TRANSACTION_CLIENT_ID),TransactionData.getTimestamp(TRANSACTION_TIMESTAMP));
                return transaction;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
