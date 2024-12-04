package com.example.propertypro;

import Overview.Summary;
import Overview.CashFlow;
import Overview.PropertySum;
import Overview.TopUsers;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * Represents the overview dashboard of the property management system.
 * It displays sections such as Summary, CashFlow, Property Summary, and Top Users.
 */
public class Overview extends BorderPane {

    /**
     * Constructs the overview dashboard interface.
     * Initializes and sets up the layout with various sections such as
     * Summary, CashFlow, PropertySum, and TopUsers.
     */
    Overview() {

        // Create the main layout using GridPane
        GridPane layout = new GridPane();

        // Initialize and set up the Summary section
        Summary summary = new Summary();
        summary.setPrefSize(550, 450);
        summary.setStyle("-fx-background-color: #e9eaff;");

        // Initialize and set up the CashFlow section
        CashFlow cashFlow = new CashFlow();
        cashFlow.setPrefSize(800, 450);
        cashFlow.setStyle("-fx-background-color: white;");

        // Initialize and set up the PropertySum section
        PropertySum propertySum = new PropertySum();
        propertySum.setPrefSize(550, 450);
        propertySum.setStyle("-fx-background-color: #e9eaff;");

        // Initialize and set up the TopUsers section
        TopUsers topClients = new TopUsers();
        topClients.setPrefSize(800, 450);
        topClients.setStyle("-fx-background-color: white;");

        // Add sections to the layout
        layout.add(summary, 0, 0);
        layout.add(cashFlow, 1, 0);
        layout.add(propertySum, 0, 1);
        layout.add(topClients, 1, 1);

        // Align the layout to the center of the BorderPane
        layout.setAlignment(Pos.CENTER);

        // Set the layout as the center of the BorderPane
        this.setCenter(layout);
    }
}
