package Overview;

import Animations.Animations;
import Database.Database;
import Overview.Charts.BarChart;
import Overview.Charts.LineChart;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Represents the Cash Flow view which allows users to view their monthly revenue
 * through either a Bar Chart or a Line Chart. The user can toggle between the two
 * chart views using buttons. The charts are populated with data fetched from the
 * database, and animations are applied when switching between charts.
 */
public class CashFlow extends BorderPane {

    /**
     * Constructs the CashFlow view with a Bar Chart and a Line Chart. It includes
     * buttons to toggle between the two chart views. The charts are animated when switched.
     *
     * The layout contains a title, two buttons ("Bar Chart" and "Line Chart"), and a container
     * for displaying the charts. The charts are populated with revenue data fetched from the
     * database.
     */
    public CashFlow(){
        // Initialize the database and charts
        Database db = Database.getNewDatabase();

        BarChart barChart = new BarChart(db);
        LineChart lineChart = new LineChart(db);

        StackPane chart = new StackPane();

        // Create a VBox to hold the cash flow title, buttons, and chart
        VBox cashFlowBox = new VBox(10);

        // Title for the Cash Flow section
        Text cashFlow = new Text("Your Cash Flow");
        cashFlow.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        // Buttons for toggling between charts
        Button barchartButton = new Button("Bar Chart");
        Button lineChartButton = new Button("Line Chart");

        // Initially add the bar chart to the StackPane
        chart.getChildren().add(barChart);

        // Define actions for the buttons
        barchartButton.setOnAction(e->{
            chart.getChildren().clear();
            chart.getChildren().add(barChart);
            Animations.translate(barChart, 800); // Animate the chart transition
        });

        lineChartButton.setOnAction(e->{
            chart.getChildren().clear();
            chart.getChildren().add(lineChart);
            Animations.translate(lineChart, 800); // Animate the chart transition
        });

        // Create a horizontal box for the buttons and set its alignment
        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(barchartButton, lineChartButton);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        // Apply styles to the buttons
        barchartButton.getStylesheets().add(getClass().getResource("/cashFlow.css").toExternalForm());
        lineChartButton.getStylesheets().add(getClass().getResource("/cashFlow.css").toExternalForm());

        // Add all components (title, buttons, chart) to the VBox
        cashFlowBox.getChildren().addAll(cashFlow, buttons, chart);
        cashFlowBox.setStyle("-fx-padding: 50px 50px 10px 50px");

        // Set the VBox as the center of the layout
        this.setCenter(cashFlowBox);

        // Animate the initial chart display
        Animations.translate(chart, 800);
    }
}
