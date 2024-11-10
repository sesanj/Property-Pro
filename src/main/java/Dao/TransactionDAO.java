package Dao;

import com.example.propertypro.Pojo.TransactionPOJO;
import com.example.propertypro.Pojo.TransactionPOJORefined;


import java.sql.Timestamp;
import java.util.ArrayList;

public interface TransactionDAO {
    public ArrayList<TransactionPOJORefined> getAllTransactions();
    public TransactionPOJORefined getTransactionById(int TransactionId);
    public TransactionPOJORefined getTransactionByUser(int user_id);
    public TransactionPOJORefined getTransactionByProperty(int property_Id);
    public TransactionPOJORefined getTransactionByDate(Timestamp date);
}
