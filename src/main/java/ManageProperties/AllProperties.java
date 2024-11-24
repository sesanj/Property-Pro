package ManageProperties;

import Overview.TopClients;
import TableQuery.PropertyTable;
import TableQuery.PropertyTypeTable;
import com.example.propertypro.Pojo.PropertyPOJO;
import com.example.propertypro.Pojo.PropertyPOJORefined;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class AllProperties extends BorderPane {

    private static TableView allProperties;


    public AllProperties() {

        VBox container = new VBox(30);

        Text title = new Text("All Properties");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        PropertyTable propertyTable = new PropertyTable();

        allProperties = new TableView();
        allProperties.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<PropertyPOJORefined, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getName()));

        TableColumn<PropertyPOJORefined, String> type = new TableColumn<>("Type");
        type.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getProperty_type()));

        TableColumn<PropertyPOJORefined, String> province = new TableColumn<>("Province");
        province.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getProvince()));

        TableColumn<PropertyPOJORefined, String> city = new TableColumn<>("City");
        city.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getCity()));

//        TableColumn<PropertyPOJORefined, String> street = new TableColumn<>("Street");
//        street.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getStreet()));

//        TableColumn<PropertyPOJORefined, String> postalCode = new TableColumn<>("Postal Code");
//        postalCode.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getPostal_code()));

//        TableColumn<PropertyPOJORefined, String> availability = new TableColumn<>("Availability");
//        availability.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getAvailability() + ""));

        allProperties.getStylesheets().add(getClass().getResource("/tableView.css").toExternalForm());

        allProperties.getColumns().addAll(name, type, province, city);
        allProperties.getItems().addAll(propertyTable.getAllProperty());

        container.getChildren().addAll(title, allProperties);
        container.setAlignment(Pos.CENTER_LEFT);
        container.setStyle("-fx-padding: 30px 50px 50px 50px");

        this.setCenter(container);
    }

    public static void getProvinceProperties(int provinceId){

        PropertyTable propertyTable = new PropertyTable();

        ArrayList<PropertyPOJORefined> properties = propertyTable.getPropertyByProvince(provinceId);

        allProperties.getItems().clear();
        allProperties.getItems().addAll(properties);
        allProperties.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public static void getCityProperties(int cityId){

        PropertyTable propertyTable = new PropertyTable();

        ArrayList<PropertyPOJORefined> properties = propertyTable.getPropertyByCity(cityId);

        allProperties.getItems().clear();
        allProperties.getItems().addAll(properties);
        allProperties.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public static void getPropertyTypeProperties(int propertyId){

        PropertyTable propertyTable = new PropertyTable();

        ArrayList<PropertyPOJORefined> properties = propertyTable.getPropertyByPropertyType(propertyId);

        allProperties.getItems().clear();
        allProperties.getItems().addAll(properties);
        allProperties.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

}
