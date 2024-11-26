package Dao;

import com.example.propertypro.Pojo.ProvincePOJO;
import com.example.propertypro.Pojo.TransactionPOJO;
import com.example.propertypro.Pojo.TransactionPOJORefined;


import java.sql.Timestamp;
import java.util.ArrayList;

public interface TransactionDAO {

    public ArrayList<TransactionPOJORefined> getAllTransactions();
    public TransactionPOJORefined getTransactionById(int TransactionId);
    public TransactionPOJORefined getTransactionByUser(int user_id);
    public TransactionPOJORefined getTransactionByProperty(int property_Id);
    public ArrayList<TransactionPOJORefined> getTransactionByDate(Timestamp startDate, Timestamp endDate);

    public void deleteTransaction(int TransactionId);
    public void updateTransaction(TransactionPOJO transaction);
    public void createTransaction(TransactionPOJO transaction);
}

