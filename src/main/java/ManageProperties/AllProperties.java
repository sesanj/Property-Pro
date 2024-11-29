package ManageProperties;

import Overview.TopClients;
import TableQuery.PropertyTable;
import TableQuery.PropertyTypeTable;
import com.example.propertypro.Pojo.PropertyPOJO;
import com.example.propertypro.Pojo.PropertyPOJORefined;
import com.example.propertypro.Pojo.TransactionPOJORefined;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class AllProperties extends BorderPane {

    public static TableView allProperties;
    public static Text title;


    public AllProperties() {

        VBox container = new VBox(30);

        Button deleteProperty = new Button("Delete Property");
        deleteProperty.getStyleClass().add("deleteButton");
        deleteProperty.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        Button viewAll = new Button("View All");
        viewAll.getStyleClass().add("tabButton");
        viewAll.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());
        HBox viewAllBox = new HBox(20);
        viewAllBox.getChildren().addAll(deleteProperty, viewAll);
        viewAllBox.setAlignment(Pos.CENTER_RIGHT);




        PropertyTable propertyTable = new PropertyTable();


        title = new Text(propertyTable.getAllProperty().size() + " Total Properties");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        HBox titleBox = new HBox();
        titleBox.getChildren().addAll(title, viewAllBox);
        HBox.setHgrow(viewAllBox, Priority.ALWAYS);

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


        allProperties.getStylesheets().add(getClass().getResource("/tableView.css").toExternalForm());

        allProperties.getColumns().addAll(name, type, province, city);
        allProperties.getItems().addAll(propertyTable.getAllProperty());

        allProperties.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{

            if(newValue != null){

                PropertyPOJORefined property = (PropertyPOJORefined) allProperties.getSelectionModel().getSelectedItem();

                PropertyDisplay.getPropertyDetails(property);
            }
        });

        viewAll.setOnAction(e -> {

            allProperties.getItems().clear();
            allProperties.getItems().addAll(propertyTable.getAllProperty());
            title.setText(propertyTable.getAllProperty().size() + " Total Properties");
        });

        deleteProperty.setOnAction(e -> {
            PropertyPOJORefined selectedProperty = (PropertyPOJORefined) allProperties.getSelectionModel().getSelectedItem();
            propertyTable.deleteProperty(selectedProperty.getProperty_id());
        });

        container.getChildren().addAll(titleBox, allProperties);
        container.setAlignment(Pos.CENTER_LEFT);
        container.setStyle("-fx-padding: 10px 50px 40px 50px");

        this.setCenter(container);
    }

    public static void getProvinceProperties(int provinceId, String provinceName){

        PropertyTable propertyTable = new PropertyTable();

        ArrayList<PropertyPOJORefined> properties = propertyTable.getPropertyByProvince(provinceId);

        allProperties.getItems().clear();
        allProperties.getItems().addAll(properties);
        allProperties.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        title.setText(properties.size() + " Property In " + provinceName);

    }

    public static void getCityProperties(int cityId, String cityName){

        PropertyTable propertyTable = new PropertyTable();

        ArrayList<PropertyPOJORefined> properties = propertyTable.getPropertyByCity(cityId);

        allProperties.getItems().clear();
        allProperties.getItems().addAll(properties);
        allProperties.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        title.setText(properties.size() + " Property In " + cityName);

    }

    public static void getPropertyTypeProperties(int propertyId, String propertyType){

        PropertyTable propertyTable = new PropertyTable();

        ArrayList<PropertyPOJORefined> properties = propertyTable.getPropertyByPropertyType(propertyId);

        allProperties.getItems().clear();
        allProperties.getItems().addAll(properties);
        allProperties.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        title.setText(properties.size() + " " + propertyType);

    }

}
