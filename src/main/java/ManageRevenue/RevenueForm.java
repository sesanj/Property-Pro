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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RevenueForm extends BorderPane {

    public static ComboBox<ClientPOJO> allClients;
    public static ComboBox<PropertyPOJO> allProperties;
    public static TextField amount;
    public static int updatableTransactionID;
    public static boolean updateFormClicked = false;
    public static Text prompt;
    public static PropertyTable propertyTable = new PropertyTable();
    public static ClientTable clientTable = new ClientTable();

    public RevenueForm(){

        allClients = new ComboBox<>();
        allClients.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        allProperties = new ComboBox<>();
        allProperties.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        amount = new TextField();
        amount.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());


        Text title = new Text("Manage Transactions");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        VBox layout = new VBox(25);
        HBox buttonBox = new HBox(20);

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

        buttonBox.getChildren().addAll(newTransactionButton, updateTransaction);
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        newTransactionButton.setOnAction(e -> {
            layout.getChildren().clear();
            layout.getChildren().addAll(title, buttonBox, transactionForm(), addTransaction);
            updateFormClicked = false;

            prompt.setText("");
            Animations.translate(addTransaction, 1000);
        });

        updateTransaction.setOnAction(e -> {
            layout.getChildren().clear();
            layout.getChildren().addAll(title, buttonBox, transactionForm(), updateTransactionButton);
            updateFormClicked = true;

            prompt.setText("Click On A Transaction To Update");
            Animations.translate(updateTransactionButton, 1000);
        });


        addTransaction.setOnAction(e ->{

            if(!amount.getText().isEmpty() && !allClients.getEditor().getText().isEmpty() && allProperties.getSelectionModel().getSelectedItem() != null ){

                double amountEntered = Double.parseDouble(amount.getText().trim());

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
            }else{
                prompt.setText("Can't Add New Transaction, Try Again, Fields Cannot Be Empty!");
                prompt.setStyle("-fx-fill: #860a0a; -fx-font-size: 14px; -fx-font-style: italic;");
            }

        });


        updateTransactionButton.setOnAction(e ->{

            if(!amount.getText().isEmpty() && !allClients.getEditor().getText().isEmpty() && allProperties.getSelectionModel().getSelectedItem() != null ){

                double amountEntered = Double.parseDouble(amount.getText().trim());

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



        layout.getChildren().addAll(title, buttonBox, transactionForm(), addTransaction);
        this.setCenter(layout);

        layout.setStyle("-fx-padding: 30px 50px 30px 50px");

        Animations.translate(buttonBox, 600);
        Animations.translate(addTransaction, 1200);
    }

    public static VBox transactionForm(){

        allClients.getSelectionModel().clearSelection();
        allProperties.getSelectionModel().clearSelection();
        allProperties.getEditor().clear();
        allClients.getEditor().clear();
        amount.setText("");

        VBox containerBox = new VBox();

        VBox amountBox = new VBox(6);
        VBox clientBox = new VBox(6);
        VBox propertyBox = new VBox(6);
        VBox formBox = new VBox(15);

        prompt = new Text();
        prompt.setStyle("-fx-fill: #202469; -fx-font-size: 14px; -fx-font-style: italic;");

        Label amountLabel = new Label("Amount");
        amount.setMaxWidth(250);
        amount.setPromptText("Enter Transaction Amount");
        amount.setStyle("-fx-border-color: #202469; -fx-border-width: 1px; -fx-border-radius: 5px;" +
                " -fx-font-size: 14px;");

        Label clientLabel = new Label("Client");
        allClients.setItems(FXCollections.observableArrayList(clientTable.getAllClient()));
        allClients.setEditable(true);
        allClients.setPromptText("Search For Client");
        allClients.setPrefWidth(250);


        Label propertyLabel = new Label("Property");
        allProperties.setItems(FXCollections.observableArrayList(propertyTable.getAllPropertyRaw()));
        allProperties.setPromptText("Select A Property");
        allProperties.setPrefWidth(250);


        allClients.setOnKeyReleased(event -> {
            String selectedClient = allClients.getEditor().getText().toLowerCase();
            List<ClientPOJO> searchedItems = clientTable.getAllClient().stream()
                    .filter(first_name -> first_name.getFirst_name().toLowerCase().contains(selectedClient))
                    .collect(Collectors.toList());
            allClients.getItems().setAll(searchedItems);

            allClients.show();
        });

        amountBox.getChildren().addAll(amountLabel, amount);
        clientBox.getChildren().addAll(clientLabel, allClients);
        propertyBox.getChildren().addAll(propertyLabel, allProperties);

        formBox.getChildren().addAll(prompt, amountBox, clientBox, propertyBox);

        containerBox.getChildren().addAll(formBox);
        containerBox.setAlignment(Pos.TOP_LEFT);

         animate(amountBox, clientBox, propertyBox);

        return containerBox;
    }

    public static void getTransactionDetails(TransactionPOJORefined transaction){

        if (updateFormClicked){

            amount.setText(transaction.getAmount() + "");

            ClientPOJO updatableClient = null;
            PropertyPOJO updatableProperty = null;


            for (ClientPOJO client : clientTable.getAllClient()){
                String name = client.getFirst_name() + " " + client.getLast_name();
                String selectedName = transaction.getClient_id();

                if(name.equals(selectedName)){
                    updatableClient = client;
                }
            }

            for (PropertyPOJO property : propertyTable.getAllPropertyRaw()){

                String name = property.getName();
                String selectedName = transaction.getProperty_id();

                if(name.equals(selectedName)){
                    updatableProperty = property;
                }
            }

            allClients.getSelectionModel().select(updatableClient);
            allProperties.getSelectionModel().select(updatableProperty);

        }
    }

    public static void animate(Node revenue, Node transaction, Node best){

        Animations.translate(revenue, 600);
        Animations.translate(transaction, 750);
        Animations.translate(best, 900);

    }

    public static int getUpdatableTransactionID() {
        return updatableTransactionID;
    }

    public static void setUpdatableTransactionID(int updatableTransactionID) {
        RevenueForm.updatableTransactionID = updatableTransactionID;
    }
}
