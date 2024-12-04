package ManageRevenue;

import Animations.Animations;
import TableQuery.ClientTable;
import TableQuery.PropertyTable;
import TableQuery.TransactionTable;
import com.example.propertypro.Pojo.ClientPOJO;
import com.example.propertypro.Pojo.PropertyPOJO;
import com.example.propertypro.Pojo.TransactionPOJO;
import com.example.propertypro.Pojo.TransactionPOJORefined;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the form for managing revenue transactions, allowing the user to add or update transactions.
 * Includes UI components for selecting clients and properties, entering transaction amounts, and displaying results.
 */
public class RevenueForm extends BorderPane {

    public static ComboBox<ClientPOJO> allClients;
    public static ComboBox<PropertyPOJO> allProperties;
    public static TextField amount;
    public static int updatableTransactionID;
    public static boolean updateFormClicked = false;
    public static Text prompt;
    public static PropertyTable propertyTable = new PropertyTable();
    public static ClientTable clientTable = new ClientTable();


    /**
     * Initializes the RevenueForm, setting up the UI components and handling button actions
     * for adding or updating transactions.
     */
    public RevenueForm(){

        // Initialize ComboBoxes and TextField for input
        allClients = new ComboBox<>();
        allClients.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        allProperties = new ComboBox<>();
        allProperties.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        amount = new TextField();
        amount.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        // Title for the page
        Text title = new Text("Manage Transactions");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        // Set up layout containers
        VBox layout = new VBox(25);
        HBox buttonBox = new HBox(20);

        // Create buttons for transaction actions
        Button newTransactionButton = new Button("New Transaction");
        newTransactionButton.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());
        newTransactionButton.getStyleClass().add("tabButton");

