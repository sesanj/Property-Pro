package dao;

import java.util.ArrayList;

public interface TransactionDAO {
    public ArrayList<Transaction> getAllTransactions();
    public Transaction getId(int TransactionId);
    public Transaction getAmount(double TransactionAmount);
    public Transaction getUserID(int user_id);
    public Transaction getPropertyID(int property_Id);
    public Transaction getTimeStamp(String timeStamp);
}
