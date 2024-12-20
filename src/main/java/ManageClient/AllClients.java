package ManageClient;

import Animations.Animations;
import Database.Database;
import Overview.TopClients;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static Database.DatabaseTableConstants.*;
import static Database.DatabaseTableConstants.CLIENT_PHONE_NUMBER;

/**
 * This class represents the view for displaying all clients in the system.
 * It includes a search functionality to filter clients by first name and a table that displays
 * client information such as first name, last name, phone number, total transactions, and amount spent.
 */
public class AllClients extends BorderPane {

    public static int clientId;
    public static ArrayList<TopClients> topClients = new ArrayList<>();
    public static ComboBox<TopClients> searchClient;

    public static Database db = Database.getNewDatabase();
    public static TableView userTable;
    public static Text title = new Text();

    /**
     * Constructor that initializes the AllClients view. Sets up the table to display client information,
     * initializes the search ComboBox, and defines the layout.
     */
    public AllClients() {

        userTable = new TableView();
        userTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        searchClient = new ComboBox<>();
        searchClient.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());
        searchClient.setEditable(true);
        searchClient.setPrefWidth(300);
        searchClient.setMinHeight(30);
        searchClient.setPromptText("Search For Client By First Name");
        searchClient.setItems(FXCollections.observableArrayList(getAllClients()));

        VBox topClientsBox = new VBox(40);

        HBox titleBox = new HBox();
        HBox searchBox = new HBox();
        searchBox.setAlignment(Pos.CENTER_RIGHT);
        searchBox.getChildren().add(searchClient);

        titleBox.getChildren().addAll(title, searchBox);
        HBox.setHgrow(searchBox, Priority.ALWAYS);

        // Sets up the search functionality when a key is released in the search box
        searchClient.setOnKeyReleased(event -> {
            String selectedClient = searchClient.getEditor().getText().toLowerCase();
            List<TopClients> searchedItems = getAllClients().stream()
                    .filter(first_name -> first_name.getFirst_name().toLowerCase().contains(selectedClient))
                    .collect(Collectors.toList());
            searchClient.getItems().setAll(searchedItems);

            searchClient.show();
        });

        // Sets the client details and transactions on client selection
        searchClient.setOnAction(e -> {
            TopClients selectedClient = searchClient.getSelectionModel().getSelectedItem();

            ClientData.getClientDetails(selectedClient);
            ClientForm.getClientDetails(selectedClient);
            ClientTransactions.getClientTransaction(selectedClient.getId(), selectedClient.getFirst_name() + " " + selectedClient.getLast_name());
        });

        // Define the table columns
        TableColumn<TopClients, String> firstName = new TableColumn<>("First Name");
        firstName.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getFirst_name()));

        TableColumn<TopClients, String> lastName = new TableColumn<>("Last Name");
        lastName.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getLast_name()));

        TableColumn<TopClients, String> totalTransactions = new TableColumn<>("Transactions");
        totalTransactions.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getTotal_transactions() + ""));

        TableColumn<TopClients, String> phoneNumber = new TableColumn<>("Phone Number");
        phoneNumber.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getPhone_number()));

        TableColumn<TopClients, String> amount = new TableColumn<>("Amount");
        amount.setCellValueFactory(e -> new SimpleStringProperty("$" + String.format("%,.2f", e.getValue().getAmount())));

        // Add columns to the table
        userTable.getColumns().addAll(firstName, lastName, phoneNumber, totalTransactions, amount);
        userTable.getItems().addAll(getAllClients());

        // Apply custom styles for the table
        userTable.getStylesheets().add(getClass().getResource("/tableView.css").toExternalForm());

        // Add listener to update details on selecting a row in the table
        userTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                TopClients client = (TopClients) userTable.getSelectionModel().getSelectedItem();

                ClientData.getClientDetails(client);

                String clientName = client.getFirst_name() + " " + client.getLast_name();

                ClientTransactions.getClientTransaction(client.getId(), clientName);
                ClientForm.getClientDetails(client);
            }
        });

        topClientsBox.getChildren().addAll(titleBox, userTable);
        topClientsBox.setAlignment(Pos.TOP_LEFT);
        topClientsBox.setStyle("-fx-padding: 50px 50px 20px 50px");

        this.setCenter(topClientsBox);

        Animations.translate(userTable, 800);
    }

    /**
     * Retrieves all clients from the database, including the total amount spent and total transactions for each client.
     *
     * @return An ArrayList of TopClients objects representing all clients with transaction details.
     */
    public static ArrayList<TopClients> getAllClients() {

        topClients.clear();

        String query = "SELECT c." + CLIENT_FIRST_NAME + ", c." + CLIENT_LAST_NAME + ", c." + CLIENT_PHONE_NUMBER + ", c." + CLIENT_EMAIL + ", c." + CLIENT_ID + ", " +
                "SUM(t." + TRANSACTION_AMOUNT + ") AS amountSpent, " +
                "COUNT(t." + TRANSACTION_ID + ") AS totalTransactions " +
                "FROM " + CLIENT_TABLE + " c " +
                "LEFT JOIN " + TRANSACTION_TABLE + " t ON c." + CLIENT_ID + " = t." + CLIENT_ID + " " +
                "GROUP BY c." + CLIENT_ID + ", c." + CLIENT_LAST_NAME + " " +
                "ORDER BY amountSpent DESC";

        try {
            Statement getClients = db.getConnection().createStatement();
            ResultSet data = getClients.executeQuery(query);

            while (data.next()) {
                topClients.add(new TopClients(data.getInt(CLIENT_ID), data.getString(CLIENT_FIRST_NAME), data.getString(CLIENT_LAST_NAME), data.getInt("totalTransactions"), data.getString(CLIENT_PHONE_NUMBER), data.getDouble("amountSpent"), data.getString(CLIENT_EMAIL)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        userTable.getItems().clear();
        userTable.getItems().addAll(topClients);
        title.setText(topClients.size() + " Total Clients");

        return topClients;
    }

    /**
     * Retrieves the client ID.
     *
     * @return The client ID.
     */
    public static int getClientId() {
        return clientId;
    }

    /**
     * Sets the client ID.
     *
     * @param clientId The client ID to be set.
     */
    public static void setClientId(int clientId) {
        AllClients.clientId = clientId;
    }
}
