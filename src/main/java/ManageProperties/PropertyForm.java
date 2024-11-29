package ManageProperties;

import ManageRevenue.AllTransaction;
import ManageRevenue.RevenueData;
import ManageRevenue.RevenueForm;
import TableQuery.*;
import com.example.propertypro.Pojo.*;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class PropertyForm extends BorderPane {

    public static CityTable cityTable = new CityTable();
    public static ProvinceTable provinceTable = new ProvinceTable();
    public static PropertyTypeTable propertyTypeTable = new PropertyTypeTable();


    public static ComboBox<PropertyTypePOJO> propertyType;
    public static ComboBox<CityPOJO> propertyCity;
    public static ComboBox<ProvincePOJO> propertyProvince;
    public static ComboBox<String> availability;
//    public static ComboBox<PropertyPOJO> allProperties;
    public static TextField propertyName;

    public static TextField propertyAddress;
    public static TextField postalCode;
//    public static int updatableTransactionID;
    public static boolean updateFormClicked = false;
    public static Text prompt;
//    public static PropertyTable propertyTable = new PropertyTable();

    public PropertyForm(){

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

        Button newPropertyTab = new Button("New Property");
        newPropertyTab.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());
        newPropertyTab.getStyleClass().add("tabButton");

        Button updatePropertyTab = new Button("Update Property");
        updatePropertyTab.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());
        updatePropertyTab.getStyleClass().add("tabButton");

        Button addTransaction = new Button("Add Property");
        addTransaction.getStyleClass().add("formButton");
        addTransaction.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        Button updateTransactionButton = new Button("Update Property");
        updateTransactionButton.getStyleClass().add("formButton");
        updateTransactionButton.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        tabBox.getChildren().addAll(newPropertyTab, updatePropertyTab);
        tabBox.setAlignment(Pos.CENTER_RIGHT);

        titleBox.getChildren().addAll(title, tabBox);
        HBox.setHgrow(tabBox, Priority.ALWAYS);

        newPropertyTab.setOnAction(e -> {
            layout.getChildren().clear();
            layout.getChildren().addAll(titleBox, transactionForm(), addTransaction);
            updateFormClicked = false;

            prompt.setText("");
        });

        updatePropertyTab.setOnAction(e -> {
            layout.getChildren().clear();
            layout.getChildren().addAll(titleBox, transactionForm(), updateTransactionButton);
            updateFormClicked = true;

            prompt.setText("Click On A Transaction To Update");
        });


        addTransaction.setOnAction(e ->{

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
//
                AllProperties.title.setText(AllProperties.allProperties.getItems().size() + 1 + " Property");
//                RevenueData.addRevenue(amountEntered);
//                RevenueData.addTransactionCount();
//
                prompt.setText("New Property Added Successfully!");
                prompt.setStyle("-fx-fill: green; -fx-font-size: 14px; -fx-font-style: italic;");
            }
            else{
                prompt.setText("Can't Add New Property, Try Again, Fields Cannot Be Empty!");
                prompt.setStyle("-fx-fill: #860a0a; -fx-font-size: 14px; -fx-font-style: italic;");
            }
        });


//        updateTransactionButton.setOnAction(e ->{
//
//            if(!amount.getText().isEmpty() && !allClients.getEditor().getText().isEmpty() && allProperties.getSelectionModel().getSelectedItem() != null ){
//
//                double amountEntered = Double.parseDouble(amount.getText().trim());
//
//                for (ClientPOJO client : clientTable.getAllClient()){
//
//                    String name = client.getFirst_name() + " " + client.getLast_name();
//                    String selectedName = allClients.getEditor().getText();
//
//                    if(name.equals(selectedName)){
//
//                        TransactionPOJO newTransaction = new TransactionPOJO(getUpdatableTransactionID(), amountEntered,
//                                client.getClient_id(),
//                                allProperties.getSelectionModel().getSelectedItem().getProperty_id(),
//                                new Timestamp(System.currentTimeMillis()));
//
//                        TransactionTable transactionTable = new TransactionTable();
//                        transactionTable.updateTransaction(newTransaction);
//                    }
//                }
//
//                prompt.setText("Your Transaction Has Been Updated Successfully!");
//                prompt.setStyle("-fx-fill: green; -fx-font-size: 14px; -fx-font-style: italic;");
//            }
//            else{
//                prompt.setText("Can't Update Transaction, Try Again, Fields Cannot Be Empty!");
//                prompt.setStyle("-fx-fill: #860a0a; -fx-font-size: 14px; -fx-font-style: italic;");
//            }
//
//        });





        layout.getChildren().addAll(titleBox, transactionForm(), addTransaction);
        this.setCenter(layout);

        layout.setStyle("-fx-padding: 10px 50px 40px 50px");
    }

    public static VBox transactionForm(){

        propertyType.getSelectionModel().clearSelection();
        propertyCity.getSelectionModel().clearSelection();
        propertyProvince.getSelectionModel().clearSelection();
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

        return containerBox;

    }

//    public static void getTransactionDetails(TransactionPOJORefined transaction){
//
//        if (updateFormClicked){
//
//            amount.setText(transaction.getAmount() + "");
//
//            ClientPOJO updatableClient = null;
//            PropertyPOJO updatableProperty = null;
//
//
//            for (ClientPOJO client : clientTable.getAllClient()){
//                String name = client.getFirst_name() + " " + client.getLast_name();
//                String selectedName = transaction.getClient_id();
//
//                if(name.equals(selectedName)){
//                    updatableClient = client;
//                }
//            }
//
//            for (PropertyPOJO property : propertyTable.getAllPropertyRaw()){
//
//                String name = property.getName();
//                String selectedName = transaction.getProperty_id();
//
//                if(name.equals(selectedName)){
//                    updatableProperty = property;
//                }
//            }
//
//            allClients.getSelectionModel().select(updatableClient);
//            allProperties.getSelectionModel().select(updatableProperty);
//
//        }
//    }

//    public static int getUpdatableTransactionID() {
//        return updatableTransactionID;
//    }
//
//    public static void setUpdatableTransactionID(int updatableTransactionID) {
//        RevenueForm.updatableTransactionID = updatableTransactionID;
//    }
}
