package com.example.propertypro;

import ManageRevenue.AllTransaction;
import ManageRevenue.RevenueChart;
import ManageRevenue.RevenueData;
import ManageRevenue.RevenueForm;
import ManageRevenue.RevenueNav;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Revenue extends BorderPane {

    Revenue() {

        GridPane layout = new GridPane();

        RevenueData revenueData = new RevenueData();
        revenueData.setPrefSize(550, 425);
        revenueData.setStyle("-fx-background-color: white;");


        RevenueForm revenueForm = new RevenueForm();
        revenueForm.setPrefSize(550, 475);
        revenueForm.setStyle("-fx-background-color: purple;");


        RevenueChart revenueChart=new RevenueChart();
        revenueChart.setPrefSize(800, 425);
        revenueChart.setStyle("-fx-background-color: white;");


        AllTransaction allTransaction=new AllTransaction();
        allTransaction.setPrefSize(800, 475);
        allTransaction.setStyle("-fx-background-color: white;");

        RevenueNav revenueNav = new RevenueNav();


        //this.setTop(revenueNav);

        layout.add(revenueData, 0, 0);
        layout.add(revenueChart, 1, 0);
        layout.add(revenueForm, 0 , 1);
        layout.add(allTransaction, 1, 1);

        layout.setAlignment(Pos.CENTER);

        this.setCenter(layout);
    }
}
