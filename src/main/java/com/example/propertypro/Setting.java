package com.example.propertypro;

import Settings.City;
import Settings.Credits;
import Settings.PropertyType;
import Settings.Province;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Setting extends BorderPane {

    Setting() {
// Text labels for navigation items
        Text creditText = new Text("Credits");
        Text propertyTypeText = new Text("Property Type");
        Text provinceText = new Text("Province");
        Text cityText = new Text("City");


        // Styles for active and inactive navigation items
        String navInactiveStyle = "-fx-font-size: 16px;" + "-fx-fill: black;" + "-fx-font-family: Ariel;";
        String navActiveStyle = "-fx-font-size: 16px;" + "-fx-fill: #8dd9ff;" + "-fx-font-family: Ariel;";

        // Set inactive style for all text labels initially
        creditText.setStyle(navInactiveStyle);
        propertyTypeText.setStyle(navInactiveStyle);
        provinceText.setStyle(navInactiveStyle);
        cityText.setStyle(navInactiveStyle);



        // VBox for the entire navigation layout
        VBox navBar = new VBox(180);
        VBox logoAndNavBox = new VBox(80); // Holds logo and main navigation items
        VBox mainNav = new VBox(30); // Main navigation sections
        VBox settingsAndLogout = new VBox(120); // Settings and logout at bottom

//

        // Revenue navigation item
        HBox credit = new HBox(20);
        credit.getChildren().add( creditText);
        credit.setAlignment(Pos.CENTER_LEFT);

        // Clients navigation item
        HBox propertyType = new HBox(20);
        propertyType.getChildren().addAll(propertyTypeText);
        propertyType.setAlignment(Pos.CENTER_LEFT);

        // Properties navigation item
        HBox province = new HBox(20);
        province.getChildren().addAll(provinceText);
        province.setAlignment(Pos.CENTER_LEFT);

        // Settings navigation item
        HBox city = new HBox(20);
        city.getChildren().addAll(cityText);
        city.setAlignment(Pos.CENTER_LEFT);



        // Add navigation elements to VBox containers for positioning
        logoAndNavBox.getChildren().addAll( mainNav);
        mainNav.getChildren().addAll(credit,propertyType,province,city);

        // Set up the complete navigation bar layout
        navBar.getChildren().addAll(logoAndNavBox, settingsAndLogout);
        navBar.setMinWidth(250);
        navBar.setStyle("-fx-background-color: #e9eaff;" + "-fx-padding: 40px;");
        navBar.setAlignment(Pos.CENTER_LEFT);

        // Set default view to "Overview" section in center pane
        this.setCenter(new Credits());
        this.setLeft(navBar);

        // Click event handlers for each navigation item
        credit.setOnMouseClicked(e -> {
            this.setCenter(new Credits());
            creditText.setStyle(navActiveStyle);
            cityText.setStyle(navInactiveStyle);
            provinceText.setStyle(navInactiveStyle);
            propertyTypeText.setStyle(navInactiveStyle);
        });

        propertyType.setOnMouseClicked(e -> {
            this.setCenter(new PropertyType());
            propertyTypeText.setStyle(navActiveStyle);
            cityText.setStyle(navInactiveStyle);
            provinceText.setStyle(navInactiveStyle);
            creditText.setStyle(navInactiveStyle);
        });

        province.setOnMouseClicked(e -> {
            this.setCenter(new Province());
            provinceText.setStyle(navActiveStyle);
            cityText.setStyle(navInactiveStyle);
            creditText.setStyle(navInactiveStyle);
            propertyTypeText.setStyle(navInactiveStyle);
        });

        city.setOnMouseClicked(e -> {
            this.setCenter(new City());
            cityText.setStyle(navActiveStyle);
            creditText.setStyle(navInactiveStyle);
            provinceText.setStyle(navInactiveStyle);
            propertyTypeText.setStyle(navInactiveStyle);
        });


    }
    }

