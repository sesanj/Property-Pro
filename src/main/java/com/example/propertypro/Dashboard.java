package com.example.propertypro;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The Dashboard class creates a navigation layout for the application.
 * It includes text labels and icons for different sections of the application, such as
 * Overview, Revenue, Manage Clients, Manage Properties, Settings, and Log Out.
 * Each menu item is styled and includes an icon to enhance the user experience.
 * When a menu item is clicked, the center pane is updated to display the corresponding content.
 */
public class Dashboard extends BorderPane {

     /**
      * Constructor that initializes the dashboard navigation menu with icons and labels.
      * Sets the navigation menu style and assigns click event listeners for each menu item,
      * updating the center pane with the appropriate content.
      */
     public Dashboard() {

          // Text labels for navigation items
          Text overviewText = new Text("Overview");
          Text revenueText = new Text("Revenue");
          Text clientsText = new Text("Manage Clients");
          Text propertyText = new Text("Manage Properties");
          Text settingsText = new Text("Settings");
          Text logoutText = new Text("Log Out");

          // Styles for active and inactive navigation items
          String navInactiveStyle = "-fx-font-size: 16px;" + "-fx-fill: white;" + "-fx-font-family: Ariel;";
          String navActiveStyle = "-fx-font-size: 16px;" + "-fx-fill: #8dd9ff;" + "-fx-font-family: Ariel;";

          // Set inactive style for all text labels initially
          overviewText.setStyle(navInactiveStyle);
          revenueText.setStyle(navInactiveStyle);
          clientsText.setStyle(navInactiveStyle);
          propertyText.setStyle(navInactiveStyle);
          settingsText.setStyle(navInactiveStyle);
          logoutText.setStyle(navInactiveStyle);

          // Load and set the application logo
          Image logo = new Image(getClass().getResourceAsStream("logo3.png"));
          ImageView logoView = new ImageView(logo);
          logoView.setFitWidth(160);
          logoView.setFitHeight(80);

          // Load and set icons for each navigation item
          Image overviewIcon = new Image(getClass().getResourceAsStream("overview.png"));
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

          // VBox for the entire navigation layout
          VBox navBar = new VBox(180);
          VBox logoAndNavBox = new VBox(80); // Holds logo and main navigation items
          VBox mainNav = new VBox(30); // Main navigation sections
          VBox settingsAndLogout = new VBox(120); // Settings and logout at bottom

          // Logo box alignment
          HBox logoBox = new HBox();
          logoBox.getChildren().addAll(logoView);
          logoBox.setAlignment(Pos.CENTER_LEFT);

          // Overview navigation item
          HBox overview = new HBox(20);
          overview.getChildren().addAll(overviewIconBox, overviewText);
          overview.setAlignment(Pos.CENTER_LEFT);

          // Revenue navigation item
          HBox revenue = new HBox(20);
          revenue.getChildren().addAll(revenueIconBox, revenueText);
          revenue.setAlignment(Pos.CENTER_LEFT);

          // Clients navigation item
          HBox clients = new HBox(20);
          clients.getChildren().addAll(clientIconBox, clientsText);
          clients.setAlignment(Pos.CENTER_LEFT);

          // Properties navigation item
          HBox properties = new HBox(20);
          properties.getChildren().addAll(propertyIconBox, propertyText);
          properties.setAlignment(Pos.CENTER_LEFT);

          // Settings navigation item
          HBox settings = new HBox(20);
          settings.getChildren().addAll(settingsIconBox, settingsText);
          settings.setAlignment(Pos.CENTER_LEFT);

          // Log Out navigation item
          HBox logOut = new HBox(20);
          logOut.getChildren().addAll(logOutIconBox, logoutText);
          logOut.setAlignment(Pos.CENTER_LEFT);

          // Add navigation elements to VBox containers for positioning
          logoAndNavBox.getChildren().addAll(logoView, mainNav);
          mainNav.getChildren().addAll(overview, revenue, clients, properties);
          settingsAndLogout.getChildren().addAll(settings, logOut);

          // Set up the complete navigation bar layout
          navBar.getChildren().addAll(logoAndNavBox, settingsAndLogout);
          navBar.setMinWidth(300);
          navBar.setStyle("-fx-background-color: #1a1b2e;" + "-fx-padding: 40px;");
          navBar.setAlignment(Pos.CENTER_LEFT);

          // Set default view to "Overview" section in center pane
          this.setCenter(new Overview());
          this.setLeft(navBar);

          // Click event handlers for each navigation item
          revenue.setOnMouseClicked(e -> {
               this.setCenter(new Revenue());
               revenueText.setStyle(navActiveStyle);
               overviewText.setStyle(navInactiveStyle);
               clientsText.setStyle(navInactiveStyle);
               propertyText.setStyle(navInactiveStyle);
               settingsText.setStyle(navInactiveStyle);
          });

          overview.setOnMouseClicked(e -> {
               this.setCenter(new Overview());
               revenueText.setStyle(navInactiveStyle);
               overviewText.setStyle(navActiveStyle);
               clientsText.setStyle(navInactiveStyle);
               propertyText.setStyle(navInactiveStyle);
               settingsText.setStyle(navInactiveStyle);
          });

          clients.setOnMouseClicked(e -> {
               this.setCenter(new Clients());
               clientsText.setStyle(navActiveStyle);
               revenueText.setStyle(navInactiveStyle);
               overviewText.setStyle(navInactiveStyle);
               propertyText.setStyle(navInactiveStyle);
               settingsText.setStyle(navInactiveStyle);
          });

          properties.setOnMouseClicked(e -> {
               this.setCenter(new Properties());
               propertyText.setStyle(navActiveStyle);
               revenueText.setStyle(navInactiveStyle);
               overviewText.setStyle(navInactiveStyle);
               clientsText.setStyle(navInactiveStyle);
               settingsText.setStyle(navInactiveStyle);
          });

          settings.setOnMouseClicked(e -> {
               this.setCenter(new Setting());
               settingsText.setStyle(navActiveStyle);
               revenueText.setStyle(navInactiveStyle);
               overviewText.setStyle(navInactiveStyle);
               clientsText.setStyle(navInactiveStyle);
               propertyText.setStyle(navInactiveStyle);
          });

          logOut.setOnMouseClicked(e -> {
               Platform.exit();
          });
     }
}
