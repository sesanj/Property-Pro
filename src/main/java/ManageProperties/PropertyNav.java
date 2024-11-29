package ManageProperties;

import TableQuery.CityTable;
import TableQuery.PropertyTypeTable;
import TableQuery.ProvinceTable;
import com.example.propertypro.Pojo.*;
import com.example.propertypro.Properties;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class PropertyNav extends HBox {

    public PropertyNav(){

        ProvinceTable provinceTable = new ProvinceTable();
        CityTable cityTable = new CityTable();
        PropertyTypeTable propertyTypeTable = new PropertyTypeTable();

        ComboBox<ProvincePOJO> allProvinces = new ComboBox<>();
        allProvinces.setItems(FXCollections.observableList(provinceTable.getAllProvinces()));
        allProvinces.setPromptText("Province");
        allProvinces.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());


        allProvinces.setOnAction(e -> {

            ProvincePOJO selectedProvince = allProvinces.getValue();

            int provinceID = selectedProvince.getProvince_id();

            AllProperties.getProvinceProperties(provinceID, selectedProvince.getProvince());

            PropertyData.pieChartByProvince(selectedProvince.getProvince_id());

        });


        ComboBox<CityPOJO> allCities = new ComboBox<>();
        allCities.setItems(FXCollections.observableList(cityTable.getAllCities()));
        allCities.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        allCities.setPromptText("Cities");

        allCities.setOnAction(e -> {

            CityPOJO selectCity = allCities.getValue();

            int cityID = selectCity.getCity_id();

            AllProperties.getCityProperties(cityID, selectCity.getCity());

            PropertyData.pieChartByCity(cityID);

        });


        ComboBox<PropertyTypePOJO> allPropertyTypes = new ComboBox<>();
        allPropertyTypes.setItems(FXCollections.observableList(propertyTypeTable.getAllPropertyTypes()));
        allPropertyTypes.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        allPropertyTypes.setPromptText("Property Type");


        allPropertyTypes.setOnAction(e -> {

            PropertyTypePOJO selectedPropertyType = allPropertyTypes.getValue();

            int propertyTypeId = selectedPropertyType.getPropertyType_id();

            AllProperties.getPropertyTypeProperties(propertyTypeId, selectedPropertyType.getProperty_type());

            PropertyData.pieChartByPropertyType(propertyTypeId);
        });




        this.getChildren().addAll(allProvinces, allCities, allPropertyTypes);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
    }
}
