package com.example.propertypro;

import ManageClient.*;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * Represents a client management interface, with sections for viewing clients,
 * entering new client data, and managing client transactions.
 */
public class Clients extends BorderPane {

    /**
     * Constructs the client management interface.
     * Initializes the layout and sets up various sections such as
     * All Clients, Client Data, Client Form, and Client Transactions.
     */
    Clients() {

        // Create the main layout using GridPane
        GridPane layout = new GridPane();

        // Initialize and set up AllClients section
        AllClients allClients = new AllClients();
        allClients.setPrefSize(800, 500);
        allClients.setStyle("-fx-background-color: white;");

        // Initialize and set up ClientData section
        ClientData clientData = new ClientData();
        clientData.setPrefSize(550, 500);
        clientData.setStyle("-fx-background-color: white;");

        // Initialize and set up ClientForm section
        ClientForm clientForm = new ClientForm();
        clientForm.setPrefSize(550, 400);
        clientForm.setStyle("-fx-background-color: white;");

        // Initialize and set up ClientTransactions section
        ClientTransactions clientTransactions = new ClientTransactions();
        clientTransactions.setPrefSize(800, 400);
        clientTransactions.setStyle("-fx-background-color: white;");

        // Add sections to the layout
        layout.add(clientTransactions, 1, 1);
        layout.add(clientData, 0, 0);
        layout.add(clientForm, 0, 1);
        layout.add(allClients, 1, 0);

        // Align the layout to the center of the BorderPane
        layout.setAlignment(Pos.CENTER);

        // Set the layout as the center of the BorderPane
        this.setCenter(layout);
    }
}
