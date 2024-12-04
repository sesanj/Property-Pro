package ManageProperties;

import Animations.Animations;
import TableQuery.PropertyTable;
import com.example.propertypro.Pojo.PropertyPOJORefined;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * AllProperties is a JavaFX component that displays a list of properties.
 * It allows filtering properties based on availability and provides
 * detailed views for properties upon selection.
 */
public class AllProperties extends BorderPane {

    public static TableView allProperties;  // TableView for displaying properties
    public static Text title;  // Title text showing the total number of properties
    public static PropertyTable propertyTable = new PropertyTable();  // PropertyTable instance for fetching property data

    /**
     * Constructor to initialize the AllProperties view.
     * Sets up the layout, buttons, and table to display properties.
     */
    public AllProperties() {

        // Container for the entire content of the view
        VBox container = new VBox(30);

        // Toggle button for filtering available properties
        ToggleButton toggleButton = new ToggleButton("Available");
        toggleButton.getStyleClass().add("toggleButton");
        toggleButton.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        // Button to view all properties
        Button viewAll = new Button("View All");
        viewAll.getStyleClass().add("tabButton");
        viewAll.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        // Horizontal box to hold the toggle and view all buttons
        HBox buttonBox = new HBox(20);
        buttonBox.getChildren().addAll(toggleButton, viewAll);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        // Title displaying the number of total properties
        title = new Text(propertyTable.getAllProperty().size() + " Total Properties");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        // HBox for the title and button box
        HBox titleBox = new HBox();
        titleBox.getChildren().addAll(title, buttonBox);
        HBox.setHgrow(buttonBox, Priority.ALWAYS);

        // TableView for displaying properties
        allProperties = new TableView();
        allProperties.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Define columns for the property details
        TableColumn<PropertyPOJORefined, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getName()));

        TableColumn<PropertyPOJORefined, String> type = new TableColumn<>("Type");
        type.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getProperty_type()));

        TableColumn<PropertyPOJORefined, String> province = new TableColumn<>("Province");
        province.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getProvince()));

        TableColumn<PropertyPOJORefined, String> city = new TableColumn<>("City");
        city.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getCity()));

        // Apply styles for the TableView
        allProperties.getStylesheets().add(getClass().getResource("/tableView.css").toExternalForm());

        // Add columns to the TableView
        allProperties.getColumns().addAll(name, type, province, city);
        allProperties.getItems().addAll(propertyTable.getAllProperty());

        // Event listener to handle selection of a property in the table
        allProperties.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                PropertyPOJORefined property = (PropertyPOJORefined) allProperties.getSelectionModel().getSelectedItem();
                PropertyDisplay.getPropertyDetails(property);  // Show detailed property information
                PropertyForm.getPropertyDetails(propertyTable.getPropertyRaw(property.getProperty_id()));  // Show property form
            }
        });

        // Event handler to show all properties when "View All" is clicked
        viewAll.setOnAction(e -> {
            allProperties.getItems().clear();
            allProperties.getItems().addAll(propertyTable.getAllProperty());
            title.setText(propertyTable.getAllProperty().size() + " Total Properties");
        });

        // Event handler to toggle availability filter
        toggleButton.setOnAction(e -> {
            if (toggleButton.isSelected()) {
                allProperties.getItems().clear();
                allProperties.getItems().addAll(propertyTable.getPropertyByAvailability(1));  // Show available properties
                title.setText(allProperties.getItems().size() + " Total Properties Available");
                toggleButton.setText("Not Available");
            } else {
                allProperties.getItems().clear();
                allProperties.getItems().addAll(propertyTable.getPropertyByAvailability(0));  // Show unavailable properties
                title.setText(allProperties.getItems().size() + " Total Properties Unavailable");
                toggleButton.setText("Available");
            }
        });

        // Add the title and table to the container
        container.getChildren().addAll(titleBox, allProperties);
        container.setAlignment(Pos.CENTER_LEFT);
        container.setStyle("-fx-padding: 20px 50px 40px 40px");

        // Set the container as the center of the BorderPane
        this.setCenter(container);

        // Apply an animation to the table for smooth rendering
        Animations.translate(allProperties, 800);
    }

    /**
     * Updates the property table to display properties from a specific province.
     *
     * @param provinceId The ID of the province.
     * @param provinceName The name of the province.
     */
    public static void getProvinceProperties(int provinceId, String provinceName) {
        PropertyTable propertyTable = new PropertyTable();

        ArrayList<PropertyPOJORefined> properties = propertyTable.getPropertyByProvince(provinceId);

        allProperties.getItems().clear();
        allProperties.getItems().addAll(properties);
        allProperties.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        title.setText(properties.size() + " Property In " + provinceName);
    }

    /**
     * Updates the property table to display properties from a specific city.
     *
     * @param cityId The ID of the city.
     * @param cityName The name of the city.
     */
    public static void getCityProperties(int cityId, String cityName) {
        PropertyTable propertyTable = new PropertyTable();

        ArrayList<PropertyPOJORefined> properties = propertyTable.getPropertyByCity(cityId);

        allProperties.getItems().clear();
        allProperties.getItems().addAll(properties);
        allProperties.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        title.setText(properties.size() + " Property In " + cityName);
    }

    /**
     * Updates the property table to display properties of a specific type.
     *
     * @param propertyId The ID of the property type.
     * @param propertyType The name of the property type.
     */
    public static void getPropertyTypeProperties(int propertyId, String propertyType) {
        PropertyTable propertyTable = new PropertyTable();

        ArrayList<PropertyPOJORefined> properties = propertyTable.getPropertyByPropertyType(propertyId);

        allProperties.getItems().clear();
        allProperties.getItems().addAll(properties);
        allProperties.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        title.setText(properties.size() + " " + propertyType);
    }

    /**
     * Refreshes the property table to show all properties.
     */
    public static void refreshPropertyTable() {
        allProperties.getItems().clear();
        allProperties.getItems().addAll(propertyTable.getAllProperty());

        title.setText(allProperties.getItems().size() + " Total Properties");
    }
}
