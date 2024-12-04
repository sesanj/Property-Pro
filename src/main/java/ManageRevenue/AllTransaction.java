package ManageRevenue;

import Animations.Animations;
import Database.Database;
import TableQuery.TransactionTable;
import com.example.propertypro.Pojo.TransactionPOJORefined;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;
import static Database.DatabaseTableConstants.TRANSACTION_TIMESTAMP;

/**
 * A class to manage and display all transactions in a table view with support for filtering by date ranges.
 * This class provides functionality to view transactions by year, month, and delete selected transactions.
 */
public class AllTransaction extends BorderPane {

    public static Text title;
    public static TableView allTransactions;
    private static TransactionTable transactionTable;
    private static Database db = Database.getNewDatabase();


    /**
     * Constructor for AllTransaction. Initializes the UI components, including the transaction table,
     * calendar controls, and buttons for filtering transactions by year/month and deleting selected transactions.
     */
    public AllTransaction(){

        // VBox for organizing the transaction-related UI elements with a 25px gap
        VBox transactionBox = new VBox(25);

        // HBox for the calendar section and buttons, arranged with 10px gap
        HBox calendarBox = new HBox(10);
        HBox buttonBox = new HBox();
        HBox titleBox = new HBox();
        HBox deleteBox = new HBox();

        // Button to trigger calendar-based search
        Button searchCalendar = new Button("Search");
        searchCalendar.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        // Button to view all transactions
        Button allTransactionButton = new Button("All Transactions");
        allTransactionButton.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        // Button to delete selected transaction
        Button deleteButton = new Button("Delete Transaction");
        deleteButton.getStyleClass().add("deleteButton");
        deleteButton.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        // Text element for displaying the transaction title
        title = new Text();
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        // Custom TableView for displaying transaction data
        transactionTable = new TransactionTable();

        // DatePickers for selecting the start and end dates
        DatePicker calendarStart = new DatePicker();
        calendarStart.setPromptText("Start Date");
        calendarStart.setPrefWidth(120);
        calendarStart.getStylesheets().add(getClass().getResource("/calendar.css").toExternalForm());

        DatePicker calendarEnd = new DatePicker();
        calendarEnd.setPromptText("End Date");
        calendarEnd.setPrefWidth(120);
        calendarEnd.getStylesheets().add(getClass().getResource("/calendar.css").toExternalForm());

        // TableView to display the list of all transactions
        allTransactions = new TableView();
        allTransactions.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Table columns for transaction attributes (Amount, Client, Property, Date, ID)
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

        // Adding all columns to the table
        allTransactions.getColumns().addAll(date, id, client, property, amount);
        getTransactionsByYear(RevenueChart.getAllYears().getLast());

        // Styling the table view
        allTransactions.getStylesheets().add(getClass().getResource("/tableView.css").toExternalForm());

        // Setting a placeholder for when no transactions are present
        Label emptyLabel = new Label("No transactions for the selected date");
        allTransactions.setPlaceholder(emptyLabel);

        // Event handler for the search button, which filters transactions based on the selected date range
        searchCalendar.setOnAction(e -> {
            getCalendarTransaction(calendarStart, calendarEnd);
        });

        // Event handler for the "All Transactions" button, which loads all transactions into the table
        allTransactionButton.setOnAction(e -> {
            allTransactions.getItems().clear();
            allTransactions.getItems().addAll(transactionTable.getAllTransactions());
            title.setText(transactionTable.getAllTransactions().size() + " Transaction(s)");
        });

        // Event handler for the "Delete Transaction" button, which removes the selected transaction from the table and updates the revenue
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

        // Setting alignment and adding delete button to the UI
        deleteBox.getChildren().add(deleteButton);
        deleteBox.setAlignment(Pos.CENTER_RIGHT);

        // Setting up the title and calendar UI elements
        titleBox.getChildren().addAll(title, calendarBox);
        HBox.setHgrow(title, Priority.ALWAYS);
        HBox.setHgrow(calendarBox, Priority.ALWAYS);

        // Adding the date pickers and search button to the calendar section
        calendarBox.getChildren().addAll(calendarStart, calendarEnd, searchCalendar);
        calendarBox.setAlignment(Pos.CENTER_RIGHT);

        // Adding all buttons to the button section
        buttonBox.getChildren().addAll(allTransactionButton, deleteBox);
        HBox.setHgrow(allTransactionButton, Priority.ALWAYS);
        HBox.setHgrow(deleteBox, Priority.ALWAYS);

        // Adding all sections to the main layout
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

        // Setting the layout of the center part of the screen
        this.setCenter(transactionBox);

        // Animating the buttons and table view with custom animations
        Animations.translate(buttonBox, 600);
        Animations.translate(allTransactions, 800);
    }

