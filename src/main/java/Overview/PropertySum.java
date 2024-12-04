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

/**
 * Represents the Property Analytics view that displays a pie chart showing the
 * distribution of properties across different provinces. Users can refresh the
 * data displayed in the chart by clicking the "Refresh Data" button.
 */
public class PropertySum extends BorderPane {

    /** The pie chart that displays the property distribution. */
    public static PieChart pieChart;

    /**
     * Constructs the PropertySum view. Initializes the layout with a title,
     * a refresh button, and a pie chart. The chart is populated with property
     * data when the view is created, and can be refreshed by the user.
     */
    public PropertySum(){

        // Initialize the pie chart
        pieChart = new PieChart();

        // Create the layout boxes
        VBox analyticsBox = new VBox(0);

        HBox titleBox = new HBox();
        HBox refreshBox = new HBox();
        refreshBox.setAlignment(Pos.CENTER_RIGHT);

        // Set the title of the section
        Text title = new Text("Property Analytics");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        // Button to refresh the data in the pie chart
        Button refresh  = new Button("Refresh Data");
        refresh.setOnAction(e->{
            generatePieChart();  // Generate the pie chart when clicked
        });

        // Arrange title and refresh button
        titleBox.getChildren().addAll(title, refreshBox);
        HBox.setHgrow(refreshBox, Priority.ALWAYS);

        // Apply styles to the refresh button
        refresh.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());
        refresh.getStyleClass().add("tabButton");

        // Add the refresh button to the refresh box
        refreshBox.getChildren().add(refresh);

        // Add the title and pie chart to the main analytics box
        analyticsBox.getChildren().addAll(titleBox, pieChart);
        analyticsBox.setStyle("-fx-padding: 30px 50px 50px 50px");

        // Set the analytics box as the center of the layout
        this.setCenter(analyticsBox);

        // Generate and display the initial pie chart
        generatePieChart();

        // Apply animation to the pie chart
        Animations.translate(pieChart, 800);
    }

    /**
     * Generates the pie chart using data from the PropertyTable and ProvinceTable.
     * The pie chart shows the number of properties in each province.
     */
    public void generatePieChart(){

        // Create instances of the tables that provide the data
        ProvinceTable provinceTable = new ProvinceTable();
        PropertyTable propertyTable = new PropertyTable();

        // Fetch all provinces and properties from the database
        ArrayList<ProvincePOJO> allProvince = provinceTable.getAllProvinces();
        ArrayList<PropertyPOJO> allProperties = propertyTable.getAllPropertyRaw();

        // List to store the pie chart data
        ArrayList<PieChart.Data> data = new ArrayList<>();
        int count = 0;

        // Iterate over all provinces and count the properties for each
        for(ProvincePOJO province : allProvince){
            for(PropertyPOJO property : allProperties){
                if(province.getProvince_id() == property.getProvince_id()){
                    count += 1;  // Increment count for each matching property
                }
            }

            // If there are properties for this province, add the data to the chart
            if(count > 0){
                data.add(new PieChart.Data(province.getProvince(), count));
            }
            count = 0;  // Reset count for the next province
        }

        // Convert the data list to an ObservableList for the pie chart
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(data);

        // Set the pie chart data and configure chart settings
        pieChart.setData(pieChartData);
        pieChart.setLabelsVisible(true);
        pieChart.setAnimated(true);
    }
}
