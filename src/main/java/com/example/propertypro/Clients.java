package com.example.propertypro;

import ManageClient.*;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class Clients extends BorderPane {

    Clients() {

        GridPane layout = new GridPane();


        AllClients allClients = new AllClients();
        allClients.setPrefSize(900, 450);
        allClients.setStyle("-fx-background-color: white;");


        ClientData clientData = new ClientData();
        clientData.setPrefSize(450, 450);
        clientData.setStyle("-fx-background-color: #e9eaff;");

      //  ClientChart clientChart = new ClientChart();
       // clientChart.setPrefSize(150, 350);

        ClientForm clientForm = new ClientForm();
        clientForm.setPrefSize(450, 450);
        clientForm.setStyle("-fx-background-color: #e9eaff;");


        ClientTransactions clientTransactions = new ClientTransactions();
        clientTransactions.setPrefSize(750, 450);
        clientTransactions.setStyle("-fx-background-color: white;");

      //  HBox combinedDataAndChart = new HBox();
       // combinedDataAndChart.setPrefSize(800, 450);
      //  combinedDataAndChart.getChildren().addAll( clientChart ,clientTransactions);
      //  combinedDataAndChart.setStyle("-fx-background-color: white;");


        layout.add(clientTransactions,1, 1 );
        layout.add(clientData, 0, 0);
        layout.add(clientForm, 0, 1);
        layout.add(allClients,1, 0 );


        layout.setAlignment(Pos.CENTER);

        this.setCenter(layout);
    }
}
