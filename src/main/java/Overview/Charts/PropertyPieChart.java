package Overview.Charts;

import TableQuery.PropertyTable;
import TableQuery.ProvinceTable;
import com.example.propertypro.Pojo.PropertyPOJO;
import com.example.propertypro.Pojo.ProvincePOJO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.chart.PieChart;

import java.util.ArrayList;

public class PropertyPieChart extends BorderPane {

    private PieChart pieChart;

    public PropertyPieChart(){

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
