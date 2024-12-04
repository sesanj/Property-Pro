package com.example.propertypro;

import ManageProperties.*;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * Represents the properties management interface of the property management system.
 * It displays sections such as All Properties, Property Data, Property Display, and Property Form.
 */
public class Properties extends BorderPane {

    /**
     * Constructs the properties management interface.
     * Initializes and sets up the layout with various sections such as
     * AllProperties, PropertyData, PropertyDisplay, and PropertyForm.
     */
    Properties() {

        // Create the main layout using GridPane
        GridPane layout = new GridPane();

        // Initialize and set up the AllProperties section
        AllProperties allProperties = new AllProperties();
        allProperties.setPrefSize(780, 450);
        allProperties.setStyle("-fx-background-color: white;");

        // Initialize and set up the PropertyData section
        PropertyData propertyData = new PropertyData();
        propertyData.setPrefSize(780, 450);
        propertyData.setStyle("-fx-background-color: white;");

        // Initialize and set up the PropertyDisplay section
        PropertyDisplay propertyDisplay = new PropertyDisplay();
        propertyDisplay.setPrefSize(570, 450);
        propertyDisplay.setStyle("-fx-background-color: white;");

        // Initialize and set up the PropertyForm section
        PropertyForm propertyForm = new PropertyForm();
        propertyForm.setPrefSize(570, 450);
        propertyForm.setStyle("-fx-background-color: white;");

        // Add sections to the layout
        layout.add(propertyDisplay, 0, 0);
        layout.add(propertyForm, 0 , 1);
        layout.add(propertyData, 1, 0);
        layout.add(allProperties, 1, 1);

        // Align the layout to the center of the BorderPane
        layout.setAlignment(Pos.CENTER);

        // Set the layout as the center of the BorderPane
        this.setCenter(layout);
    }
}
