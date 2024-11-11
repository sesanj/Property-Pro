package Overview;

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

import java.util.ArrayList;

public class Totals extends BorderPane {

    public Totals(){

        TransactionTable transactionTable = new TransactionTable();
        ClientTable clientTable = new ClientTable();
        PropertyTable propertyTable = new PropertyTable();

        ArrayList<TransactionPOJORefined> allTransactions = transactionTable.getAllTransactions();
        ArrayList<ClientPOJO> allClients = clientTable.getAllClient();
        ArrayList<PropertyPOJORefined> allProperties = propertyTable.getAllProperty();

        VBox allContents = new VBox(30);
        VBox revenueBox = new VBox(10);
        VBox transactionBox = new VBox(10);
        VBox clientBox = new VBox(10);
        VBox propertyBox = new VBox(10);

        //String labelStyle = "-fx-fill: red; -fx-font-size: 14px;";

        Label revenueLabel = new Label("Total Revenue");
        //revenueLabel.setStyle(labelStyle);

        Label transactionLabel = new Label("All Transactions");
        //transactionLabel.setStyle(labelStyle);

        Label clientLabel = new Label("Current Clients");
        //clientLabel.setStyle(labelStyle);

        Label propertyLabel = new Label("Properties");
        //propertyLabel.setStyle(labelStyle);

        String style = "-fx-fill: #1a1b2e; -fx-font-size: 20px; -fx-font-weight: bold;";

        Text summary = new Text("Summary");
        summary.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");

        Text totalRevenueText = new Text();
        totalRevenueText.setStyle("-fx-font-size: 22px; -fx-fill: #202469; -fx-font-weight: bold;");

        Text totalTransactions = new Text();
        totalTransactions.setStyle(style);

        Text totalClients = new Text();
        totalClients.setStyle(style);

        Text totalProperties = new Text();
        totalProperties.setStyle(style);

        double totalRevenue = 0;
        int transactionCount = allTransactions.size();
        int clientCount = allClients.size();
        int propertyCount = allProperties.size();

        String figure = "";

        for(TransactionPOJORefined transaction : allTransactions){

            totalRevenue += transaction.getAmount();
        }

        String formattedRevenue = String.format("%,.2f", totalRevenue);
        String formattedTransactionCount = String.format("%,d", transactionCount);
        String formattedClientCount = String.format("%,d", clientCount);
        String formattedPropertyCount = String.format("%,d", propertyCount);


        if (totalRevenue > 999999 && totalRevenue < 1000000000){
            figure = "M";
        } else if (totalRevenue > 999 && totalRevenue < 1000000) {
            figure = "K";
        } else if (totalRevenue > 999999999) {
            figure = "B";
        }

        totalRevenueText.setText("$" + formattedRevenue + figure);
        totalTransactions.setText(formattedTransactionCount + "+");
        totalClients.setText(formattedClientCount + "+");
        totalProperties.setText(formattedPropertyCount + "+");

        revenueBox.getChildren().addAll(revenueLabel, totalRevenueText);
        transactionBox.getChildren().addAll(transactionLabel, totalTransactions);
        clientBox.getChildren().addAll(clientLabel, totalClients);
        propertyBox.getChildren().addAll(propertyLabel, totalProperties);

        allContents.getChildren().addAll(summary, revenueBox, clientBox, propertyBox, transactionBox);
        allContents.setAlignment(Pos.CENTER_LEFT);
        allContents.setStyle("-fx-padding: 50px");

        this.setCenter(allContents);

    }
}