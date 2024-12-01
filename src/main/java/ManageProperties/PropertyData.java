package ManageProperties;

import TableQuery.CityTable;
import TableQuery.PropertyTable;
import TableQuery.PropertyTypeTable;
import TableQuery.ProvinceTable;
import com.example.propertypro.Pojo.CityPOJO;
import com.example.propertypro.Pojo.PropertyPOJO;
import com.example.propertypro.Pojo.PropertyTypePOJO;
import com.example.propertypro.Pojo.ProvincePOJO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class PropertyData extends BorderPane {

    public static PieChart pieChart;

    public PropertyData(){

        pieChart = new PieChart();
        pieChart.setLegendVisible(false);

        defaultPieChart();

        this.setCenter(pieChart);

        VBox container = new VBox(20);
        HBox headerBox = new HBox();

        PropertyNav propertyNav = new PropertyNav();
        propertyNav.setAlignment(Pos.CENTER_RIGHT);

        Text title = new Text("Property Data");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        headerBox.getChildren().addAll(title, propertyNav);
        HBox.setHgrow(propertyNav, Priority.ALWAYS);

        container.getChildren().addAll(headerBox, pieChart);
        container.setAlignment(Pos.TOP_LEFT);
        container.setStyle("-fx-padding: 50px 50px 50px 50px");

        this.setCenter(container);
    }

    public static void defaultPieChart(){

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
                data.add(new PieChart.Data(count + " in " + province.getProvince(), count));
            }
            count = 0;
        }

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(data);

        pieChart.setData(pieChartData);
        pieChart.setLabelsVisible(true);
        pieChart.setAnimated(true);
    }

    public static void pieChartByProvince(int provinceID){

        pieChart.getData().clear();

        PropertyTypeTable propertyTypeTable = new PropertyTypeTable();
        PropertyTable propertyTable = new PropertyTable();

        ArrayList<PropertyTypePOJO> allPropertyTypes = propertyTypeTable.getAllPropertyTypes();
        ArrayList<PropertyPOJO> allProperties = propertyTable.getAllPropertyRaw();

        ArrayList<PieChart.Data> data = new ArrayList<>();
        int count = 0;

        for(PropertyTypePOJO propertyType : allPropertyTypes){

            for(PropertyPOJO property : allProperties){

                if(property.getProperty_type_id() == propertyType.getPropertyType_id() && property.getProvince_id() == provinceID){
                    count += 1;
                }
            }

            if(count > 0){
                data.add(new PieChart.Data(+ count + " " + propertyType.getProperty_type(), count));
            }
            count = 0;
        }

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(data);

        pieChart.setData(pieChartData);
        pieChart.setLabelsVisible(true);
        pieChart.setAnimated(true);

    }

    public static void pieChartByCity(int cityID){

        pieChart.getData().clear();

        PropertyTypeTable propertyTypeTable = new PropertyTypeTable();
        PropertyTable propertyTable = new PropertyTable();

        ArrayList<PropertyTypePOJO> allPropertyType = propertyTypeTable.getAllPropertyTypes();
        ArrayList<PropertyPOJO> allProperties = propertyTable.getAllPropertyRaw();

        ArrayList<PieChart.Data> data = new ArrayList<>();
        int count = 0;

        for(PropertyTypePOJO propertyType : allPropertyType){

            for(PropertyPOJO property : allProperties){

                if(property.getProperty_type_id() == propertyType.getPropertyType_id() && property.getCity_id() == cityID){
                    count += 1;
                }
            }

            if(count > 0){
                data.add(new PieChart.Data(+ count + " " + propertyType.getProperty_type(), count));
            }
            count = 0;
        }

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(data);

        pieChart.setData(pieChartData);
        pieChart.setLabelsVisible(true);
        pieChart.setAnimated(true);

    }

    public static void pieChartByPropertyType(int typeID){

        pieChart.getData().clear();

        CityTable cityTable = new CityTable();
        PropertyTable propertyTable = new PropertyTable();

        ArrayList<CityPOJO> allCities = cityTable.getAllCities();
        ArrayList<PropertyPOJO> allProperties = propertyTable.getAllPropertyRaw();

        ArrayList<PieChart.Data> data = new ArrayList<>();
        int count = 0;

        for(CityPOJO city : allCities){

            for(PropertyPOJO property : allProperties){

                if(property.getCity_id() == city.getCity_id() && property.getProperty_type_id() == typeID){
                    count += 1;
                }
            }

            if(count > 0){
                data.add(new PieChart.Data(+ count + " in " + city.getCity(), count));
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
