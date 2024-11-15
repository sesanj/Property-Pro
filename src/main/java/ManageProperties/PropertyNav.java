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

        Text province = new Text("Province");
        Text city = new Text("City");
        Text propertyType = new Text("Property Type");

        ProvinceTable provinceTable = new ProvinceTable();
        CityTable cityTable = new CityTable();
        PropertyTypeTable propertyTypeTable = new PropertyTypeTable();

        ComboBox<ProvincePOJO> allProvinces = new ComboBox<>();
        allProvinces.setItems(FXCollections.observableList(provinceTable.getAllProvinces()));
        //allProvinces.getSelectionModel().select(0);
        allProvinces.setPromptText("Province");


        ComboBox<CityPOJO> allCities = new ComboBox<>();
        allCities.setItems(FXCollections.observableList(cityTable.getAllCities()));
        //allCities.getSelectionModel().select(0);
        allCities.setPromptText("Cities");

        ComboBox<PropertyTypePOJO> allPropertyTypes = new ComboBox<>();
        allPropertyTypes.setItems(FXCollections.observableList(propertyTypeTable.getAllPropertyTypes()));
        //allPropertyTypes.getSelectionModel().select(0);
        allPropertyTypes.setPromptText("Property Type");

        this.getChildren().addAll(allProvinces, allCities, allPropertyTypes);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
    }
}
