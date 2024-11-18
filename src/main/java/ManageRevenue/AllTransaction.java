package ManageRevenue;

import Overview.TopClients;
import TableQuery.TransactionTable;
import com.example.propertypro.Pojo.TransactionPOJO;
import com.example.propertypro.Pojo.TransactionPOJORefined;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


import java.security.PublicKey;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

public class AllTransaction extends BorderPane {


    public AllTransaction(){

        VBox transactionBox = new VBox(25);
        HBox calendarBox = new HBox(10);
        HBox buttonBox = new HBox();
        HBox titleBox = new HBox();
        HBox deleteBox = new HBox();

        Button searchCalendar = new Button("Search");
        searchCalendar.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        Button allTransactionButton = new Button("All Transactions");
        allTransactionButton.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        Button deleteButton = new Button("Delete Transaction");
        deleteButton.getStyleClass().add("deleteButton");
        deleteButton.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());


        Text title = new Text();
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");


        TransactionTable transactionTable = new TransactionTable();

        DatePicker calendarStart = new DatePicker();
        calendarStart.setPromptText("Start Date");
        calendarStart.setPrefWidth(120);
        calendarStart.getStylesheets().add(getClass().getResource("/calendar.css").toExternalForm());

        DatePicker calendarEnd = new DatePicker();
        calendarEnd.setPromptText("End Date");
        calendarEnd.setPrefWidth(120);
        calendarEnd.getStylesheets().add(getClass().getResource("/calendar.css").toExternalForm());


        //ArrayList<TransactionPOJORefined> transactions = transactionTable.getAllTransactions();
        title.setText(transactionTable.getAllTransactions().size() + " Transaction(s)");

        TableView allTransactions = new TableView();
        allTransactions.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        TableColumn<TransactionPOJORefined, String> amount = new TableColumn<>("Amount");
        amount.setCellValueFactory(e-> new SimpleStringProperty("$" + String.format("%,.2f", e.getValue().getAmount())));

        TableColumn<TransactionPOJORefined, String> client = new TableColumn<>("Client");
        client.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getClient_id()));

        TableColumn<TransactionPOJORefined, String> property = new TableColumn<>("Property");
        property.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getProperty_id()));

        TableColumn<TransactionPOJORefined, String> date = new TableColumn<>("Date");
        date.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getTimestamp().toLocalDateTime().toLocalDate() + ""));

        TableColumn<TransactionPOJORefined, String> id = new TableColumn<>("ID");
        id.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getId() + ""));


        allTransactions.getColumns().addAll(date, id, client, property, amount);
        allTransactions.getItems().addAll(transactionTable.getAllTransactions());
        allTransactions.getStylesheets().add(getClass().getResource("/tableView.css").toExternalForm());


        Label emptyLabel = new Label("No transactions for the selected date");
        allTransactions.setPlaceholder(emptyLabel);

        searchCalendar.setOnAction(e -> {

            getCalendarTransaction(calendarStart, calendarEnd, transactionTable, allTransactions, title);

        });

        allTransactionButton.setOnAction(e -> {

            allTransactions.getItems().clear();
            allTransactions.getItems().addAll(transactionTable.getAllTransactions());
            title.setText(transactionTable.getAllTransactions().size() + " Transaction(s)");

        });

        deleteButton.setOnAction(e -> {

            TransactionPOJORefined selectedItem = (TransactionPOJORefined) allTransactions.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {

                transactionTable.deleteTransaction(selectedItem.getId());

            } else {
                System.out.println("Please Select A Transaction To Delete.");
            }

            allTransactions.getItems().clear();
            allTransactions.getItems().addAll(transactionTable.getAllTransactions());
            title.setText(transactionTable.getAllTransactions().size() + " Transaction(s)");
        });

        deleteBox.getChildren().add(deleteButton);
        deleteBox.setAlignment(Pos.CENTER_RIGHT);

        titleBox.getChildren().addAll(title, calendarBox);
        HBox.setHgrow(title, Priority.ALWAYS);
        HBox.setHgrow(calendarBox, Priority.ALWAYS);

        calendarBox.getChildren().addAll(calendarStart, calendarEnd, searchCalendar);
        calendarBox.setAlignment(Pos.CENTER_RIGHT);

        buttonBox.getChildren().addAll(allTransactionButton, deleteBox);
        HBox.setHgrow(allTransactionButton, Priority.ALWAYS);
        HBox.setHgrow(deleteBox, Priority.ALWAYS);

        transactionBox.getChildren().addAll(titleBox, buttonBox, allTransactions);
        transactionBox.setAlignment(Pos.CENTER_LEFT);
        transactionBox.setStyle("-fx-padding: 30px 50px 30px 50px");


        this.setCenter(transactionBox);
    }

    public void getCalendarTransaction(DatePicker calendarStart, DatePicker calendarEnd, TransactionTable transactionTable, TableView allTransactions, Text title){

        LocalDate startDate = calendarStart.getValue();
        LocalDate endDate = calendarEnd.getValue();

        Timestamp startOfDay = null;
        Timestamp endOfDay = null;

        if (startDate != null && endDate != null) {
            startOfDay = Timestamp.valueOf(startDate.atStartOfDay());
            endOfDay = Timestamp.valueOf(endDate.plusDays(1).atStartOfDay());
        }

        else{
            LocalDate date = (startDate != null) ? startDate : endDate;

            startOfDay = Timestamp.valueOf(date.atStartOfDay());
            endOfDay = Timestamp.valueOf(date.plusDays(1).atStartOfDay());
        }

        ArrayList<TransactionPOJORefined> calendarTransactions = transactionTable.getTransactionByDate(startOfDay, endOfDay);


        allTransactions.getItems().clear();
        allTransactions.getItems().addAll(calendarTransactions);

        title.setText(calendarTransactions.size() + " Transaction(s)");

    }
}
