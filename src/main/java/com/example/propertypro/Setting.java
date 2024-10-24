package com.example.propertypro;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class Setting extends BorderPane {

    Setting() {

        Text titleSet = new Text("Setting");
        titleSet.setStyle("-fx-font-size:22px;");

        this.setCenter(titleSet);
    }
}
