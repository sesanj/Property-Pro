package ManageClient;

import Database.Database;
import Overview.TopClients;
import TableQuery.ClientTable;
import TableQuery.PropertyTable;
import TableQuery.TransactionTable;
import com.example.propertypro.Pojo.ClientPOJO;
import com.example.propertypro.Pojo.PropertyPOJORefined;
import com.example.propertypro.Pojo.TransactionPOJORefined;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;

public class ClientData extends BorderPane {

    private static Text name;
    private static Text phone;
    private static Text email;
    private static Text totalRevenueText;

    public ClientData() {
        // Initialize table query objects to get data
        TransactionTable transactionTable = new TransactionTable();
        ClientTable clientTable = new ClientTable();
        PropertyTable propertyTable = new PropertyTable();

        // Get data from the database
        ArrayList<TransactionPOJORefined> allTransactions = transactionTable.getAllTransactions();
        ArrayList<ClientPOJO> allClients = clientTable.getAllClient();
        ArrayList<PropertyPOJORefined> allProperties = propertyTable.getAllProperty();

        // Create layout containers
        VBox allContents = new VBox(30);
        VBox NameBox = new VBox(6);
        VBox PhoneBox = new VBox(6);
        VBox EmailBox = new VBox(6);
        VBox ExpenseBox = new VBox(6);
        VBox RankBox = new VBox(6);

        // Labels for the data fields
        Label name_Label = new Label("Name");
        Label phone_Label = new Label("Phone");
        Label email_Label = new Label("Email");
        Label total_revenue_Label = new Label("Amount Spent");
        Label rank_label = new Label("Rank");

        // Styling for the text
        String style = "-fx-fill: #1a1b2e; -fx-font-size: 25px; -fx-font-weight: bold;";

        // Summary text
        Text summary = new Text("Summary");
        summary.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        // Total revenue text
        totalRevenueText = new Text();
        totalRevenueText.setStyle("-fx-font-size: 30px; -fx-fill: green; -fx-font-weight: bold;");

        // Calculate total revenue for all transactions
        double totalRevenue = 0;
        String figure = "";
        for (TransactionPOJORefined transaction : allTransactions) {
            totalRevenue += transaction.getAmount();
        }

        // Format the total revenue with suffix (K, M, B)
        if (totalRevenue > 999999999) {
            figure = "B";
            totalRevenue /= 1000000000;
        } else if (totalRevenue > 999999) {
            figure = "M";
            totalRevenue /= 1000000;
        } else if (totalRevenue > 999) {
            figure = "K";
            totalRevenue /= 1000;
        }

        String formattedRevenue = String.format("%,.2f", totalRevenue);
//        totalRevenueText.setText("$" + formattedRevenue + figure + "+");

        // Add summary and total revenue once to the main container
        allContents.getChildren().addAll(summary, totalRevenueText);

        // Loop through all clients to display their data
            // Create and format client-specific fields
        name = new Text();
        name.setStyle(style);

         phone = new Text();
        phone.setStyle(style);

        email = new Text();
        email.setStyle(style);


        // Set rank based on the total spent by the client (optional for now)
//        String rank = "Bronze";
//        if (clientTotalSpent > 10000) {
//            rank = "Gold";
//        } else if (clientTotalSpent > 5000) {
//            rank = "Silver";
//        }

//        Text rankText = new Text(rank);
//        rankText.setStyle(style);

        // Add client-specific data to the respective VBox containers
        NameBox.getChildren().addAll(name, name_Label);
        PhoneBox.getChildren().addAll(phone, phone_Label);
        EmailBox.getChildren().addAll(email, email_Label);

        // Format and display the total amount spent by the client
//        Text clientSpentText = new Text(String.format("%,.2f", clientTotalSpent));
//        clientSpentText.setStyle(style);
//        ExpenseBox.getChildren().addAll(clientSpentText, total_revenue_Label);

        //RankBox.getChildren().addAll(rankText);

        // After the loop, add all client data containers (VBox) to the main container
        allContents.getChildren().addAll(NameBox, PhoneBox, EmailBox, ExpenseBox, RankBox);

        // Set layout styles
        allContents.setAlignment(Pos.CENTER_LEFT);
        allContents.setStyle("-fx-padding: 50px");

        // Set the main content area of the BorderPane
        this.setCenter(allContents);

    }
    public static double getClientRevenue(int client_id){
        Database db =Database.getNewDatabase();
        String query = "SELECT c." +CLIENT_FIRST_NAME + ", " +
                "SUM(t. "+ TRANSACTION_AMOUNT +") AS revenue " +
                "FROM " + CLIENT_TABLE + " c " +
                "JOIN " + TRANSACTION_TABLE + " t ON c." +CLIENT_ID + " = t." + CLIENT_ID +
                " WHERE c." + CLIENT_ID + " = " + client_id;

        double revenue =0;
        try{
            Statement getClients = db.getConnection().createStatement();
            ResultSet data = getClients.executeQuery(query);

            while (data.next()){
                revenue += data.getDouble("revenue");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return revenue;
    }


    public static void getClientDetails(ClientPOJO clientDetails){

        name.setText(clientDetails.getFirst_name() + " " + clientDetails.getLast_name());
        phone.setText(clientDetails.getPhone_number());
        email.setText(clientDetails.getEmail());


        totalRevenueText.setText("$" + String.format("%,.2f", getClientRevenue(clientDetails.getClient_id())));


    }
}
