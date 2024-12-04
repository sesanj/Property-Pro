package Overview.Charts;

import Database.Database;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;

/**
 * Represents a bar chart that displays monthly revenue data for the last 12 months.
 * The chart is populated with data fetched from the database and includes tooltips
 * for each bar showing revenue details and the corresponding date.
 */
public class BarChart extends BorderPane {

    /**
     * Constructs a BarChart and initializes the chart with revenue data from the database.
     * The data represents the total revenue for each of the last 12 months.
     * The chart displays the months on the x-axis and the revenue (in dollars) on the y-axis.
     * Tooltips are added to each bar for displaying detailed information when hovered over.
     *
     * @param db The Database object used to fetch the transaction data.
     */
    public BarChart(Database db) {

        // Set up the X and Y axes for the bar chart
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Format the Y-axis labels as currency
        yAxis.setTickLabelFormatter(new StringConverter<>() {
            @Override
            public String toString(Number object) {
                return String.format("$%,.0f", object);
            }

            @Override
            public Number fromString(String string) {
                return Double.parseDouble(string.replace("$", "").replace(",", ""));
            }
        });

        // Create the bar chart with the specified axes
        javafx.scene.chart.BarChart<String, Number> barChart = new javafx.scene.chart.BarChart<>(xAxis, yAxis);
        barChart.setTitle("Monthly Revenue for Last 12 Months");

        // Prepare the series to hold the data for the bar chart
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        // SQL query to fetch revenue data from the last 12 months
        String query = "SELECT YEAR(" + TRANSACTION_TIMESTAMP + ") AS year, " +
                "MONTH(" + TRANSACTION_TIMESTAMP + ") AS month, " +
                "SUM(" + TRANSACTION_AMOUNT + ") AS monthly_revenue " +
                "FROM " + TRANSACTION_TABLE + " " +
                "WHERE " + TRANSACTION_TIMESTAMP + " >= DATE_SUB(CURDATE(), INTERVAL 10 MONTH) " +
                "GROUP BY YEAR(" + TRANSACTION_TIMESTAMP + "), MONTH(" + TRANSACTION_TIMESTAMP + ") " +
                "ORDER BY YEAR(" + TRANSACTION_TIMESTAMP + "), MONTH(" + TRANSACTION_TIMESTAMP + ")";

        ArrayList<String> month = new ArrayList<>();
        ArrayList<Double> revenue = new ArrayList<>();

        // Fetch data from the database
        try {
            Statement transactions = db.getConnection().createStatement();
            ResultSet data = transactions.executeQuery(query);

            while (data.next()) {
                month.add(data.getString("year") + "-" + data.getString("month"));
                revenue.add(data.getDouble("monthly_revenue"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add the fetched data to the series
        for (int i = 0; i < month.size(); i++) {
            series.getData().add(new XYChart.Data<>(month.get(i), revenue.get(i)));
        }

        // Add tooltips for each data point
        for (XYChart.Data<String, Number> data : series.getData()) {
            data.nodeProperty().addListener((obs, oldNode, newNode) -> {
                if (newNode != null) {
                    // Create and install a tooltip on the new node
                    Tooltip tooltip = new Tooltip("Revenue: $" + data.getYValue() + "\nDate: " + data.getXValue());
                    Tooltip.install(newNode, tooltip);

                    // Optional: Change color on hover
                    newNode.setOnMouseEntered(event -> newNode.setStyle("-fx-bar-fill: #3e4284;"));
                    newNode.setOnMouseExited(event -> newNode.setStyle(""));
                }
            });
        }

        // Add the series to the bar chart and apply styles
        barChart.getData().add(series);
        barChart.getStylesheets().add(getClass().getResource("/cashFlow.css").toExternalForm());
        barChart.setLegendVisible(false);

        // Set the bar chart as the center of the layout
        this.setCenter(barChart);
    }
}
