package ManageProperties;

import Animations.Animations;
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

/**
 * This class manages the display of property data in the form of a pie chart.
 * It includes functionality to display default data and filter data by province,
 * city, or property type.
 */
public class PropertyData extends BorderPane {

    public static PieChart pieChart;

    /**
     * Constructor initializes the layout and the pie chart for property data.
     */
    public PropertyData(){

        pieChart = new PieChart();
        pieChart.setLegendVisible(false);  // Hide legend by default

        defaultPieChart();  // Load default pie chart with property data

        this.setCenter(pieChart);

        VBox container = new VBox(20);
        HBox headerBox = new HBox();

        // Property navigation menu
        PropertyNav propertyNav = new PropertyNav();
        propertyNav.setAlignment(Pos.CENTER_RIGHT);

        // Title of the property data section
        Text title = new Text("Property Data");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        headerBox.getChildren().addAll(title, propertyNav);
        HBox.setHgrow(propertyNav, Priority.ALWAYS);

        container.getChildren().addAll(headerBox, pieChart);
        container.setAlignment(Pos.TOP_LEFT);
        container.setStyle("-fx-padding: 50px 50px 50px 50px");

        this.setCenter(container);

        // Add animation to the pie chart
        Animations.translate(pieChart, 800);

    }

    /**
     * This method generates a default pie chart based on the properties in each province.
     */
    public static void defaultPieChart(){

        ProvinceTable provinceTable = new ProvinceTable();
        PropertyTable propertyTable = new PropertyTable();

        ArrayList<ProvincePOJO> allProvince = provinceTable.getAllProvinces();
        ArrayList<PropertyPOJO> allProperties = propertyTable.getAllPropertyRaw();

        ArrayList<PieChart.Data> data = new ArrayList<>();
        int count = 0;

        // Count properties in each province
        for(ProvincePOJO province : allProvince){

            for(PropertyPOJO property : allProperties){

                if(province.getProvince_id() == property.getProvince_id()){
                    count += 1;
                }
            }

            // Add data to pie chart if there are properties in the province
            if(count > 0){
                data.add(new PieChart.Data(count + " in " + province.getProvince(), count));
            }
            count = 0;
        }

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(data);

        pieChart.setData(pieChartData);
        pieChart.setLabelsVisible(true);  // Show labels
        pieChart.setAnimated(true);  // Enable animation
    }

    /**
     * This method filters the pie chart data by a specific province.
     * @param provinceID The ID of the province to filter by.
     */
    public static void pieChartByProvince(int provinceID){

        pieChart.getData().clear();

        PropertyTypeTable propertyTypeTable = new PropertyTypeTable();
        PropertyTable propertyTable = new PropertyTable();

        ArrayList<PropertyTypePOJO> allPropertyTypes = propertyTypeTable.getAllPropertyTypes();
        ArrayList<PropertyPOJO> allProperties = propertyTable.getAllPropertyRaw();

        ArrayList<PieChart.Data> data = new ArrayList<>();
        int count = 0;

        // Count properties of each type in the specified province
        for(PropertyTypePOJO propertyType : allPropertyTypes){

            for(PropertyPOJO property : allProperties){

                if(property.getProperty_type_id() == propertyType.getPropertyType_id() && property.getProvince_id() == provinceID){
                    count += 1;
                }
            }

            // Add data to pie chart if there are properties of this type in the province
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

    /**
     * This method filters the pie chart data by a specific city.
     * @param cityID The ID of the city to filter by.
     */
    public static void pieChartByCity(int cityID){

        pieChart.getData().clear();

        PropertyTypeTable propertyTypeTable = new PropertyTypeTable();
        PropertyTable propertyTable = new PropertyTable();

        ArrayList<PropertyTypePOJO> allPropertyType = propertyTypeTable.getAllPropertyTypes();
        ArrayList<PropertyPOJO> allProperties = propertyTable.getAllPropertyRaw();

        ArrayList<PieChart.Data> data = new ArrayList<>();
        int count = 0;

        // Count properties of each type in the specified city
        for(PropertyTypePOJO propertyType : allPropertyType){

            for(PropertyPOJO property : allProperties){

                if(property.getProperty_type_id() == propertyType.getPropertyType_id() && property.getCity_id() == cityID){
                    count += 1;
                }
            }

            // Add data to pie chart if there are properties of this type in the city
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

    /**
     * This method filters the pie chart data by a specific property type.
     * @param typeID The ID of the property type to filter by.
     */
    public static void pieChartByPropertyType(int typeID){

        pieChart.getData().clear();

        CityTable cityTable = new CityTable();
        PropertyTable propertyTable = new PropertyTable();

        ArrayList<CityPOJO> allCities = cityTable.getAllCities();
        ArrayList<PropertyPOJO> allProperties = propertyTable.getAllPropertyRaw();

        ArrayList<PieChart.Data> data = new ArrayList<>();
        int count = 0;

        // Count properties in each city for the specified property type
        for(CityPOJO city : allCities){

            for(PropertyPOJO property : allProperties){

                if(property.getCity_id() == city.getCity_id() && property.getProperty_type_id() == typeID){
                    count += 1;
                }
            }

            // Add data to pie chart if there are properties of this type in the city
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
