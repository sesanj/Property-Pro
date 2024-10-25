package com.example.propertypro;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.*;

/**
 * This code sets up the styles and icons for our navigation menu
 *  the element for the navigation are  overview, revenue,setting, property pro, clients.
 *  Icons (images) are loaded for each section of the menu overview, revenue, clients, and each icon is displayed using an ImageView.
 *  The size of each icon is set to 25x25 pixels using setFitWidth() and setFitHeight() methods.
 */


public class Dashboard extends BorderPane {
   public Dashboard(){
        BorderPane layout = new BorderPane();
        Text logoText = new Text("Property Pro");
        Text overviewText = new Text("Overview");
        Text revenueText = new Text("Revenue");
        Text clientsText = new Text("Manage Clients");
        Text propertyText = new Text("Manage Properties");
        Text settingsText = new Text("Settings");
        Text logoutText = new Text("Log Out");


        String navInactiveStyle = "-fx-font-size: 16px;" + "-fx-fill: white;" + "-fx-font-family: Ariel;";
        String navActiveStyle = "-fx-font-size: 16px;" + "-fx-fill: #8dd9ff;" + "-fx-font-family: Ariel;";

        overviewText.setStyle(navInactiveStyle);
        revenueText.setStyle(navInactiveStyle);
        clientsText.setStyle(navInactiveStyle);
        propertyText.setStyle(navInactiveStyle);
        settingsText.setStyle(navInactiveStyle);
        logoutText.setStyle(navInactiveStyle);
        logoText.setStyle(navInactiveStyle);

        Image overviewIcon = new Image(getClass().getResourceAsStream("com/example/propertypro/overview.png"));
        ImageView overviewIconBox = new ImageView(overviewIcon);
        overviewIconBox.setFitWidth(25);
        overviewIconBox.setFitHeight(25);

        Image revenueIcon = new Image(getClass().getResourceAsStream("revenue.png"));
        ImageView revenueIconBox = new ImageView(revenueIcon);
        revenueIconBox.setFitWidth(25);
        revenueIconBox.setFitHeight(25);

        Image clientIcon = new Image(getClass().getResourceAsStream("clients.png"));
        ImageView clientIconBox = new ImageView(clientIcon);
        clientIconBox.setFitWidth(25);
        clientIconBox.setFitHeight(25);

        Image propertyIcon = new Image(getClass().getResourceAsStream("properties.png"));
        ImageView propertyIconBox = new ImageView(propertyIcon);
        propertyIconBox.setFitWidth(25);
        propertyIconBox.setFitHeight(25);

        Image settingsIcon = new Image(getClass().getResourceAsStream("settings.png"));
        ImageView settingsIconBox = new ImageView(settingsIcon);
        settingsIconBox.setFitWidth(25);
        settingsIconBox.setFitHeight(25);

        Image logOutIcon = new Image(getClass().getResourceAsStream("logout.png"));
        ImageView logOutIconBox = new ImageView(logOutIcon);
        logOutIconBox.setFitWidth(25);
        logOutIconBox.setFitHeight(25);
    }
}
