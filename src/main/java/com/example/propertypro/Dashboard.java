package com.example.propertypro;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class Dashboard extends BorderPane {
    Dashboard(){
        BorderPane layout = new BorderPane();
        Text logoText = new Text("Property Pro");
        Text overviewText = new Text("Overview");
        Text revenueText = new Text("Revenue");
        Text clientsText = new Text("Manage Clients");
        Text propertyText = new Text("Manage Properties");
        Text settingsText = new Text("Settings");
        Text logoutText = new Text("Log Out");
    }
}
