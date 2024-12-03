package ManageRevenue;

import Animations.Animations;
import Database.Database;
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
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;
import static Database.DatabaseTableConstants.TRANSACTION_TIMESTAMP;

public class AllTransaction extends BorderPane {

    public static Text title;
    public static TableView allTransactions;
    private static TransactionTable transactionTable;
    private static Database db = Database.getNewDatabase();


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


        title = new Text();
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");


        transactionTable = new TransactionTable();

        DatePicker calendarStart = new DatePicker();
        calendarStart.setPromptText("Start Date");
        calendarStart.setPrefWidth(120);
        calendarStart.getStylesheets().add(getClass().getResource("/calendar.css").toExternalForm());

        DatePicker calendarEnd = new DatePicker();
        calendarEnd.setPromptText("End Date");
        calendarEnd.setPrefWidth(120);
        calendarEnd.getStylesheets().add(getClass().getResource("/calendar.css").toExternalForm());


        allTransactions = new TableView();
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
        getTransactionsByYear(RevenueChart.getAllYears().getLast());

        //allTransactions.getItems().addAll(getTransactionsByYear(RevenueChart.getAllYears().getLast()));
        allTransactions.getStylesheets().add(getClass().getResource("/tableView.css").toExternalForm());


        Label emptyLabel = new Label("No transactions for the selected date");
        allTransactions.setPlaceholder(emptyLabel);

        searchCalendar.setOnAction(e -> {

            getCalendarTransaction(calendarStart, calendarEnd);

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
                allTransactions.getItems().remove(selectedItem);
                allTransactions.refresh();

                title.setText(allTransactions.getItems().size() + " Transaction(s)");

                RevenueData.updateRevenue(selectedItem.getAmount());
                RevenueData.updateTotalTransaction();

            } else {
                System.out.println("Please Select A Transaction To Delete.");
            }
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


        allTransactions.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{

            if(newValue != null){

                TransactionPOJORefined transaction = (TransactionPOJORefined) allTransactions.getSelectionModel().getSelectedItem();
                RevenueForm.getTransactionDetails(transaction);

                RevenueForm.setUpdatableTransactionID(((TransactionPOJORefined) allTransactions.getSelectionModel().getSelectedItem()).getId());
            }
        });


        this.setCenter(transactionBox);

        Animations.translate(buttonBox, 600);
        Animations.translate(allTransactions, 800);
    }

    public void getCalendarTransaction(DatePicker calendarStart, DatePicker calendarEnd){

        LocalDate startDate = calendarStart.getValue();
        LocalDate endDate = calendarEnd.getValue();

        Timestamp startOfDay;
        Timestamp endOfDay;

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

    public static void getTransactionsByYear(int selectedYear){

        ArrayList<TransactionPOJORefined> yearlyTransactions = new ArrayList<>();

        String query = "SELECT t." + TRANSACTION_ID + ", t." + TRANSACTION_AMOUNT + ", c." + CLIENT_FIRST_NAME + ", c." + CLIENT_LAST_NAME + ", p." + PROPERTY_NAME +
                ", t." + TRANSACTION_TIMESTAMP +
                " FROM " + TRANSACTION_TABLE + " t " +
                "JOIN " + CLIENT_TABLE + " c ON t." + TRANSACTION_CLIENT_ID + " = c." + CLIENT_ID +
                " JOIN " + PROPERTY_TABLE + " p ON t." + TRANSACTION_PROPERTY_ID + " = p." + PROPERTY_ID +
                " WHERE EXTRACT(YEAR FROM " + TRANSACTION_TIMESTAMP + ") = " + selectedYear +
                " ORDER BY " + TRANSACTION_TIMESTAMP + " DESC";

        try{

            Statement statement = db.getConnection().createStatement();
            ResultSet TransactionData = statement.executeQuery(query);

            while(TransactionData.next()){

                yearlyTransactions.add(new TransactionPOJORefined(TransactionData.getInt(TRANSACTION_ID),TransactionData.getString(CLIENT_FIRST_NAME) + " " + TransactionData.getString(CLIENT_LAST_NAME),TransactionData.getString(PROPERTY_NAME), TransactionData.getDouble(TRANSACTION_AMOUNT), TransactionData.getTimestamp(TRANSACTION_TIMESTAMP)));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        allTransactions.getItems().clear();
        allTransactions.getItems().addAll(yearlyTransactions);

        title.setText(yearlyTransactions.size() + " Transaction(s)");

        RevenueData.setTotalTransactions(String.format("%,d", yearlyTransactions.size()), yearlyTransactions.size());

    }

    public static void getTransactionsByMonth(String selectedMonth, int selectedYear){

        ArrayList<TransactionPOJORefined> monthlyTransactions = new ArrayList<>();

        String query = "SELECT t." + TRANSACTION_ID + ", t." + TRANSACTION_AMOUNT + ", c." + CLIENT_FIRST_NAME + ", c." + CLIENT_LAST_NAME + ", p." + PROPERTY_NAME +
                ", t." + TRANSACTION_TIMESTAMP +
                " FROM " + TRANSACTION_TABLE + " t " +
                "JOIN " + CLIENT_TABLE + " c ON t." + TRANSACTION_CLIENT_ID + " = c." + CLIENT_ID +
                " JOIN " + PROPERTY_TABLE + " p ON t." + TRANSACTION_PROPERTY_ID + " = p." + PROPERTY_ID +
                " WHERE EXTRACT(YEAR FROM " + TRANSACTION_TIMESTAMP + ") = " + selectedYear + " " +
                "AND TRIM(TO_CHAR(" + TRANSACTION_TIMESTAMP + ", 'Month')) = '" + selectedMonth + "' " +
                "ORDER BY " + TRANSACTION_TIMESTAMP + " DESC";

        try{

            Statement statement = db.getConnection().createStatement();
            ResultSet TransactionData = statement.executeQuery(query);

            while(TransactionData.next()){

                monthlyTransactions.add(new TransactionPOJORefined(TransactionData.getInt(TRANSACTION_ID),TransactionData.getString(CLIENT_FIRST_NAME) + " " + TransactionData.getString(CLIENT_LAST_NAME),TransactionData.getString(PROPERTY_NAME), TransactionData.getDouble(TRANSACTION_AMOUNT), TransactionData.getTimestamp(TRANSACTION_TIMESTAMP)));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        allTransactions.getItems().clear();
        allTransactions.getItems().addAll(monthlyTransactions);

        title.setText(monthlyTransactions.size() + " Transaction(s)");

        RevenueData.setTotalTransactions(String.format("%,d", monthlyTransactions.size()), monthlyTransactions.size());

    }


}
