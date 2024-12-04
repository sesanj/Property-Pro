package ManageClient;

import Animations.Animations;
import Overview.TopClients;
import TableQuery.*;
import com.example.propertypro.Pojo.*;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * ClientForm is a JavaFX component for managing client details.
 * It provides a form for adding and updating client information,
 * with features for input validation and dynamic UI updates.
 */
public class ClientForm extends BorderPane {

    public static ClientTable clientTable = new ClientTable();  // Table for managing client data.

    // TextFields for user input
    public static TextField firstName;
    public static TextField lastName;
    public static TextField phoneNumber;
    public static TextField emailAddress;

    public static int updatableClientID;  // ID of the client being updated
    public static boolean updateFormClicked = false;  // Flag to track if update form is being used
    public static Text prompt;  // Text element for displaying form status messages

    /**
     * Constructor to initialize the client form UI.
     * Sets up the form fields, buttons, and event handlers.
     */
    public ClientForm(){
        // Initialize TextFields and apply CSS styling
        firstName = new TextField();
        firstName.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        lastName = new TextField();
        lastName.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        phoneNumber = new TextField();
        phoneNumber.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        emailAddress = new TextField();
        emailAddress.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        // Title and layout setup
        Text title = new Text("Manage Clients");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        VBox layout = new VBox(20);
        HBox tabBox = new HBox(20);

        // Buttons for tab switching and form actions
        Button newClientTab = new Button("New Client");
        newClientTab.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());
        newClientTab.getStyleClass().add("tabButton");

