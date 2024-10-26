package com.example.propertypro;

import Database.Database;
import Database.DatabaseCredentials;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;

/**
 * The LogIn class represents the UI for a login screen in a JavaFX application.
 * It extends the BorderPane and contains elements like logo, text fields for
 * credentials, and a button to test the connection to a database.
 */
public class LogIn extends BorderPane {

    public static File credentials = new File("credentials.txt");

    /**
     * Constructor for LogIn class.
     * Initializes and sets up the layout of the login screen, including a logo,
     * text fields for user input, and a sign-in button.
     */
    public LogIn(){

        // Main container for the login screen
        HBox mainContainer = new HBox();

        // Container for the login form elements with 30px spacing
        VBox signInContainer = new VBox(30);
        signInContainer.setMinWidth(600);

        // Container for the title and description with 20px spacing
        VBox titleContainer = new VBox(20);

        // Image and ImageView for the logo
        Image logo = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView logoBox = new ImageView(logo);
        logoBox.setFitHeight(500);
        logoBox.setFitWidth(500);

        // Container for the logo image, centered with minimum width of 600px
        HBox logoContainer = new HBox();
        logoContainer.getChildren().add(logoBox);
        logoContainer.setMinWidth(600);
        logoContainer.setAlignment(Pos.CENTER);

        // Title text for the login screen
        Text title = new Text("Sign In");
        title.setStyle("-fx-font-size: 30px; -fx-font-family: 'Roboto'; -fx-fill: #202469; -fx-font-weight: bold;");

        // Description text for login instructions
        Text description = new Text("Enter database credentials to Sign In");
        description.setStyle("-fx-font-size: 17px; -fx-font-family: 'Arial'");

        // Adding the title and description to the title container and centering it
        titleContainer.getChildren().addAll(title, description);
        titleContainer.setAlignment(Pos.CENTER);

        // Container for the form elements with 18px spacing
        VBox formContainer = new VBox(18);

        // Style applied to the text fields for borders and rounded corners
        String formStyle = "-fx-border-color: #202469; -fx-border-width: 1px;" +
                " -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-font-size: 14px;";

        // TextField for the database name input
        PasswordField databaseName = new PasswordField();
        databaseName.setPromptText("Database Name");
        databaseName.setStyle(formStyle);
        databaseName.setMaxWidth(300);
        databaseName.setMinHeight(35);

        // TextField for the username input
        PasswordField username = new PasswordField();
        username.setPromptText("Username");
        username.setStyle(formStyle);
        username.setMaxWidth(300);
        username.setMinHeight(35);

        // TextField for the password input
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setStyle(formStyle);
        password.setMaxWidth(300);
        password.setMinHeight(35);

        // Adding the text fields to the form container and centering it
        formContainer.getChildren().addAll(databaseName, username, password);
        formContainer.setAlignment(Pos.CENTER);

        // Button to test connection with styling for color, size, and font
        Button textConnection = new Button("Test Connection");
        textConnection.setStyle("-fx-background-color: #202469; -fx-text-fill: white; -fx-font-size: 15px;" +
                "-fx-font-weight: bold; -fx-padding: 10px 22px; -fx-font-family: 'Roboto';" +
                "-fx-background-radius: 20px");

        // Button to sign in after database connection is successful with styling for color, size, and font
        Button signIn = new Button("Click To Sign In");
        signIn.setStyle("-fx-background-color: #035b01; -fx-text-fill: white; -fx-font-size: 15px;" +
                "-fx-font-weight: bold; -fx-padding: 10px 22px; -fx-font-family: 'Roboto';" +
                "-fx-background-radius: 20px");


        // Adding the title, form, and button to the sign-in container and centering it
        signInContainer.getChildren().addAll(titleContainer, formContainer, textConnection);
        signInContainer.setAlignment(Pos.CENTER);

        // Adding the logo and sign-in containers to the main container and centering it
        mainContainer.getChildren().addAll(logoContainer, signInContainer);
        mainContainer.setAlignment(Pos.CENTER);

        // Setting the background image for the main container
        mainContainer.setStyle("-fx-background-image: url('/back.jpg');" +
                "-fx-background-size: cover;" +
                "-fx-background-position: center;");


        // Set an action for the textConnection button to retrieve and set database credentials,
        // establish a new database connection, and reload the sign-in form.
        textConnection.setOnAction(e -> {

            DatabaseCredentials.getCredentialsFromSignIn(databaseName.getText(), username.getText(), password.getText());

            Database.getNewDatabase();

            reloadForm(signInContainer, titleContainer, formContainer, signIn, description);
        });

        // Set an action for the signIn button to execute when clicked.
        signIn.setOnAction(e -> {
            PropertyPro.root.getChildren().clear();
            PropertyPro.root.getChildren().add(new Dashboard());
            System.out.println("Sign In Button Clicked");
        });

        // Check if credentials file exists, and if so, load credentials, connect to the database,
        // and update the form UI to indicate a successful connection.
        if (credentials.exists()) {

            DatabaseCredentials.getCredentialsFromFile(databaseName, username, password);

            Database.getNewDatabase();

            reloadForm(signInContainer, titleContainer, formContainer, signIn, description);
        }

        // Setting the main container in the center of the BorderPane
        this.setCenter(mainContainer);
    }

    /**
     * The reloadForm() method updates the provided VBox container to display the sign-in form upon a successful database connection.
     * Clears any existing children in the container and adds the title, form, and button nodes.
     * Updates the description text to indicate that the connection was established successfully.
     *
     * @param container   The main VBox container to be updated with the new form elements.
     * @param title       The VBox containing the title of the form.
     * @param form        The VBox containing the form input fields.
     * @param button      The Button for form submission.
     * @param description The Text element to display a message about the database connection status.
     */
    public void reloadForm(VBox container, VBox title, VBox form, Button button, Text description){

        if(Database.connectionSuccessful()){
            container.getChildren().clear();
            container.getChildren().addAll(title, form, button);

            description.setText("Connection Established! Proceed To Sign In");
            description.setStyle("-fx-fill: #035b01; -fx-font-size: 17px; -fx-font-family: 'Arial'");
        }else{
            description.setText("Connection Not Successful! Please Text Connection");
            description.setStyle("-fx-fill: #bd0505; -fx-font-size: 17px; -fx-font-family: 'Arial'");
        }
    }
}