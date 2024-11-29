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
       
        TransactionTable transactionTable = new TransactionTable();
        ClientTable clientTable = new ClientTable();
        PropertyTable propertyTable = new PropertyTable();


        ArrayList<TransactionPOJORefined> allTransactions = transactionTable.getAllTransactions();
        ArrayList<ClientPOJO> allClients = clientTable.getAllClient();
        ArrayList<PropertyPOJORefined> allProperties = propertyTable.getAllProperty();


        VBox allContents = new VBox(30);
        VBox NameBox = new VBox(6);
        VBox PhoneBox = new VBox(6);
        VBox EmailBox = new VBox(6);
       // VBox ExpenseBox = new VBox(6);
        //VBox RankBox = new VBox(6);


        Label name_Label = new Label("Name");
        Label phone_Label = new Label("Phone");
        Label email_Label = new Label("Email");
       // Label total_revenue_Label = new Label("Amount Spent");
      //  Label rank_label = new Label("Rank");

        // Styling for the text
        String style = "-fx-fill: #1a1b2e; -fx-font-size: 25px; -fx-font-weight: bold;";

        // Summary text
        Text summary = new Text("Summary");
        summary.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");


        totalRevenueText = new Text();
        totalRevenueText.setStyle("-fx-font-size: 30px; -fx-fill: green; -fx-font-weight: bold;");





        allContents.getChildren().addAll(summary, totalRevenueText);

        name = new Text();
        name.setStyle(style);

         phone = new Text();
        phone.setStyle(style);

        email = new Text();
        email.setStyle(style);



        NameBox.getChildren().addAll(name_Label, name);
        PhoneBox.getChildren().addAll(phone_Label, phone);
        EmailBox.getChildren().addAll(email_Label, email);


        allContents.getChildren().addAll(NameBox, PhoneBox, EmailBox);


        allContents.setAlignment(Pos.CENTER_LEFT);
        allContents.setStyle("-fx-padding: 50px");


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