        Button updateTransaction = new Button("Update Transaction");
        updateTransaction.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());
        updateTransaction.getStyleClass().add("tabButton");

        Button addTransaction = new Button("Add Transaction");
        addTransaction.getStyleClass().add("formButton");
        addTransaction.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        Button updateTransactionButton = new Button("Update Transaction");
        updateTransactionButton.getStyleClass().add("formButton");
        updateTransactionButton.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        // Arrange buttons in a horizontal layout
        buttonBox.getChildren().addAll(newTransactionButton, updateTransaction);
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        // Action for creating a new transaction
        newTransactionButton.setOnAction(e -> {
            layout.getChildren().clear();
            layout.getChildren().addAll(title, buttonBox, transactionForm(), addTransaction);
            updateFormClicked = false;
            prompt.setText("");
            Animations.translate(addTransaction, 1000);
        });

        // Action for updating an existing transaction
        updateTransaction.setOnAction(e -> {
            layout.getChildren().clear();
            layout.getChildren().addAll(title, buttonBox, transactionForm(), updateTransactionButton);
            updateFormClicked = true;
            prompt.setText("Click On A Transaction To Update");
            Animations.translate(updateTransactionButton, 1000);
        });

        // Action for adding a new transaction
        addTransaction.setOnAction(e -> {
            if(!amount.getText().isEmpty() && !allClients.getEditor().getText().isEmpty() && allProperties.getSelectionModel().getSelectedItem() != null ){

                double amountEntered = Double.parseDouble(amount.getText().trim());

                // Find the selected client and create a new transaction
                for (ClientPOJO client : clientTable.getAllClient()){
                    String name = client.getFirst_name() + " " + client.getLast_name();
                    String selectedName = allClients.getEditor().getText();

                    if(name.equals(selectedName)){
                        TransactionPOJO newTransaction = new TransactionPOJO(0, amountEntered,
                                client.getClient_id(),
                                allProperties.getSelectionModel().getSelectedItem().getProperty_id(),
                                new Timestamp(System.currentTimeMillis()));

                        TransactionTable transactionTable = new TransactionTable();
                        transactionTable.createTransaction(newTransaction);
                        AllTransaction.allTransactions.getItems().add(newTransaction);
                    }
                }

                AllTransaction.title.setText(AllTransaction.allTransactions.getItems().size() + " Transaction(s)");
                RevenueData.addRevenue(amountEntered);
                RevenueData.addTransactionCount();

                prompt.setText("New Transaction Created Successfully!");
                prompt.setStyle("-fx-fill: green; -fx-font-size: 14px; -fx-font-style: italic;");
            } else {
                prompt.setText("Can't Add New Transaction, Try Again, Fields Cannot Be Empty!");
                prompt.setStyle("-fx-fill: #860a0a; -fx-font-size: 14px; -fx-font-style: italic;");
            }
        });

        // Action for updating an existing transaction
        updateTransactionButton.setOnAction(e -> {
            if(!amount.getText().isEmpty() && !allClients.getEditor().getText().isEmpty() && allProperties.getSelectionModel().getSelectedItem() != null ){

                double amountEntered = Double.parseDouble(amount.getText().trim());

                // Find the selected client and update the existing transaction
                for (ClientPOJO client : clientTable.getAllClient()){
                    String name = client.getFirst_name() + " " + client.getLast_name();
                    String selectedName = allClients.getEditor().getText();

                    if(name.equals(selectedName)){
                        TransactionPOJO newTransaction = new TransactionPOJO(getUpdatableTransactionID(), amountEntered,
                                client.getClient_id(),
                                allProperties.getSelectionModel().getSelectedItem().getProperty_id(),
                                new Timestamp(System.currentTimeMillis()));

                        TransactionTable transactionTable = new TransactionTable();
                        transactionTable.updateTransaction(newTransaction);
                    }
                }

                prompt.setText("Your Transaction Has Been Updated Successfully!");
                prompt.setStyle("-fx-fill: green; -fx-font-size: 14px; -fx-font-style: italic;");
            }
            else{
                prompt.setText("Can't Update Transaction, Try Again, Fields Cannot Be Empty!");
                prompt.setStyle("-fx-fill: #860a0a; -fx-font-size: 14px; -fx-font-style: italic;");
            }
        });

        // Add all elements to the layout
        layout.getChildren().addAll(title, buttonBox, transactionForm(), addTransaction);
        this.setCenter(layout);

        // Style the layout with padding
        layout.setStyle("-fx-padding: 30px 50px 30px 50px");

        // Apply animations to buttons
        Animations.translate(buttonBox, 600);
        Animations.translate(addTransaction, 1200);
    }


    /**
     * Creates and returns the form layout for adding or updating transactions.
     * Resets the fields to default empty states.
     *
     * @return VBox containing the transaction form
     */
    public static VBox transactionForm(){

        // Clear previous selections and inputs
        allClients.getSelectionModel().clearSelection();
        allProperties.getSelectionModel().clearSelection();
        allProperties.getEditor().clear();
        allClients.getEditor().clear();
        amount.setText("");

        // Create a container for the form
        VBox containerBox = new VBox();

        // Create individual sections for amount, client, and property inputs
        VBox amountBox = new VBox(6);
        VBox clientBox = new VBox(6);
        VBox propertyBox = new VBox(6);
        VBox formBox = new VBox(15);

        // Create and style the prompt text
        prompt = new Text();
        prompt.setStyle("-fx-fill: #202469; -fx-font-size: 14px; -fx-font-style: italic;");

        // Amount input
        Label amountLabel = new Label("Amount");
        amount.setMaxWidth(250);
        amount.setPromptText("Enter Transaction Amount");
        amount.setStyle("-fx-border-color: #202469; -fx-border-width: 1px; -fx-border-radius: 5px;" +
                " -fx-font-size: 14px;");

        // Client input
        Label clientLabel = new Label("Client");
        allClients.setItems(FXCollections.observableArrayList(clientTable.getAllClient()));
        allClients.setEditable(true);
        allClients.setPromptText("Search For Client");
        allClients.setPrefWidth(250);

        // Property input
        Label propertyLabel = new Label("Property");
        allProperties.setItems(FXCollections.observableArrayList(propertyTable.getAllPropertyRaw()));
        allProperties.setPromptText("Select A Property");
        allProperties.setPrefWidth(250);

        // Handle client search via key input
        allClients.setOnKeyReleased(event -> {
            String selectedClient = allClients.getEditor().getText().toLowerCase();
            List<ClientPOJO> searchedItems = clientTable.getAllClient().stream()
                    .filter(first_name -> first_name.getFirst_name().toLowerCase().contains(selectedClient))
                    .collect(Collectors.toList());
            allClients.getItems().setAll(searchedItems);

            allClients.show();
        });

        // Add the elements to their respective sections
        amountBox.getChildren().addAll(amountLabel, amount);
        clientBox.getChildren().addAll(clientLabel, allClients);
        propertyBox.getChildren().addAll(propertyLabel, allProperties);

        // Add sections to the form box
        formBox.getChildren().addAll(prompt, amountBox, clientBox, propertyBox);

        // Add the form box to the container box
        containerBox.getChildren().addAll(formBox);
        containerBox.setAlignment(Pos.TOP_LEFT);

        // Apply animation effects
        animate(amountBox, clientBox, propertyBox);

        // Return the fully constructed form layout
        return containerBox;
    }

    /**
     * Retrieves and populates the transaction details in the form for updating.
     * This method is called when updating an existing transaction to pre-fill the form fields.
     * It sets the transaction amount, client, and property based on the provided transaction details.
     *
     * @param transaction the transaction to retrieve details from, containing the amount, client, and property information
     */
    public static void getTransactionDetails(TransactionPOJORefined transaction){

        if (updateFormClicked){

            // Set the transaction amount in the form
            amount.setText(transaction.getAmount() + "");

            ClientPOJO updatableClient = null;
            PropertyPOJO updatableProperty = null;

            // Find the corresponding client for the transaction
            for (ClientPOJO client : clientTable.getAllClient()){
                String name = client.getFirst_name() + " " + client.getLast_name();
                String selectedName = transaction.getClient_id();

                if(name.equals(selectedName)){
                    updatableClient = client;
                }
            }

            // Find the corresponding property for the transaction
            for (PropertyPOJO property : propertyTable.getAllPropertyRaw()){
                String name = property.getName();
                String selectedName = transaction.getProperty_id();

                if(name.equals(selectedName)){
                    updatableProperty = property;
                }
            }

            // Pre-select the client and property in the form
            allClients.getSelectionModel().select(updatableClient);
            allProperties.getSelectionModel().select(updatableProperty);
        }
    }


    /**
     * Animates the transition of the given nodes by applying translation effects.
     * This method translates the provided nodes (revenue, transaction, best) with different durations.
     *
     * @param revenue the Node representing the revenue element to be animated
     * @param transaction the Node representing the transaction element to be animated
     * @param best the Node representing the best element to be animated
     */
    public static void animate(Node revenue, Node transaction, Node best){
        Animations.translate(revenue, 600);
        Animations.translate(transaction, 750);
        Animations.translate(best, 900);
    }

    /**
     * Retrieves the ID of the transaction that is currently being updated.
     *
     * @return the updatable transaction ID
     */
    public static int getUpdatableTransactionID() {
        return updatableTransactionID;
    }

    /**
     * Sets the ID of the transaction that is currently being updated.
     *
     * @param updatableTransactionID the ID of the transaction to be updated
     */
    public static void setUpdatableTransactionID(int updatableTransactionID) {
        RevenueForm.updatableTransactionID = updatableTransactionID;
    }
}
