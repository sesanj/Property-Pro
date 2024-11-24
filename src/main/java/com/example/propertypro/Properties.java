package com.example.propertypro;

import ManageProperties.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Properties extends BorderPane {

    Properties() {

        GridPane layout = new GridPane();

        AllProperties allProperties = new AllProperties();
        allProperties.setPrefSize(800, 450);
        allProperties.setStyle("-fx-background-color: white;");

        PropertyData propertyData = new PropertyData();
        propertyData.setPrefSize(800, 450);
        propertyData.setStyle("-fx-background-color: red; -fx-color: white;");


        PropertyDisplay propertyDisplay = new PropertyDisplay();
        propertyDisplay.setPrefSize(550, 450);
        propertyDisplay.setStyle("-fx-background-color: white;");

        PropertyForm propertyForm = new PropertyForm();
        propertyForm.setPrefSize(550, 450);
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