package ManageClient.Graph;

import TableQuery.*;
import com.example.propertypro.Pojo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class ClientPieChar extends BorderPane {
    private PieChart pieChart;

    public ClientPieChar(){

        pieChart = new PieChart();

        generatePieChart();

        this.setCenter(pieChart);

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