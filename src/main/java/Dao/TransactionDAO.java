package Dao;

import com.example.propertypro.Pojo.TransactionPOJO;

import java.util.ArrayList;

public interface TransactionDAO {
    public ArrayList<TransactionPOJO> getAllTransactions();
    public TransactionPOJO getTransactionById(int TransactionId);
    public TransactionPOJO getTransactionByUser(int user_id);
    public TransactionPOJO getTransactionByProperty(int property_Id);
    public TransactionPOJO getTransactionByDate(String date);
}