        Button updateClientTab = new Button("Update Client");
        updateClientTab.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());
        updateClientTab.getStyleClass().add("tabButton");

        Button addClient = new Button("Add Client");
        addClient.getStyleClass().add("formButton");
        addClient.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        Button updateClient = new Button("Update Client");
        updateClient.getStyleClass().add("formButton");
        updateClient.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        // Add buttons to tabBox
        tabBox.getChildren().addAll(newClientTab, updateClientTab);
        tabBox.setAlignment(Pos.CENTER_LEFT);

        // Event handlers for switching between tabs
        newClientTab.setOnAction(e -> {
            layout.getChildren().clear();
            layout.getChildren().addAll(title, tabBox, clientForm(), addClient);
            updateFormClicked = false;
            prompt.setText("");
            Animations.translate(addClient, 1200);
        });

        updateClientTab.setOnAction(e -> {
            layout.getChildren().clear();
            layout.getChildren().addAll(title, tabBox, clientForm(), updateClient);
            updateFormClicked = true;
            prompt.setText("Click On A Client To Update");
            Animations.translate(updateClient, 1200);
        });

        // Event handler for adding a new client
        addClient.setOnAction(e -> {
            if(!firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !phoneNumber.getText().isEmpty() &&
                    !emailAddress.getText().isEmpty()){

                // Create new ClientPOJO and add to the table
                ClientPOJO newClient = new ClientPOJO(getUpdatableClientID(), firstName.getText(),
                        lastName.getText(), phoneNumber.getText(), emailAddress.getText());
                clientTable.createClient(newClient);

                AllClients.title.setText(AllClients.topClients.size() + 1 + " Total Clients");
                prompt.setText("New Client Added Successfully!");
                prompt.setStyle("-fx-fill: green; -fx-font-size: 14px; -fx-font-style: italic;");
            } else {
                prompt.setText("Can't Add New Client, Try Again, Fields Cannot Be Empty!");
                prompt.setStyle("-fx-fill: #860a0a; -fx-font-size: 14px; -fx-font-style: italic;");
            }
        });

        // Event handler for updating an existing client
        updateClient.setOnAction(e -> {
            if(!firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !phoneNumber.getText().isEmpty() &&
                    !emailAddress.getText().isEmpty()){

                // Create updated ClientPOJO and update in the table
                ClientPOJO newClient = new ClientPOJO(getUpdatableClientID(), firstName.getText(),
                        lastName.getText(), phoneNumber.getText(), emailAddress.getText());
                clientTable.updateClient(newClient);

                prompt.setText("Your Client Has Been Updated Successfully!");
                prompt.setStyle("-fx-fill: green; -fx-font-size: 14px; -fx-font-style: italic;");

                // Update the client details in the view if it matches the updated ID
                for(TopClients topClient : AllClients.getAllClients()){
                    if(topClient.getId() == newClient.getClient_id()){
                        ClientData.getClientDetails(topClient);
                        System.out.println("Client Updated");
                    }
                }
            } else {
                prompt.setText("Can't Update Client, Try Again, Fields Cannot Be Empty!");
                prompt.setStyle("-fx-fill: #860a0a; -fx-font-size: 14px; -fx-font-style: italic;");
            }
        });

        layout.getChildren().addAll(title, tabBox, clientForm(), addClient);
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setStyle("-fx-padding: 50px 50px 50px 50px");
        this.setCenter(layout);

        // Apply animations to UI elements
        Animations.translate(tabBox, 600);
        Animations.translate(addClient, 1200);
    }

    /**
     * Creates and returns the client form layout.
     * Resets form fields and sets up the layout for both new client and update forms.
     *
     * @return VBox container with the form layout.
     */
    public static VBox clientForm() {
        // Clear existing values in form fields
        firstName.setText("");
        phoneNumber.setText("");
        lastName.setText("");
        emailAddress.setText("");

        VBox containerBox = new VBox();
        VBox formBox = new VBox(25);

        HBox nameBox = new HBox(30);
        VBox fNameBox = new VBox(6);
        VBox lNameBox = new VBox(6);
        nameBox.getChildren().addAll(fNameBox, lNameBox);

        HBox phoneAndEmailBox = new HBox(30);
        VBox phoneBox = new VBox(6);
        VBox emailBox = new VBox(6);
        phoneAndEmailBox.getChildren().addAll(phoneBox, emailBox);

        // Text prompt for form status
        prompt = new Text();
        prompt.setStyle("-fx-fill: #202469; -fx-font-size: 14px; -fx-font-style: italic;");

        // Form field labels and styling
        Label firstNameLabel = new Label("First Name");
        firstName.setMaxWidth(200);
        firstName.setPromptText("Enter First Name");
        firstName.setStyle("-fx-border-color: #202469; -fx-border-width: 1px; -fx-border-radius: 5px;" +
                " -fx-font-size: 14px;");

        Label lastNameLabel = new Label("Last Name");
        lastName.setMaxWidth(200);
        lastName.setPromptText("Enter Last Name");
        lastName.setStyle("-fx-border-color: #202469; -fx-border-width: 1px; -fx-border-radius: 5px;" +
                " -fx-font-size: 14px;");

        Label phoneLabel = new Label("Phone Number");
        phoneNumber.setMaxWidth(200);
        phoneNumber.setPromptText("Enter Phone Number");
        phoneNumber.setStyle("-fx-border-color: #202469; -fx-border-width: 1px; -fx-border-radius: 5px;" +
                " -fx-font-size: 14px;");

        Label emailLabel = new Label("Email Address");
        emailAddress.setMaxWidth(200);
        emailAddress.setPromptText("Enter Email Address");
        emailAddress.setStyle("-fx-border-color: #202469; -fx-border-width: 1px; -fx-border-radius: 5px;" +
                " -fx-font-size: 14px;");

        // Add form fields to their respective containers
        fNameBox.getChildren().addAll(firstNameLabel, firstName);
        lNameBox.getChildren().addAll(lastNameLabel, lastName);
        phoneBox.getChildren().addAll(phoneLabel, phoneNumber);
        emailBox.getChildren().addAll(emailLabel, emailAddress);

        // Add elements to the formBox
        formBox.getChildren().addAll(prompt, nameBox, phoneAndEmailBox);

        // Add formBox to the containerBox and align them
        containerBox.getChildren().addAll(formBox);
        containerBox.setAlignment(Pos.TOP_LEFT);

        // Animate UI elements
        animate(nameBox, phoneAndEmailBox);

        return containerBox;
    }

    /**
     * Pre-fills the form fields with the provided client's details.
     *
     * @param client The client whose details are to be loaded into the form.
     */
    public static void getClientDetails(TopClients client){
        if (updateFormClicked){
            firstName.setText(client.getFirst_name());
            lastName.setText(client.getLast_name());
            phoneNumber.setText(client.getPhone_number());
            emailAddress.setText(client.getEmail());
            setUpdatableClientID(client.getId());
        }
    }

    /**
     * Animates the specified nodes (e.g., form fields) on the screen.
     *
     * @param revenue The node representing the revenue field.
     * @param transaction The node representing the transaction field.
     */
    public static void animate(Node revenue, Node transaction){
        Animations.translate(revenue, 600);
        Animations.translate(transaction, 800);
    }

    /**
     * Retrieves the ID of the client being updated.
     *
     * @return The client ID to be updated.
     */
    public static int getUpdatableClientID() {
        return updatableClientID;
    }

    /**
     * Sets the ID of the client to be updated.
     *
     * @param updatableClientID The client ID to be updated.
     */
    public static void setUpdatableClientID(int updatableClientID) {
        ClientForm.updatableClientID = updatableClientID;
    }
}
