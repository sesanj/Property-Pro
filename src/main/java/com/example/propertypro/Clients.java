package com.example.propertypro;

import ManageClient.*;
import ManageRevenue.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Clients extends BorderPane {

    Clients() {

        GridPane layout = new GridPane();

        AllClients allClients=new AllClients();
        allClients.setPrefSize(675, 450);
        allClients.setStyle("-fx-background-color: green; -fx-color: white;");

        ClientChart clientChart=new ClientChart();
        clientChart.setPrefSize(450, 450);
        clientChart.setStyle("-fx-background-color: red; -fx-color: white;");


        ClientData clientData = new ClientData();
        clientData.setPrefSize(450, 450);
        clientData.setStyle("-fx-background-color: blue; -fx-color: white;");

        ClientForm clientForm = new ClientForm();
        clientForm.setPrefSize(675, 450);
        clientForm.setStyle("-fx-background-color: purple; -fx-color: white;");

        ClientTransactions clientTransactions=new ClientTransactions();
        clientTransactions.setPrefSize(450, 450);
        clientTransactions.setStyle("-fx-background-color: yellow; -fx-color: white;");

        layout.add(clientData, 0, 0);
        layout.add(clientChart, 1 , 0);
        layout.add(clientTransactions,2,0);
        layout.add(clientForm, 0, 1);
        layout.add(allClients, 1,1);

        this.setCenter(layout);
    }
}