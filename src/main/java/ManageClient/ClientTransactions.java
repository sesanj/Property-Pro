package ManageClient;

import Database.Database;
import TableQuery.TransactionTable;
import com.example.propertypro.Pojo.TransactionPOJORefined;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;

public class ClientTransactions extends BorderPane {

    public static TransactionTable transactionTable = new TransactionTable();

    // Class-level TableView declaration
    private static TableView<TransactionPOJORefined> userTable;
    public static Text title;

    public ClientTransactions() {
        // Initialize the TableView and columns
        userTable = new TableView<>();
        userTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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

        // Column for Amount
        TableColumn<TransactionPOJORefined, String> amount = new TableColumn<>("Amount");
        amount.setCellValueFactory(e -> new SimpleStringProperty("$" + String.format("%,.2f", e.getValue().getAmount())));

        // Column for Timestamp with proper formatting
        TableColumn<TransactionPOJORefined, String> date = new TableColumn<>("Date");


        date.setCellValueFactory(e -> {
            // Format the timestamp to a more readable format
            Timestamp ts = e.getValue().getTimestamp();
            if (ts != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return new SimpleStringProperty(sdf.format(ts));
            } else {
                return new SimpleStringProperty("N/A");
            }
        });

        // Add columns to the TableView
        userTable.getColumns().addAll(date, id, client, property, amount);
        userTable.getStylesheets().add(getClass().getResource("/tableView.css").toExternalForm());

        userTable.getItems().addAll(transactionTable.getTransactionByUser(AllClients.topClients.getFirst().getId()));

        title.setText(AllClients.topClients.getFirst().getFirst_name() + " "  +
                AllClients.topClients.getFirst().getLast_name() + "'s Transactions");

        // Add table to the layout
        VBox transactionBox = new VBox(30);
        transactionBox.getChildren().addAll(title, userTable);
        transactionBox.setAlignment(Pos.TOP_LEFT);
        transactionBox.setStyle("-fx-padding: 50px 50px 50px 50px");

        this.setCenter(transactionBox);
    }

    public static void getClientTransaction(int clientID, String clientName) {

        userTable.getItems().clear();
        userTable.getItems().addAll(transactionTable.getTransactionByUser(clientID));

        title.setText(clientName + "'s Transactions");

    }
}
