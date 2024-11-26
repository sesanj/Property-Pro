package ManageRevenue;

import Database.Database;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormatSymbols;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;

import static Database.DatabaseTableConstants.*;

public class RevenueChart extends BorderPane {

    public static Database db = Database.getNewDatabase();
    public RevenueChart(){

        VBox content = new VBox(25);
        HBox titleBox = new HBox();
        HBox dropDownBox = new HBox(10);

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


        CategoryAxis xAxis = new CategoryAxis();

        NumberAxis yAxis = new NumberAxis();

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Monthly Data For " + getAllYears().getLast());

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        XYChart.Series<String, Number> daySeries = new XYChart.Series<>();

        getMonthlyTransactions(getAllYears().getLast(), series);


        allYears.setOnAction(e -> {

            Integer selectedYear = allYears.getValue();

            series.getData().clear();
            getMonthlyTransactions(selectedYear, series);

            lineChart.getData().clear();

            getTooltip(series);
            lineChart.getData().add(series);
            lineChart.setTitle("Monthly Data For " + selectedYear);

            title.setText(selectedYear + " Revenue Chart");

            AllTransaction.getTransactionsByYear(selectedYear);

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

                daySeries.getData().clear();
                getDailyTransactions(selectedYear, selectedMonth, daySeries);

                lineChart.getData().clear();

                getTooltip(daySeries);
                lineChart.getData().add(daySeries);

                AllTransaction.getTransactionsByMonth(selectedMonth, selectedYear);

                title.setText(selectedMonth + ", " +selectedYear + " Revenue Chart");
                lineChart.setTitle("Daily Data For " + selectedMonth + ", " + selectedYear);
            }

        });

        getTooltip(series);



        dropDownBox.getChildren().addAll(allYears, allMonths);
        dropDownBox.setAlignment(Pos.CENTER_RIGHT);

        titleBox.getChildren().addAll(title, dropDownBox);
        HBox.setHgrow(dropDownBox, Priority.ALWAYS);

        lineChart.getData().add(series);
        lineChart.getStylesheets().add(getClass().getResource("/cashFlow.css").toExternalForm());
        lineChart.setLegendVisible(false);

        content.getChildren().addAll(titleBox, lineChart);
        content.setStyle("-fx-padding: 40px 50px 10px 50px");

        this.setCenter(content);
    }

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
