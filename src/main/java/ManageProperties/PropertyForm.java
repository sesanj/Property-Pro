package ManageProperties;

import Animations.Animations;
import TableQuery.*;
import com.example.propertypro.Pojo.*;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

/**
 * PropertyForm class for managing property information such as adding or updating property details.
 * This class defines the form layout, validation, and action handlers for adding or updating properties.
 */

public class PropertyForm extends BorderPane {

    // Static tables to fetch data for combo boxes
    public static CityTable cityTable = new CityTable();
    public static ProvinceTable provinceTable = new ProvinceTable();
    public static PropertyTypeTable propertyTypeTable = new PropertyTypeTable();

    // UI components for the form
    public static ComboBox<PropertyTypePOJO> propertyType;
    public static ComboBox<CityPOJO> propertyCity;
    public static ComboBox<ProvincePOJO> propertyProvince;
    public static ComboBox<String> availability;
    public static TextField propertyName;
    public static TextField propertyAddress;
    public static TextField postalCode;

    // Variables for handling update
    public static int updatablePropertyID;
    public static boolean updateFormClicked = false;
    public static Text prompt;

    /**
     * Constructor for PropertyForm class that sets up the UI components and their action handlers.
     */
    public PropertyForm(){

        // Initialize combo boxes and text fields with styles
        propertyType = new ComboBox<>();
        propertyType.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        propertyCity = new ComboBox<>();
        propertyCity.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        propertyProvince = new ComboBox<>();
        propertyProvince.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        availability = new ComboBox<>();
        availability.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());


