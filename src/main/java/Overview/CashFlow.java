package Overview;

import Database.Database;
import Overview.Tabs.BarChartTab;
import Overview.Tabs.LineChartTab;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.control.*;
//import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.StringConverter;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;

public class CashFlow extends BorderPane {

    public CashFlow(){
        Database db = Database.getNewDatabase();

        BarChartTab barChart = new BarChartTab(db);
        LineChartTab lineChart = new LineChartTab(db);

        StackPane chart = new StackPane();



        VBox cashFlowBox = new VBox();


        Text cashFlow = new Text("Your Cash Flow");
        cashFlow.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");



        Button barchartButton = new Button("Bar Chart");
        Button lineChartButton = new Button("Line Chart");

        chart.getChildren().add(barChart);

        barchartButton.setOnAction(e->{
            chart.getChildren().clear();
            chart.getChildren().add(barChart);
        });

        lineChartButton.setOnAction(e->{
            chart.getChildren().clear();
            chart.getChildren().add(lineChart);
        });

        HBox buttons = new HBox(10);

        buttons.getChildren().addAll(barchartButton, lineChartButton);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        barchartButton.getStylesheets().add(getClass().getResource("/cashFlow.css").toExternalForm());
        lineChartButton.getStylesheets().add(getClass().getResource("/cashFlow.css").toExternalForm());

//        chartTab.getTabs().addAll(barChart, lineChart);
//        chartTab.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
//        chartTab.getStylesheets().add(getClass().getResource("/cashFlow.css").toExternalForm());
//        chartTab.setStyle("-fx-tab-header-area-skin: right;");

//        CategoryAxis xAxis = new CategoryAxis();
//
//        NumberAxis yAxis = new NumberAxis();
//
//        yAxis.setTickLabelFormatter(new StringConverter<>() {
//            @Override
//            public String toString(Number object) {
//                return String.format("$%,.0f", object); // Format with commas and the "$" sign
//            }
//
//            @Override
//            public Number fromString(String string) {
//                return Double.parseDouble(string.replace("$", "").replace(",", ""));
//            }
//        });
//
//        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
//        barChart.setTitle("Monthly Revenue for Last 12 Months");
//
//        XYChart.Series<String, Number> series = new XYChart.Series<>();
//        //series.setName("Revenue");
//
//
//        String query = "SELECT YEAR(" + TRANSACTION_TIMESTAMP  + ") AS year, " +
//                "MONTH(" + TRANSACTION_TIMESTAMP + ") AS month, " +
//                "SUM(" + TRANSACTION_AMOUNT + ") AS monthly_revenue " +
//                "FROM " + TRANSACTION_TABLE + " " +
//                "WHERE " + TRANSACTION_TIMESTAMP + " >= DATE_SUB(CURDATE(), INTERVAL 10 MONTH) " +
//                "GROUP BY YEAR(" + TRANSACTION_TIMESTAMP  + "), MONTH(" + TRANSACTION_TIMESTAMP + ")" +
//                "ORDER BY YEAR(" + TRANSACTION_TIMESTAMP  + "), MONTH(" + TRANSACTION_TIMESTAMP + ")";
//
//        ArrayList<String> month = new ArrayList<>();
//        ArrayList<Double> revenue = new ArrayList<>();
//
//        try{
//            Statement transactions = db.getConnection().createStatement();
//            ResultSet data = transactions.executeQuery(query);
//
//            while (data.next()){
//                month.add(data.getString("year") + "-" + data.getString("month"));
//                revenue.add(data.getDouble("monthly_revenue"));
//            }
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//        for (int i = 0; i < month.size(); i++) {
//            series.getData().add(new XYChart.Data<>(month.get(i), revenue.get(i)));
//        }
//
//        // Add tooltips for each data point
//        for (XYChart.Data<String, Number> data : series.getData()) {
//            // Add a listener to the node property
//            data.nodeProperty().addListener((obs, oldNode, newNode) -> {
//                if (newNode != null) {
//                    // Create and install the tooltip on the new node
//                    Tooltip tooltip = new Tooltip("Revenue: $" + data.getYValue() + "\nDate: " + data.getXValue());
//                    Tooltip.install(newNode, tooltip);
//
//                    // Optional: Change color on hover
//                    newNode.setOnMouseEntered(event -> newNode.setStyle("-fx-bar-fill: #ff9800;"));
//                    newNode.setOnMouseExited(event -> newNode.setStyle(""));
//                }
//            });
//        }
//
//        barChart.getData().add(series);
//        barChart.getStylesheets().add(getClass().getResource("/cashFlow.css").toExternalForm());
//        barChart.setLegendVisible(false);

        cashFlowBox.getChildren().addAll(cashFlow, buttons, chart);
        cashFlowBox.setStyle("-fx-padding: 50px");

        this.setCenter(cashFlowBox);
    }
}
