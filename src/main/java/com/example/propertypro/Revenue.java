package com.example.propertypro;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class Revenue extends BorderPane {

    Revenue() {

        Text titleRev = new Text("Revenue");
        titleRev.setStyle("-fx-font-size:22px;");

        this.setCenter(titleRev);
    }
}
