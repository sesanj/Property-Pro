package com.example.propertypro;

import ManageRevenue.AllTransaction;
import ManageRevenue.RevenueChart;
import ManageRevenue.RevenueData;
import ManageRevenue.RevenueForm;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * Represents the revenue management interface in the property management system.
 * It displays sections such as Revenue Data, Revenue Form, Revenue Chart, and All Transactions.
 */
public class Revenue extends BorderPane {

    /**
     * Constructs the revenue management interface.
     * Initializes and sets up the layout with various sections including
     * RevenueData, RevenueChart, RevenueForm, and AllTransaction.
     */
    Revenue() {

        // Create the main layout using GridPane
        GridPane layout = new GridPane();

        // Initialize and set up the RevenueData section
        RevenueData revenueData = new RevenueData();
        revenueData.setPrefSize(550, 440);
        revenueData.setStyle("-fx-background-color: white;");

        // Initialize and set up the RevenueForm section
        RevenueForm revenueForm = new RevenueForm();
        revenueForm.setPrefSize(550, 460);
        revenueForm.setStyle("-fx-background-color: white;");

        // Initialize and set up the RevenueChart section
        RevenueChart revenueChart = new RevenueChart();
        revenueChart.setPrefSize(800, 440);
        revenueChart.setStyle("-fx-background-color: white;");

        // Initialize and set up the AllTransaction section
        AllTransaction allTransaction = new AllTransaction();
        allTransaction.setPrefSize(800, 460);
        allTransaction.setStyle("-fx-background-color: white;");

        // Add sections to the layout
        layout.add(revenueData, 0, 0);
        layout.add(revenueChart, 1, 0);
        layout.add(revenueForm, 0, 1);
        layout.add(allTransaction, 1, 1);

        // Align the layout to the center of the BorderPane
        layout.setAlignment(Pos.CENTER);

        // Set the layout as the center of the BorderPane
        this.setCenter(layout);
    }
}
