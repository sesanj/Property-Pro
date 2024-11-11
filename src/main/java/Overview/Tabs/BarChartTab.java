package Overview.Tabs;

import Database.Database;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;

public class BarChartTab extends BorderPane {
    public BarChartTab(Database db){

        CategoryAxis xAxis = new CategoryAxis();

        NumberAxis yAxis = new NumberAxis();

        yAxis.setTickLabelFormatter(new StringConverter<>() {
            @Override
            public String toString(Number object) {
                return String.format("$%,.0f", object); // Format with commas and the "$" sign
            }

            @Override
            public Number fromString(String string) {
                return Double.parseDouble(string.replace("$", "").replace(",", ""));
            }
        });

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Monthly Revenue for Last 12 Months");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        //series.setName("Revenue");


        String query = "SELECT YEAR(" + TRANSACTION_TIMESTAMP  + ") AS year, " +
                "MONTH(" + TRANSACTION_TIMESTAMP + ") AS month, " +
                "SUM(" + TRANSACTION_AMOUNT + ") AS monthly_revenue " +
                "FROM " + TRANSACTION_TABLE + " " +
                "WHERE " + TRANSACTION_TIMESTAMP + " >= DATE_SUB(CURDATE(), INTERVAL 10 MONTH) " +
                "GROUP BY YEAR(" + TRANSACTION_TIMESTAMP  + "), MONTH(" + TRANSACTION_TIMESTAMP + ")" +
                "ORDER BY YEAR(" + TRANSACTION_TIMESTAMP  + "), MONTH(" + TRANSACTION_TIMESTAMP + ")";

        ArrayList<String> month = new ArrayList<>();
        ArrayList<Double> revenue = new ArrayList<>();

        try{
            Statement transactions = db.getConnection().createStatement();
            ResultSet data = transactions.executeQuery(query);

            while (data.next()){
                month.add(data.getString("year") + "-" + data.getString("month"));
                revenue.add(data.getDouble("monthly_revenue"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        for (int i = 0; i < month.size(); i++) {
            series.getData().add(new XYChart.Data<>(month.get(i), revenue.get(i)));
        }

        // Add tooltips for each data point
        for (XYChart.Data<String, Number> data : series.getData()) {
            // Add a listener to the node property
            data.nodeProperty().addListener((obs, oldNode, newNode) -> {
                if (newNode != null) {
                    // Create and install the tooltip on the new node
                    Tooltip tooltip = new Tooltip("Revenue: $" + data.getYValue() + "\nDate: " + data.getXValue());
                    Tooltip.install(newNode, tooltip);

                    // Optional: Change color on hover
                    newNode.setOnMouseEntered(event -> newNode.setStyle("-fx-bar-fill: #ff9800;"));
                    newNode.setOnMouseExited(event -> newNode.setStyle(""));
                }
            });
        }

        barChart.getData().add(series);
        barChart.getStylesheets().add(getClass().getResource("/cashFlow.css").toExternalForm());
        barChart.setLegendVisible(false);

        this.setCenter(barChart);

    }
}
