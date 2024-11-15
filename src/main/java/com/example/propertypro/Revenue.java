package com.example.propertypro;

import ManageProperties.*;
import ManageRevenue.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Revenue extends BorderPane {

    Revenue() {

        GridPane layout = new GridPane();

        AllTransaction allTransaction=new AllTransaction();
        allTransaction.setPrefSize(675, 450);
        allTransaction.setStyle("-fx-background-color: green; -fx-color: white;");

        RevenueChart revenueChart=new RevenueChart();
        revenueChart.setPrefSize(675, 450);
        revenueChart.setStyle("-fx-background-color: red; -fx-color: white;");


        RevenueData revenueData = new RevenueData();
        revenueData.setPrefSize(675, 450);
        revenueData.setStyle("-fx-background-color: blue; -fx-color: white;");

        RevenueForm revenueForm = new RevenueForm();
        revenueForm.setPrefSize(675, 450);
        revenueForm.setStyle("-fx-background-color: purple; -fx-color: white;");

        RevenueNav revenueNav = new RevenueNav();


        this.setTop(revenueNav);

        layout.add(revenueData, 0, 0);
        layout.add(revenueChart, 0 , 1);
        layout.add(revenueForm, 1, 0);
        layout.add(allTransaction, 1, 1);

        this.setCenter(layout);
    }
}
