public class TopClients {
    private String first_name;
    private String last_name;
    private String phone_number;
    private int total_transactions;
    private double amount;


    public TopClients(String first_name, String last_name, int total_transactions, String phone_number, double amount) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.amount = amount;
        this.total_transactions = total_transactions;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTotal_transactions() {
        return total_transactions;
    }

    public void setTotal_transactions(int total_transactions) {
        this.total_transactions = total_transactions;
    }

    @Override
    public String toString() {
        return first_name + " " + last_name;
    }
}

