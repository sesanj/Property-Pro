package com.example.propertypro;

import Settings.City;
import Settings.Credits;
import Settings.Help_Support;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Setting extends BorderPane {

    Setting() {

// Text labels for navigation items
        Text creditText = new Text("Credits");
        Text help_SupportText = new Text("Help & Support");

        Text cityText = new Text("City");


        // Styles for active and inactive navigation items
        String navInactiveStyle = "-fx-font-size: 16px;" + "-fx-fill: black;" + "-fx-font-family: Ariel;";
        String navActiveStyle = "-fx-font-size: 16px;" + "-fx-fill: #8dd9ff;" + "-fx-font-family: Ariel;";

        // Set inactive style for all text labels initially
        creditText.setStyle(navInactiveStyle);
        help_SupportText.setStyle(navInactiveStyle);
        cityText.setStyle(navInactiveStyle);



        // VBox for the entire navigation layout
        VBox navBar = new VBox(180);
        VBox logoAndNavBox = new VBox(80); // Holds logo and main navigation items
        VBox mainNav = new VBox(30); // Main navigation sections
        VBox settingsAndLogout = new VBox(120); // Settings and logout at bottom

//


        HBox credit = new HBox(20);
        credit.getChildren().add( creditText);
        credit.setAlignment(Pos.CENTER_LEFT);


        HBox help_support = new HBox(20);
        help_support.getChildren().addAll(help_SupportText);
        help_support.setAlignment(Pos.CENTER_LEFT);


        HBox city = new HBox(20);
        city.getChildren().addAll(cityText);
        city.setAlignment(Pos.CENTER_LEFT);



        // Add navigation elements to VBox containers for positioning
        logoAndNavBox.getChildren().addAll( mainNav);
        mainNav.getChildren().addAll(credit,help_support,city);

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
            help_SupportText.setStyle(navInactiveStyle);
        });

        help_support.setOnMouseClicked(e -> {
            this.setCenter(new Help_Support());
            help_SupportText.setStyle(navActiveStyle);
            cityText.setStyle(navInactiveStyle);
            creditText.setStyle(navInactiveStyle);
        });



        city.setOnMouseClicked(e -> {
            this.setCenter(new City());
            cityText.setStyle(navActiveStyle);
            creditText.setStyle(navInactiveStyle);
            help_SupportText.setStyle(navInactiveStyle);
        });


    }
    }

