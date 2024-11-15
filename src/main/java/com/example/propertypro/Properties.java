package com.example.propertypro;

import ManageProperties.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Properties extends BorderPane {

    Properties() {

        GridPane layout = new GridPane();

        AllProperties allProperties = new AllProperties();
        allProperties.setPrefSize(675, 450);
        allProperties.setStyle("-fx-background-color: green; -fx-color: white;");

        PropertyData propertyData = new PropertyData();
        propertyData.setPrefSize(675, 450);
        propertyData.setStyle("-fx-background-color: red; -fx-color: white;");


        PropertyDisplay propertyDisplay = new PropertyDisplay();
        propertyDisplay.setPrefSize(675, 450);
        propertyDisplay.setStyle("-fx-background-color: blue; -fx-color: white;");

        PropertyForm propertyForm = new PropertyForm();
        propertyForm.setPrefSize(675, 450);
        propertyForm.setStyle("-fx-background-color: purple; -fx-color: white;");

        PropertyNav propertyNav = new PropertyNav();


        this.setTop(propertyNav);

        layout.add(propertyDisplay, 0, 0);
        layout.add(propertyForm, 0 , 1);
        layout.add(allProperties, 1, 0);
        layout.add(propertyData, 1, 1);

        this.setCenter(layout);



    }
}