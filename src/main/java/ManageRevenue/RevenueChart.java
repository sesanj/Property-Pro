package ManageRevenue;

import Animations.Animations;
import Database.Database;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;

import static Database.DatabaseTableConstants.*;

/**
 * RevenueChart class provides functionality to display a revenue chart for monthly and daily data in the application.
 * It fetches and displays transaction data from the database, allows users to select a year and month, and displays
 * a corresponding chart.
 */
public class RevenueChart extends BorderPane {

    public static Database db = Database.getNewDatabase();

    /**
     * Constructor to initialize the RevenueChart layout and populate it with relevant data.
     */
    public RevenueChart(){

        VBox content = new VBox(25);
        HBox titleBox = new HBox();
        HBox dropDownBox = new HBox(10);

/*
  Creates and initializes combo boxes for selecting a year and a month,
  along with the title and the chart for displaying revenue data.

  The revenue data will be displayed on a line chart that updates dynamically
  based on the user's selection of year and month.
 */
        ComboBox<Integer> allYears = new ComboBox<>();
        allYears.getItems().addAll(getAllYears());
        allYears.setPromptText("Select A Year");
        allYears.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        ComboBox<String> allMonths = new ComboBox<>();
        allMonths.getItems().addAll("Select A Month", "January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December");
        allMonths.getSelectionModel().select(0);
        allMonths.setPromptText("Select A Month");
        allMonths.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        Text title = new Text(getAllYears().getLast() + " Revenue Chart");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

/*
  Creates the axes for the line chart.
  The x-axis represents the months or days,
  and the y-axis represents the revenue amounts.
 */
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Monthly Data For " + getAllYears().getLast());

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        XYChart.Series<String, Number> daySeries = new XYChart.Series<>();

/*
  Initializes the chart by fetching and displaying the monthly revenue data
  for the last available year.
 */
        getMonthlyTransactions(getAllYears().getLast(), series);

        allYears.setOnAction(e -> {

            Integer selectedYear = allYears.getValue();

            // Clears previous chart data and fetches the new data based on selected year
            series.getData().clear();
            getMonthlyTransactions(selectedYear, series);

            lineChart.getData().clear();

            getTooltip(series);
            lineChart.getData().add(series);
            lineChart.setTitle("Monthly Data For " + selectedYear);

            title.setText(selectedYear + " Revenue Chart");

            AllTransaction.getTransactionsByYear(selectedYear);

            // Resets the month selection when the year is changed
            allMonths.getSelectionModel().clearSelection();
            allMonths.getSelectionModel().select(0);
        });

        allMonths.setOnAction(e -> {

            if(allMonths.getValue() == null || allMonths.getValue().equals("Select A Month")){
                return;
            }

            String selectedMonth = allMonths.getValue();
            Integer selectedYear = allYears.getValue();

            if(selectedYear != null){

                // Clears previous daily data and fetches new daily data based on the selected month and year
                daySeries.getData().clear();
                getDailyTransactions(selectedYear, selectedMonth, daySeries);

                lineChart.getData().clear();

                getTooltip(daySeries);
                lineChart.getData().add(daySeries);

                AllTransaction.getTransactionsByMonth(selectedMonth, selectedYear);

                title.setText(selectedMonth + ", " + selectedYear + " Revenue Chart");
                lineChart.setTitle("Daily Data For " + selectedMonth + ", " + selectedYear);
            }

        });

//Adds tooltips to the chart data to display detailed information on each data point.

        getTooltip(series);

  //Adds the combo boxes and title to the layout, and sets the style for the content.

        dropDownBox.getChildren().addAll(allYears, allMonths);
        dropDownBox.setAlignment(Pos.CENTER_RIGHT);

        titleBox.getChildren().addAll(title, dropDownBox);
        HBox.setHgrow(dropDownBox, Priority.ALWAYS);

        lineChart.getData().add(series);
        lineChart.getStylesheets().add(getClass().getResource("/cashFlow.css").toExternalForm());
        lineChart.setLegendVisible(false);

        content.getChildren().addAll(titleBox, lineChart);
        content.setStyle("-fx-padding: 50px 50px 10px 50px");

/*
  Sets the content of the current layout to include the title and chart,
  with animation applied to the chart.
 */
        this.setCenter(content);
        Animations.translate(lineChart, 800);

    }

