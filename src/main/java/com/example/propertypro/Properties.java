package com.example.propertypro;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class Properties extends BorderPane {

    Properties() {

        Text titlePro = new Text("Properties");
        titlePro.setStyle("-fx-font-size:22px;");

        this.setCenter(titlePro);
    }
}