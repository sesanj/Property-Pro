package Overview;

import Animations.Animations;
import Database.Database;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;

/**
 * The TopUsers class represents a view of the top 20 clients based on the total amount spent.
 * It retrieves data from the database and displays it in a TableView with client details such as
 * name, phone number, total transactions, and the amount spent.
 */
public class TopUsers extends BorderPane {

    /**
     * Constructs a TopUsers view by querying the top 20 clients from the database
     * and displaying the results in a TableView.
     */
    public TopUsers() {

        // Initialize the database connection
        Database db = Database.getNewDatabase();

        // Query to retrieve the top 20 clients
        String query = "SELECT c." + CLIENT_FIRST_NAME + ", c." + CLIENT_LAST_NAME + ", c." + CLIENT_PHONE_NUMBER + ", c." + CLIENT_EMAIL + ", c." + CLIENT_ID + ", " +
                "SUM(t." + TRANSACTION_AMOUNT + ") AS amountSpent, " +
                "COUNT(t." + TRANSACTION_ID + ") AS totalTransactions " +
                "FROM " + CLIENT_TABLE + " c " +
                "JOIN " + TRANSACTION_TABLE + " t ON c." + CLIENT_ID + " = t." + CLIENT_ID + " " +
                "GROUP BY c." + CLIENT_ID + ", c." + CLIENT_FIRST_NAME + " " +
                "ORDER BY amountSpent DESC LIMIT 20";

        VBox topClientsBox = new VBox(30);

        // Title for the TableView
        Text title = new Text("Top 20 Clients");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        // TableView to display top client data
        TableView userTable = new TableView();
        userTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // List to hold TopClients objects
        ArrayList<TopClients> topClients = new ArrayList<>();

        try {
            // Execute the query and populate the list with TopClients data
            Statement getClients = db.getConnection().createStatement();
            ResultSet data = getClients.executeQuery(query);

            while(data.next()){

                topClients.add(new TopClients(data.getInt(CLIENT_ID), data.getString(CLIENT_FIRST_NAME), data.getString(CLIENT_LAST_NAME), data.getInt("totalTransactions"), data.getString(CLIENT_PHONE_NUMBER), data.getDouble("amountSpent"), data.getString(CLIENT_EMAIL)));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Define columns for the TableView
        TableColumn<TopClients, String> firstName = new TableColumn<>("First Name");
        firstName.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getFirst_name()));

        TableColumn<TopClients, String> lastName = new TableColumn<>("Last Name");
        lastName.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getLast_name()));

        TableColumn<TopClients, String> phoneNumber = new TableColumn<>("Phone Number");
        phoneNumber.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getPhone_number()));

        TableColumn<TopClients, String> totalTransactions = new TableColumn<>("Transactions");
        totalTransactions.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getTotal_transactions() + ""));

        TableColumn<TopClients, String> amount = new TableColumn<>("Amount Spent");
        amount.setCellValueFactory(e-> new SimpleStringProperty("$" + String.format("%,.2f", e.getValue().getAmount())));

        // Add columns to the TableView
        userTable.getColumns().addAll(firstName, lastName, phoneNumber, totalTransactions, amount);

        // Add data to the TableView
        userTable.getItems().addAll(topClients);
        userTable.getStylesheets().add(getClass().getResource("/tableView.css").toExternalForm());

        // Add the title and TableView to the VBox
        topClientsBox.getChildren().addAll(title, userTable);
        topClientsBox.setAlignment(Pos.CENTER_LEFT);
        topClientsBox.setStyle("-fx-padding: 30px 50px 50px 50px");

        // Set the VBox as the center of the BorderPane
        this.setCenter(topClientsBox);

        // Apply animation to the TableView
        Animations.translate(userTable, 800);
    }
}
