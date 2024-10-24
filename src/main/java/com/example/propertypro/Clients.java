package com.example.propertypro;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class Clients extends BorderPane {

    Clients() {

        Text titleCli = new Text("Clients");
        titleCli.setStyle("-fx-font-size:22px;");

        this.setCenter(titleCli);
    }
}