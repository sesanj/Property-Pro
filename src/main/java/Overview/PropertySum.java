package Overview;

import Animations.Animations;
import TableQuery.PropertyTable;
import TableQuery.ProvinceTable;
import com.example.propertypro.Pojo.PropertyPOJO;
import com.example.propertypro.Pojo.ProvincePOJO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class PropertySum extends BorderPane {

    public static PieChart pieChart;
    public PropertySum(){

        pieChart = new PieChart();

        VBox analyticsBox = new VBox(0);

        HBox titleBox = new HBox();
        HBox refreshBox = new HBox();
        refreshBox.setAlignment(Pos.CENTER_RIGHT);

        Text title = new Text("Property Analytics");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Button refresh  = new Button("Refresh Data");
        refresh.setOnAction(e->{
            generatePieChart();
        });

        titleBox.getChildren().addAll(title, refreshBox);
        HBox.setHgrow(refreshBox, Priority.ALWAYS);

        refresh.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());
        refresh.getStyleClass().add("tabButton");

        refreshBox.getChildren().add(refresh);

        analyticsBox.getChildren().addAll(titleBox, pieChart);
        analyticsBox.setStyle("-fx-padding: 30px 50px 50px 50px");

        this.setCenter(analyticsBox);

        generatePieChart();
        Animations.translate(pieChart, 800);
    }

    public void generatePieChart(){

        ProvinceTable provinceTable = new ProvinceTable();
        PropertyTable propertyTable = new PropertyTable();

        ArrayList<ProvincePOJO> allProvince = provinceTable.getAllProvinces();
        ArrayList<PropertyPOJO> allProperties = propertyTable.getAllPropertyRaw();

        ArrayList<PieChart.Data> data = new ArrayList<>();
        int count = 0;

        for(ProvincePOJO province : allProvince){

            for(PropertyPOJO property : allProperties){

                if(province.getProvince_id() == property.getProvince_id()){
                    count += 1;
                }
            }

            if(count > 0){
                data.add(new PieChart.Data(province.getProvince(), count));
            }
            count = 0;
        }

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(data);

        pieChart.setData(pieChartData);
        pieChart.setLabelsVisible(true);
        pieChart.setAnimated(true);
    }


}