    /**
     * Retrieves transactions based on the selected calendar date range and updates the table view.
     *
     * @param calendarStart The start date of the range.
     * @param calendarEnd The end date of the range.
     */
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

    /**
     * Retrieves and displays transactions for a given year.
     *
     * @param selectedYear The year for which to retrieve transactions.
     */
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

    /**
     * Retrieves and displays transactions for a specified month and year.
     * The method executes an SQL query to fetch transaction data for the given month and year,
     * and then updates the table view with the retrieved transactions.
     *
     * @param selectedMonth The name of the month (e.g., "January", "February").
     * @param selectedYear The year for which to retrieve transactions.
     */
    public static void getTransactionsByMonth(String selectedMonth, int selectedYear){

        ArrayList<TransactionPOJORefined> monthlyTransactions = new ArrayList<>();

        // SQL query to fetch transaction data for the selected month and year
        String query = "SELECT t." + TRANSACTION_ID + ", t." + TRANSACTION_AMOUNT + ", c." + CLIENT_FIRST_NAME + ", c." + CLIENT_LAST_NAME + ", p." + PROPERTY_NAME +
                ", t." + TRANSACTION_TIMESTAMP +
                " FROM " + TRANSACTION_TABLE + " t " +
                "JOIN " + CLIENT_TABLE + " c ON t." + TRANSACTION_CLIENT_ID + " = c." + CLIENT_ID +
                " JOIN " + PROPERTY_TABLE + " p ON t." + TRANSACTION_PROPERTY_ID + " = p." + PROPERTY_ID +
                " WHERE EXTRACT(YEAR FROM " + TRANSACTION_TIMESTAMP + ") = " + selectedYear + " " +
                "AND TRIM(TO_CHAR(" + TRANSACTION_TIMESTAMP + ", 'Month')) = '" + selectedMonth + "' " +
                "ORDER BY " + TRANSACTION_TIMESTAMP + " DESC";

        try{
            // Execute the query and process the result set
            Statement statement = db.getConnection().createStatement();
            ResultSet TransactionData = statement.executeQuery(query);

            // Loop through the result set and add each transaction to the list
            while(TransactionData.next()){

                monthlyTransactions.add(new TransactionPOJORefined(TransactionData.getInt(TRANSACTION_ID),TransactionData.getString(CLIENT_FIRST_NAME) + " " + TransactionData.getString(CLIENT_LAST_NAME),TransactionData.getString(PROPERTY_NAME), TransactionData.getDouble(TRANSACTION_AMOUNT), TransactionData.getTimestamp(TRANSACTION_TIMESTAMP)));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        // Clear the previous items and add the newly fetched transactions
        allTransactions.getItems().clear();
        allTransactions.getItems().addAll(monthlyTransactions);

        // Update the title to reflect the number of transactions
        title.setText(monthlyTransactions.size() + " Transaction(s)");

        // Update total transaction count and display
        RevenueData.setTotalTransactions(String.format("%,d", monthlyTransactions.size()), monthlyTransactions.size());
    }
}
