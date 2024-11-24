package ManageClient;

import ManageClient.Graph.ClientPieChar;
import ManageClient.Graph.ClientPieChart;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;




public class ClientChart extends BorderPane {
    public ClientChart(){

        VBox chart =new VBox(0);
        HBox button = new HBox();

        ClientPieChar pieChart = new ClientPieChar();

        chart.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        Button refresh  =new Button("Refresh Data");
        refresh.setOnAction(e->{
            pieChart.generatePieChart();
        });
        button.getChildren().add(refresh);
        button.setAlignment(Pos.CENTER_RIGHT);

        button.getStylesheets().add(getClass().getResource("/cashFlow.css").toExternalForm());

        chart.getChildren().addAll( button, pieChart);
        chart.setStyle("-fx-padding: 30px 50px 50px 50px");
        this.setCenter(chart);



    }
}
