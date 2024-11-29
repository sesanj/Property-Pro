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

    public ClientTransactions() {
        // Initialize the TableView and columns
        userTable = new TableView<>();
        userTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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
        amount.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getAmount())));

        // Column for Timestamp with proper formatting
        TableColumn<TransactionPOJORefined, String> timestamp = new TableColumn<>("Timestamp");


//        timestamp.setCellValueFactory(e -> {
//            // Format the timestamp to a more readable format
//            Timestamp ts = e.getValue().getTimestamp();
//            if (ts != null) {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                return new SimpleStringProperty(sdf.format(ts));
//            } else {
//                return new SimpleStringProperty("N/A");
//            }
//        });

        // Add columns to the TableView
        userTable.getColumns().addAll(id, client, property, amount, timestamp);
        userTable.getStylesheets().add(getClass().getResource("/tableView.css").toExternalForm());

        // Add table to the layout
        VBox transactionBox = new VBox(30);
        Text title = new Text("All Transactions");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        transactionBox.getChildren().addAll(title, userTable);
        transactionBox.setAlignment(Pos.CENTER_LEFT);
        transactionBox.setStyle("-fx-padding: 30px 50px 50px 50px");

        this.setCenter(transactionBox);
    }

    public static void getClientTransaction(int clientID) {

        userTable.getItems().clear();
        userTable.getItems().addAll(transactionTable.getTransactionByUser(clientID));

    }
}