        propertyName = new TextField();
        propertyName.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        propertyAddress = new TextField();
        propertyAddress.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        postalCode = new TextField();
        postalCode.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());


        HBox titleBox = new HBox();

        Text title = new Text("Manage Properties");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        VBox layout = new VBox(20);
        HBox tabBox = new HBox(20);

        // Create layout and action handlers for buttons and tabs

        Button newPropertyTab = new Button("New Property");
        newPropertyTab.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());
        newPropertyTab.getStyleClass().add("tabButton");

        Button updatePropertyTab = new Button("Update Property");
        updatePropertyTab.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());
        updatePropertyTab.getStyleClass().add("tabButton");

        Button addPropertyButton = new Button("Add Property");
        addPropertyButton.getStyleClass().add("formButton");
        addPropertyButton.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        Button updatePropertyButton = new Button("Update Property");
        updatePropertyButton.getStyleClass().add("formButton");
        updatePropertyButton.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        tabBox.getChildren().addAll(newPropertyTab, updatePropertyTab);
        tabBox.setAlignment(Pos.CENTER_RIGHT);

        titleBox.getChildren().addAll(title, tabBox);
        HBox.setHgrow(tabBox, Priority.ALWAYS);

        newPropertyTab.setOnAction(e -> {
            layout.getChildren().clear();
            layout.getChildren().addAll(titleBox, propertyForm(), addPropertyButton);
            updateFormClicked = false;

            prompt.setText("");
            Animations.translate(addPropertyButton, 1300);
        });

        updatePropertyTab.setOnAction(e -> {
            layout.getChildren().clear();
            layout.getChildren().addAll(titleBox, propertyForm(), updatePropertyButton);
            updateFormClicked = true;

            prompt.setText("Click On A Transaction To Update");
            Animations.translate(updatePropertyButton, 1300);
        });


        addPropertyButton.setOnAction(e ->{

            if(!propertyName.getText().isEmpty() && !propertyAddress.getText().isEmpty() && !postalCode.getText().isEmpty() &&
                    propertyProvince.getSelectionModel().getSelectedItem() != null &&
                    propertyCity.getSelectionModel().getSelectedItem() != null &&
                    propertyType.getSelectionModel().getSelectedItem() != null){

                int available;

                if(availability.getSelectionModel().getSelectedItem().equals("Available")){
                    available = 1;
                }else{
                    available = 0;
                }

                PropertyPOJO newProperty = new PropertyPOJO(0, propertyName.getText(),
                        propertyType.getSelectionModel().getSelectedItem().getPropertyType_id(),
                        propertyProvince.getSelectionModel().getSelectedItem().getProvince_id(),
                        propertyCity.getSelectionModel().getSelectedItem().getCity_id(),
                        propertyAddress.getText(), postalCode.getText().toUpperCase(), available);

                PropertyTable propertyTable = new PropertyTable();

                propertyTable.createProperty(newProperty);
                AllProperties.title.setText(AllProperties.allProperties.getItems().size() + 1 + " Property");

                prompt.setText("New Property Added Successfully!");
                prompt.setStyle("-fx-fill: green; -fx-font-size: 14px; -fx-font-style: italic;");

                AllProperties.refreshPropertyTable();
            }
            else{
                prompt.setText("Can't Add New Property, Try Again, Fields Cannot Be Empty!");
                prompt.setStyle("-fx-fill: #860a0a; -fx-font-size: 14px; -fx-font-style: italic;");
            }
        });


        updatePropertyButton.setOnAction(e ->{

            if(!propertyName.getText().isEmpty() && !propertyAddress.getText().isEmpty() && !postalCode.getText().isEmpty() &&
                    propertyProvince.getSelectionModel().getSelectedItem() != null &&
                    propertyCity.getSelectionModel().getSelectedItem() != null &&
                    propertyType.getSelectionModel().getSelectedItem() != null){


                int available;

                if(availability.getSelectionModel().getSelectedItem().equals("Available")){
                    available = 1;
                }else{
                    available = 0;
                }

                PropertyTable propertyTable = new PropertyTable();


                PropertyPOJO newProperty = new PropertyPOJO(getUpdatablePropertyID(), propertyName.getText(),
                        propertyType.getSelectionModel().getSelectedItem().getPropertyType_id(),
                        propertyProvince.getSelectionModel().getSelectedItem().getProvince_id(),
                        propertyCity.getSelectionModel().getSelectedItem().getCity_id(),
                        propertyAddress.getText(), postalCode.getText().toUpperCase(), available);


                propertyTable.updateProperty(newProperty);

                prompt.setText("Your Transaction Has Been Updated Successfully!");
                prompt.setStyle("-fx-fill: green; -fx-font-size: 14px; -fx-font-style: italic;");

                AllProperties.refreshPropertyTable();

            }
            else{
                prompt.setText("Can't Update Transaction, Try Again, Fields Cannot Be Empty!");
                prompt.setStyle("-fx-fill: #860a0a; -fx-font-size: 14px; -fx-font-style: italic;");
            }

        });


        layout.getChildren().addAll(titleBox, propertyForm(), addPropertyButton);
        this.setCenter(layout);

        layout.setStyle("-fx-padding: 20px 40px 40px 50px");
    }

    /**
     * Generates the form layout for property details, including fields like property name, address, and availability.
     * @return a VBox containing the form elements
     */
    public static VBox propertyForm(){

        // Reset form fields before displaying new form
        propertyType.getSelectionModel().clearSelection();
        propertyCity.getSelectionModel().clearSelection();
        propertyProvince.getSelectionModel().clearSelection();
        availability.getItems().clear();
        propertyName.setText("");
        postalCode.setText("");
        propertyAddress.setText("");

        VBox containerBox = new VBox();
        VBox formBox = new VBox(15);
        HBox streetAndPostalCodeBox = new HBox(30);
        HBox provinceAndCityBox = new HBox(30);
        HBox typeAndAvailabilityBox = new HBox(30);

        VBox nameBox = new VBox(6);
        VBox streetBox = new VBox(6);
        VBox postalCodeBox = new VBox(6);

        VBox typeBox = new VBox(6);
        VBox cityBox = new VBox(6);
        VBox provinceBox = new VBox(6);
        VBox availabilityBox = new VBox(6);


        prompt = new Text();
        prompt.setStyle("-fx-fill: #202469; -fx-font-size: 14px; -fx-font-style: italic;");

        Label nameLabel = new Label("Property Name");
        propertyName.setMaxWidth(200);
        propertyName.setPromptText("Enter property name");
        propertyName.setStyle("-fx-border-color: #202469; -fx-border-width: 1px; -fx-border-radius: 5px;" +
                " -fx-font-size: 14px;");

        Label addressLabel = new Label("Address");
        propertyAddress.setPrefWidth(200);
        propertyAddress.setPromptText("Enter address");
        propertyAddress.setStyle("-fx-border-color: #202469; -fx-border-width: 1px; -fx-border-radius: 5px;" +
                " -fx-font-size: 14px;");

        Label postalCodeLabel = new Label("Postal Code");
        postalCode.setPrefWidth(200);;
        postalCode.setPromptText("Enter postal code");
        postalCode.setStyle("-fx-border-color: #202469; -fx-border-width: 1px; -fx-border-radius: 5px;" +
                " -fx-font-size: 14px;");

        Label cityLabel = new Label("City");
        propertyCity.setItems(FXCollections.observableArrayList(cityTable.getAllCities()));
        propertyCity.setPromptText("Select city");
        propertyCity.setPrefWidth(200);


        Label provinceLabel = new Label("Province");
        propertyProvince.setItems(FXCollections.observableArrayList(provinceTable.getAllProvinces()));
        propertyProvince.setPromptText("Select province");
        propertyProvince.setPrefWidth(200);


        Label propertyTypeLabel = new Label("Property Type");
        propertyType.setItems(FXCollections.observableArrayList(propertyTypeTable.getAllPropertyTypes()));
        propertyType.setPromptText("Select type");
        propertyType.setPrefWidth(200);

        Label availabilityLabel = new Label("Availability");
        availability.getItems().addAll("Available", "Not Available");
        availability.getSelectionModel().selectFirst();
        availability.setPrefWidth(200);


        nameBox.getChildren().addAll(nameLabel, propertyName);
        streetBox.getChildren().addAll(addressLabel, propertyAddress);
        postalCodeBox.getChildren().addAll(postalCodeLabel, postalCode);

        cityBox.getChildren().addAll(cityLabel, propertyCity);
        provinceBox.getChildren().addAll(provinceLabel, propertyProvince);
        typeBox.getChildren().addAll(propertyTypeLabel, propertyType);
        availabilityBox.getChildren().addAll(availabilityLabel, availability);


        streetAndPostalCodeBox.getChildren().addAll(streetBox, postalCodeBox);
        provinceAndCityBox.getChildren().addAll(provinceBox, cityBox);
        typeAndAvailabilityBox.getChildren().addAll(typeBox, availabilityBox);


        formBox.getChildren().addAll(prompt, nameBox, streetAndPostalCodeBox, provinceAndCityBox, typeAndAvailabilityBox);

        containerBox.getChildren().addAll(formBox);
        containerBox.setAlignment(Pos.TOP_LEFT);

        animate(nameBox, streetAndPostalCodeBox, provinceAndCityBox, typeAndAvailabilityBox);

        return containerBox;

    }

    /**
     * Populates the form fields with details of the property to be updated.
     * This method is used when the user clicks on a property to load its details
     * into the form for editing.
     *
     * @param property The PropertyPOJO object containing the details of the property to update.
     */
    public static void getPropertyDetails(PropertyPOJO property){

        if (updateFormClicked){

            String available = "";

            if(property.getAvailability() == 1){
                available = "Available";
            }else {
                available = "Not Available";
            }

            propertyName.setText(property.getName());
            propertyAddress.setText(property.getStreet());
            postalCode.setText(property.getPostal_code());
            propertyType.getSelectionModel().select(propertyTypeTable.getPropertyType(property.getProperty_type_id()));
            propertyProvince.getSelectionModel().select(provinceTable.getProvince(property.getProvince_id()));
            propertyCity.getSelectionModel().select(cityTable.getCityByID(property.getCity_id()));
            availability.getSelectionModel().select(available);

            setUpdatablePropertyID(property.getProperty_id());
        }
    }


    /**
     * Retrieves the property ID for the property to be updated.
     * @return the ID of the property to update
     */
    public static int getUpdatablePropertyID() {
        return updatablePropertyID;
    }

    public static void setUpdatablePropertyID(int updatablePropertyID) {
        PropertyForm.updatablePropertyID = updatablePropertyID;
    }

    /**
     * Animates the given nodes by applying translation effects with staggered delays.
     * This method is used to animate multiple UI nodes, making them appear with a slight delay for a smooth transition effect.
     *
     * @param node1 The first node to animate.
     * @param node2 The second node to animate.
     * @param node3 The third node to animate.
     * @param node4 The fourth node to animate.
     */
    public static void animate(Node node1, Node node2, Node node3, Node node4){

        Animations.translate(node1, 500);
        Animations.translate(node2, 600);
        Animations.translate(node3, 700);
        Animations.translate(node4, 800);
    }
}
