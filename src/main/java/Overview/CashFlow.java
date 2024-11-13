package Overview;

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

public class CashFlow extends BorderPane {

    public CashFlow(){
        Database db = Database.getNewDatabase();

        BarChart barChart = new BarChart(db);
        LineChart lineChart = new LineChart(db);

        StackPane chart = new StackPane();

        VBox cashFlowBox = new VBox(10);

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

        cashFlowBox.getChildren().addAll(cashFlow, buttons, chart);
        cashFlowBox.setStyle("-fx-padding: 50px 50px 10px 50px");

        this.setCenter(cashFlowBox);
    }
}
