package com.example.propertypro;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class Overview extends BorderPane {

     Overview() {

        Text title = new Text("Overview");
        title.setStyle("-fx-font-size:22px;");

        this.setCenter(title);
    }
}
