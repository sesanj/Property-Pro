package Overview;

import Overview.Charts.PropertyPieChart;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javax.swing.border.Border;

public class PropertySum extends BorderPane {
    public PropertySum(){

        VBox analyticsBox = new VBox(0);
        HBox button = new HBox();

        PropertyPieChart pieChart = new PropertyPieChart();

        Text title = new Text("Property Analytics");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");


        Button refresh  =new Button("Refresh Data");
        refresh.setOnAction(e->{
            pieChart.generatePieChart();
        });

        button.getChildren().add(refresh);
        button.setAlignment(Pos.CENTER_RIGHT);

        button.getStylesheets().add(getClass().getResource("/cashFlow.css").toExternalForm());

        analyticsBox.getChildren().addAll(title, button, pieChart);
        analyticsBox.setStyle("-fx-padding: 30px 50px 50px 50px");

        this.setCenter(analyticsBox);

    }


}
