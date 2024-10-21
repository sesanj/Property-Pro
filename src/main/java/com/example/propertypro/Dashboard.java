package com.example.propertypro;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class Dashboard extends BorderPane {
    Dashboard(){
        Text titleDas = new Text("DashBord");
        titleDas.setStyle("-fx-font-size:22px;");

        this.setCenter(titleDas);
    }
}
