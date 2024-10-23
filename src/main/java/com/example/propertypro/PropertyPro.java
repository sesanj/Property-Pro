package com.example.propertypro;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * The PropertyPro class serves as the main entry point for the Property Pro application.
 * It extends the JavaFX Application class and sets up the main window (Stage) and scene.
 */
public class PropertyPro extends Application {

    /**
     * The start method is the entry point of any JavaFX application.
     * It sets up the primary stage and scene, and initializes the UI by adding the LogIn component.
     *
     * @param primaryStage the main window (stage) where the application will be displayed.
     */
    @Override
    public void start(Stage primaryStage) {

        // Root container to hold the UI components
        StackPane root = new StackPane();

        // Create an instance of the LogIn screen to be displayed in the application
        LogIn logIn = new LogIn();

        // Add the LogIn component to the root container
        root.getChildren().add(logIn);

        // Create a scene with the root container, setting the width and height of the window
        Scene scene = new Scene(root, 1400, 800);

        // Set the title of the primary stage (window)
        primaryStage.setTitle("Property Pro");

        // Set the scene for the primary stage and display it
        primaryStage.setScene(scene);
        primaryStage.show();

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