    /**
     * Retrieves a list of all distinct years from the transaction records in the database.
     *
     * @return an ArrayList of integers representing the available years for the revenue chart.
     */
    public static ArrayList<Integer> getAllYears(){

        ArrayList<Integer> years = new ArrayList<>();

        String query = "SELECT DISTINCT EXTRACT(YEAR FROM " + TRANSACTION_TIMESTAMP + ") AS year " +
                "FROM " + TRANSACTION_TABLE + " " +
                "ORDER BY year";
        try{
            Statement statement = db.getConnection().createStatement();
            ResultSet data = statement.executeQuery(query);

            while (data.next()){
                years.add(data.getInt("year"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return years;
    }

    /**
     * Retrieves and processes monthly transaction data for a given year to populate the revenue chart.
     *
     * @param year the year for which the monthly transaction data is to be fetched.
     * @param series the series to be updated with the monthly transaction data.
     */
    public void getMonthlyTransactions(int year, XYChart.Series<String, Number> series){

        ArrayList<String> month = new ArrayList<>();
        ArrayList<Double> amount = new ArrayList<>();
        double totalRevenue = 0;


        String query = "SELECT EXTRACT(MONTH FROM " + TRANSACTION_TIMESTAMP + ") AS month, " +
                "SUM(" + TRANSACTION_AMOUNT + ") AS amount " +
                "FROM " + TRANSACTION_TABLE + " " +
                "WHERE EXTRACT(YEAR FROM " + TRANSACTION_TIMESTAMP + ") = " + year + " " +
                "GROUP BY month " +
                "ORDER BY MONTH(" + TRANSACTION_TIMESTAMP + ")";

        try{

            Statement statement = db.getConnection().createStatement();
            ResultSet data = statement.executeQuery(query);

            while(data.next()){

                String monthName = new DateFormatSymbols().getShortMonths()[data.getInt("month") - 1];

                month.add(monthName);
                amount.add(data.getDouble("amount"));

                totalRevenue += data.getDouble("amount");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        RevenueData.getRevenue(totalRevenue);

        for (int i = 0; i < month.size(); i++) {
            series.getData().add(new XYChart.Data<>(month.get(i), amount.get(i)));
        }

        double highestRevenue = Collections.max(amount);
        double lowestRevenue = Collections.min(amount);

        String bestMonth = month.get(amount.indexOf(highestRevenue));
        String worstMonth = month.get(amount.indexOf(lowestRevenue));

        RevenueData.getBestAndWorst(highestRevenue, lowestRevenue, bestMonth, worstMonth);

    }

    /**
     * Retrieves and processes daily transaction data for a given year and month to populate the revenue chart.
     *
     * @param year the year for which the daily transaction data is to be fetched.
     * @param month the month for which the daily transaction data is to be fetched.
     * @param series the series to be updated with the daily transaction data.
     */
    public void getDailyTransactions(int year, String month, XYChart.Series<String, Number> series){

        ArrayList<String> day = new ArrayList<>();
        ArrayList<Double> amount = new ArrayList<>();
        double totalRevenue = 0;

        String query = "SELECT EXTRACT(DAY FROM " + TRANSACTION_TIMESTAMP + ") AS day, " +
                "SUM(" + TRANSACTION_AMOUNT + ") AS amount " +
                "FROM " + TRANSACTION_TABLE + " " +
                "WHERE EXTRACT(YEAR FROM " + TRANSACTION_TIMESTAMP + ") = " + year + " " +
                "AND TRIM(TO_CHAR(" + TRANSACTION_TIMESTAMP + ", 'Month')) = '" + month + "' " +
                "GROUP BY day " +
                "ORDER BY day";

        try{

            Statement statement = db.getConnection().createStatement();
            ResultSet data = statement.executeQuery(query);

            while(data.next()){

                day.add(data.getString("day"));
                amount.add(data.getDouble("amount"));

                totalRevenue += data.getDouble("amount");

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        RevenueData.getRevenue(totalRevenue);


        for (int i = 0; i < day.size(); i++) {
            series.getData().add(new XYChart.Data<>(day.get(i), amount.get(i)));
        }

        double highestRevenue = Collections.max(amount);
        double lowestRevenue = Collections.min(amount);

        String bestDay = day.get(amount.indexOf(highestRevenue));
        String worstDay = day.get(amount.indexOf(lowestRevenue));

        RevenueData.getBestAndWorst(highestRevenue, lowestRevenue, bestDay, worstDay);
    }


    /**
     * Adds tooltips to each data point in the chart, displaying the revenue and the month/day associated with that data point.
     *
     * @param series the series containing the data points for which the tooltips are to be added.
     */
    public void getTooltip(XYChart.Series<String, Number> series){

        // Add tooltips for each data point
        for (XYChart.Data<String, Number> data : series.getData()) {
            // Add a listener to the node property
            data.nodeProperty().addListener((obs, oldNode, newNode) -> {
                if (newNode != null) {
                    // Create and install the tooltip on the new node
                    Tooltip tooltip = new Tooltip("Revenue: $" + data.getYValue() + "\nMonth: " + data.getXValue());
                    Tooltip.install(newNode, tooltip);

                    // Optional: Change color on hover
                    newNode.setOnMouseEntered(event -> newNode.setStyle("-fx-bar-fill: red;"));
                    newNode.setOnMouseExited(event -> newNode.setStyle(""));
                }
            });
        }
    }
}
