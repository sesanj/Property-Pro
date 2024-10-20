package com.example.propertypro;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LogIn extends Application {
    @Override
    public void start(Stage primaryStage){

        BorderPane root = new BorderPane();
        VBox container = new VBox(30);

        VBox titleContainer = new VBox(20);
        Text title = new Text("Sign In");
        Text description = new Text("Please enter your database credentials to sign in");
        titleContainer.getChildren().addAll(title, description);
        titleContainer.setAlignment(Pos.CENTER);

        VBox formContainer = new VBox(15);
        TextField databaseName = new TextField();
        databaseName.setPromptText("Database Name");
        databaseName.setMaxWidth(300);
        databaseName.setMinHeight(30);

        TextField username = new TextField();
        username.setPromptText("Username");
        username.setMaxWidth(300);
        username.setMinHeight(30);

        TextField password = new TextField();
        password.setPromptText("Password");
        password.setMaxWidth(300);
        password.setMinHeight(30);

        formContainer.getChildren().addAll(databaseName, username, password);
        formContainer.setAlignment(Pos.CENTER);

        Button signIn = new Button("Text Connection");


        container.getChildren().addAll(titleContainer, formContainer, signIn);
        container.setAlignment(Pos.CENTER);


        root.setCenter(container);

        Scene scene = new Scene(root, 1400, 800);
        primaryStage.setTitle("Property Pro");
        primaryStage.setScene(scene);
        primaryStage.show();


        root.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}