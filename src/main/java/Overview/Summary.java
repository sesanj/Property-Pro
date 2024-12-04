package Overview;

import Animations.Animations;
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

/**
 * Represents the summary view displaying key statistics of the property management system.
 * This includes total revenue, the number of transactions, clients, and properties.
 * It uses a vertical layout to show these figures, with animations for a dynamic effect.
 */
public class Summary extends BorderPane {

    /**
     * Constructs the Summary view, which displays a summary of the system's key figures
     * such as total revenue, number of transactions, clients, and properties. The values
     * are fetched from the respective tables, and the UI is animated to highlight the figures.
     */
    public Summary(){

        // Initialize the tables to retrieve data
        TransactionTable transactionTable = new TransactionTable();
        ClientTable clientTable = new ClientTable();
        PropertyTable propertyTable = new PropertyTable();

        // Retrieve all records from the database
        ArrayList<TransactionPOJORefined> allTransactions = transactionTable.getAllTransactions();
        ArrayList<ClientPOJO> allClients = clientTable.getAllClient();
        ArrayList<PropertyPOJORefined> allProperties = propertyTable.getAllProperty();

        // Create VBox containers for displaying labels and figures
        VBox allContents = new VBox(30);
        VBox revenueBox = new VBox(6);
        VBox transactionBox = new VBox(6);
        VBox clientBox = new VBox(6);
        VBox propertyBox = new VBox(6);

        // Create labels for each section
        Label revenueLabel = new Label("Total Revenue");
        Label transactionLabel = new Label("Total Transactions");
        Label clientLabel = new Label("Current Clients");
        Label propertyLabel = new Label("Properties");

        // Define style for text elements
        String style = "-fx-fill: #1a1b2e; -fx-font-size: 25px; -fx-font-weight: bold;";

        // Initialize summary section title
        Text summary = new Text("Summary");
        summary.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        // Initialize text elements to display the summary figures
        Text totalRevenueText = new Text();
        totalRevenueText.setStyle("-fx-font-size: 30px; -fx-fill: green; -fx-font-weight: bold;");
        Text totalTransactions = new Text();
        totalTransactions.setStyle(style);
        Text totalClients = new Text();
        totalClients.setStyle(style);
        Text totalProperties = new Text();
        totalProperties.setStyle(style);

        // Calculate total revenue and other counts
        double totalRevenue = 0;
        int transactionCount = allTransactions.size();
        int clientCount = allClients.size();
        int propertyCount = allProperties.size();
        String figure = "";

        // Sum up total revenue
        for(TransactionPOJORefined transaction : allTransactions){
            totalRevenue += transaction.getAmount();
        }

        // Format the figures for display
        String formattedRevenue = String.format("%,.2f", totalRevenue);
        String formattedTransactionCount = String.format("%,d", transactionCount);
        String formattedClientCount = String.format("%,d", clientCount);
        String formattedPropertyCount = String.format("%,d", propertyCount);

        // Add suffixes (K, M, B) for large revenue values
        if (totalRevenue > 999999 && totalRevenue < 1000000000){
            figure = "M";
        } else if (totalRevenue > 999 && totalRevenue < 1000000) {
            figure = "K";
        } else if (totalRevenue > 999999999) {
            figure = "B";
        }

        // Set text for each figure
        totalRevenueText.setText("$" + formattedRevenue + figure + "+");
        totalTransactions.setText(formattedTransactionCount + "+");
        totalClients.setText(formattedClientCount + "+");
        totalProperties.setText(formattedPropertyCount + "+");

        // Add the text and labels to their respective boxes
        revenueBox.getChildren().addAll(revenueLabel, totalRevenueText);
        transactionBox.getChildren().addAll(transactionLabel, totalTransactions);
        clientBox.getChildren().addAll(clientLabel, totalClients);
        propertyBox.getChildren().addAll(propertyLabel, totalProperties);

        // Add all sections to the main content box
        allContents.getChildren().addAll(summary, revenueBox, clientBox, propertyBox, transactionBox);
        allContents.setAlignment(Pos.CENTER_LEFT);
        allContents.setStyle("-fx-padding: 50px");

        // Set the content box as the center of the BorderPane
        this.setCenter(allContents);

        // Apply animations to the text and labels
        animate(totalRevenueText, totalClients, totalProperties, totalTransactions, revenueLabel, clientLabel, propertyLabel, transactionLabel);
    }

    /**
     * Animates the summary text and labels with a sliding effect to create a dynamic appearance.
     *
     * @param revenue The text displaying total revenue.
     * @param client The text displaying the total number of clients.
     * @param property The text displaying the total number of properties.
     * @param transaction The text displaying total transactions.
     * @param revenueLabel The label for revenue.
     * @param clientLabel The label for clients.
     * @param propertyLabel The label for properties.
     * @param transactionLabel The label for transactions.
     */
    public static void animate(Text revenue, Text client, Text property, Text transaction, Label revenueLabel,
                               Label clientLabel, Label propertyLabel, Label transactionLabel){

        // Apply animations to each text and label with increasing delay
        Animations.translate(revenue, 600);
        Animations.translate(client, 800);
        Animations.translate(property, 1000);
        Animations.translate(transaction, 1200);

        Animations.translate(revenueLabel, 600);
        Animations.translate(clientLabel, 800);
        Animations.translate(propertyLabel, 1000);
        Animations.translate(transactionLabel, 1200);
    }
}
