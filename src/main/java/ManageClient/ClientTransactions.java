package ManageClient;

import Animations.Animations;
import TableQuery.TransactionTable;
import com.example.propertypro.Pojo.TransactionPOJORefined;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * ClientTransactions is a JavaFX component that displays a list of transactions
 * for a specific client. It provides a table to show transaction details including
 * transaction ID, client, property, amount, and date.
 */
public class ClientTransactions extends BorderPane {

    public static TransactionTable transactionTable = new TransactionTable();  // Instance of TransactionTable to fetch transaction data
    private static TableView<TransactionPOJORefined> userTable;  // TableView for displaying transaction data
    public static Text title;  // Text element for the title of the transaction view

    /**
     * Constructor to initialize the ClientTransactions view.
     * It sets up the TableView, table columns, and the layout of the transaction screen.
     */
    public ClientTransactions() {
        // Initialize the TableView and set its column resize policy
        userTable = new TableView<>();
        userTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Set the title of the page
        title = new Text("Clients Transactions");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        // Column for Transaction ID
        TableColumn<TransactionPOJORefined, String> id = new TableColumn<>("Id");
        id.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getId())));

        // Column for Client's Full Name
        TableColumn<TransactionPOJORefined, String> client = new TableColumn<>("Client");
        client.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getClient_id()));

        // Column for Property Name
        TableColumn<TransactionPOJORefined, String> property = new TableColumn<>("Property");
        property.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getProperty_id()));

        // Column for Transaction Amount
        TableColumn<TransactionPOJORefined, String> amount = new TableColumn<>("Amount");
        amount.setCellValueFactory(e -> new SimpleStringProperty("$" + String.format("%,.2f", e.getValue().getAmount())));

        // Column for Timestamp with proper date formatting
        TableColumn<TransactionPOJORefined, String> date = new TableColumn<>("Date");
        date.setCellValueFactory(e -> {
            // Format the timestamp into a readable date format (yyyy-MM-dd)
            Timestamp ts = e.getValue().getTimestamp();
            if (ts != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return new SimpleStringProperty(sdf.format(ts));
            } else {
                return new SimpleStringProperty("N/A");
            }
        });

        // Add all columns to the TableView
        userTable.getColumns().addAll(date, id, client, property, amount);
        userTable.getStylesheets().add(getClass().getResource("/tableView.css").toExternalForm());

        // Fetch and display transactions for the first client in the topClients list
        userTable.getItems().addAll(transactionTable.getTransactionByUser(AllClients.topClients.getFirst().getId()));

        // Set the title of the transaction screen to show the first client's name
        title.setText(AllClients.topClients.getFirst().getFirst_name() + " "  +
                AllClients.topClients.getFirst().getLast_name() + "'s Transactions");

        // Create a VBox to hold the title and the TableView, then set alignment and padding
        VBox transactionBox = new VBox(30);
        transactionBox.getChildren().addAll(title, userTable);
        transactionBox.setAlignment(Pos.TOP_LEFT);
        transactionBox.setStyle("-fx-padding: 50px 50px 50px 50px");

        // Set the VBox as the center content of the BorderPane
        this.setCenter(transactionBox);

        // Apply an animation to the table for smooth rendering
        Animations.translate(userTable, 800);
    }

    /**
     * Updates the transaction table for a given client by clearing the existing items
     * and adding new transactions fetched for the specified client.
     *
     * @param clientID The ID of the client whose transactions are to be displayed.
     * @param clientName The name of the client whose transactions are being displayed.
     */
    public static void getClientTransaction(int clientID, String clientName) {
        // Clear the current items in the table and load the new client's transactions
        userTable.getItems().clear();
        userTable.getItems().addAll(transactionTable.getTransactionByUser(clientID));

        // Update the title with the new client's name
        title.setText(clientName + "'s Transactions");
    }
}