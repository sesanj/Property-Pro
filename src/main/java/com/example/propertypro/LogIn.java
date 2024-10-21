package com.example.propertypro;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LogIn extends Application {
    @Override
    public void start(Stage primaryStage){

        BorderPane root = new BorderPane();

        HBox mainContainer = new HBox();
        VBox signInContainer = new VBox(30);
        signInContainer.setMinWidth(600);

        VBox titleContainer = new VBox(20);

        Image logo = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView logoBox = new ImageView(logo);
        logoBox.setFitHeight(500);
        logoBox.setFitWidth(500);

        HBox logoContainer = new HBox();
        logoContainer.getChildren().add(logoBox);
        logoContainer.setMinWidth(600);
        logoContainer.setAlignment(Pos.CENTER);


        Text title = new Text("Sign In");
        title.setStyle("-fx-font-size: 30px; -fx-font-family: 'Roboto'; -fx-fill: #202469; -fx-font-weight: bold;");

        Text description = new Text("Enter database credentials to Sign In");
        description.setStyle("-fx-font-size: 17px; -fx-font-family: 'Arial'");

        titleContainer.getChildren().addAll(title, description);
        titleContainer.setAlignment(Pos.CENTER);

        VBox formContainer = new VBox(18);

        String formStyle = "-fx-border-color: #202469; -fx-border-width: 1px;" +
                " -fx-border-radius: 20px; -fx-background-radius: 20px;";

        TextField databaseName = new TextField();
        databaseName.setPromptText("Database Name");
        databaseName.setStyle(formStyle);
        databaseName.setMaxWidth(300);
        databaseName.setMinHeight(35);

        TextField username = new TextField();
        username.setPromptText("Username");
        username.setStyle(formStyle);
        username.setMaxWidth(300);
        username.setMinHeight(35);

        TextField password = new TextField();
        password.setPromptText("Password");
        password.setStyle(formStyle);
        password.setMaxWidth(300);
        password.setMinHeight(35);


        formContainer.getChildren().addAll(databaseName, username, password);
        formContainer.setAlignment(Pos.CENTER);

        Button signIn = new Button("Test Connection");
        signIn.setStyle("-fx-background-color: #202469; -fx-text-fill: white; -fx-font-size: 15px;" +
                "-fx-font-weight: bold; -fx-padding: 10px 22px; -fx-font-family: 'Roboto';" +
                "-fx-background-radius: 20px");


        signInContainer.getChildren().addAll(titleContainer, formContainer, signIn);
        signInContainer.setAlignment(Pos.CENTER);

        mainContainer.getChildren().addAll(logoContainer, signInContainer);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setStyle("-fx-background-image: url('/back.jpg');" +
                "-fx-background-size: cover;" +
                "-fx-background-position: center;");

        root.setCenter(mainContainer);

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