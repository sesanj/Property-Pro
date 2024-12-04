package ManageProperties;

import TableQuery.CityTable;
import TableQuery.PropertyTypeTable;
import TableQuery.ProvinceTable;
import com.example.propertypro.Pojo.*;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

/**
 * PropertyNav is a custom navigation bar for managing property data.
 * It provides ComboBoxes for selecting a province, city, and property type,
 * each triggering updates in the property data and pie charts.
 */
public class PropertyNav extends HBox {

    /**
     * Constructor initializes the navigation controls for selecting province, city, and property type.
     */
    public PropertyNav(){

        ProvinceTable provinceTable = new ProvinceTable();
        CityTable cityTable = new CityTable();
        PropertyTypeTable propertyTypeTable = new PropertyTypeTable();

        // ComboBox for selecting province
        ComboBox<ProvincePOJO> allProvinces = new ComboBox<>();
        allProvinces.setItems(FXCollections.observableList(provinceTable.getAllProvinces()));
        allProvinces.setPromptText("Province");
        allProvinces.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        // Action handler when a province is selected
        allProvinces.setOnAction(e -> {

            ProvincePOJO selectedProvince = allProvinces.getValue();
            int provinceID = selectedProvince.getProvince_id();

            // Fetch and display properties based on the selected province
            AllProperties.getProvinceProperties(provinceID, selectedProvince.getProvince());

            // Update pie chart by province
            PropertyData.pieChartByProvince(selectedProvince.getProvince_id());
        });

        // ComboBox for selecting city
        ComboBox<CityPOJO> allCities = new ComboBox<>();
        allCities.setItems(FXCollections.observableList(cityTable.getAllCities()));
        allCities.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());
        allCities.setPromptText("Cities");

        // Action handler when a city is selected
        allCities.setOnAction(e -> {

            CityPOJO selectCity = allCities.getValue();
            int cityID = selectCity.getCity_id();

            // Fetch and display properties based on the selected city
            AllProperties.getCityProperties(cityID, selectCity.getCity());

            // Update pie chart by city
            PropertyData.pieChartByCity(cityID);
        });

        // ComboBox for selecting property type
        ComboBox<PropertyTypePOJO> allPropertyTypes = new ComboBox<>();
        allPropertyTypes.setItems(FXCollections.observableList(propertyTypeTable.getAllPropertyTypes()));
        allPropertyTypes.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());
        allPropertyTypes.setPromptText("Property Type");

        // Action handler when a property type is selected
        allPropertyTypes.setOnAction(e -> {

            PropertyTypePOJO selectedPropertyType = allPropertyTypes.getValue();
            int propertyTypeId = selectedPropertyType.getPropertyType_id();

            // Fetch and display properties based on the selected property type
            AllProperties.getPropertyTypeProperties(propertyTypeId, selectedPropertyType.getProperty_type());

            // Update pie chart by property type
            PropertyData.pieChartByPropertyType(propertyTypeId);
        });

        // Add the combo boxes to the navigation bar
        this.getChildren().addAll(allProvinces, allCities, allPropertyTypes);
        this.setAlignment(Pos.CENTER);  // Center align the items
        this.setSpacing(10);  // Set spacing between the combo boxes
    }
}
