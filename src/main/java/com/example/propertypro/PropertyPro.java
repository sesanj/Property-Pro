package com.example.propertypro;

import Database.Database;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * The PropertyPro class serves as the main entry point for the Property Pro application.
 * It extends the JavaFX Application class and sets up the main window (Stage) and scene.
 */
public class PropertyPro extends Application {

    // Root container to hold the UI components
    public static StackPane root = new StackPane();

    /**
     * The start method is the entry point of any JavaFX application.
     * It sets up the primary stage and scene, and initializes the UI by adding the LogIn component.
     *
     * @param primaryStage the main window (stage) where the application will be displayed.
     */
    @Override
    public void start(Stage primaryStage) {

        // Create an instance of the LogIn screen to be displayed in the application
        LogIn login = new LogIn();
        Dashboard dashboard = new Dashboard();

        // Add the LogIn component to the root container
        root.getChildren().add(login);

        // Create a scene with the root container, setting the width and height of the window
        Scene scene = new Scene(root, 1600, 900);

        // Set the title of the primary stage (window)
        primaryStage.setTitle("Property Pro");

        // Set the scene for the primary stage and display it
        primaryStage.setScene(scene);
        primaryStage.show();

        if(Database.connectionSuccessful() && LogIn.credentials.exists()){
            root.getChildren().clear();
            root.getChildren().add(dashboard);
        }

        // Request focus to the root container when the application starts
        root.requestFocus();
    }

    /**
     * The main method is the entry point for the application when it's launched.
     * It calls the launch() method to start the JavaFX application lifecycle.
     *
     * @param args the command-line arguments (if any).
     */
    public static void main(String[] args) {
        launch();
    }
}
