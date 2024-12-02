package ManageClient;

import ManageProperties.AllProperties;
import ManageProperties.PropertyForm;
import Overview.TopClients;
import TableQuery.*;
import com.example.propertypro.Pojo.*;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ClientForm extends BorderPane {

    public static ClientTable clientTable = new ClientTable();

    public static TextField firstName;

    public static TextField lastName;
    public static TextField phoneNumber;
    public static TextField emailAddress;
    public static int updatableClientID;
    public static boolean updateFormClicked = false;
    public static Text prompt;

    public ClientForm(){

        firstName = new TextField();
        firstName.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        lastName = new TextField();
        lastName.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        phoneNumber = new TextField();
        phoneNumber.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());

        emailAddress = new TextField();
        emailAddress.getStylesheets().add(getClass().getResource("/comboBox.css").toExternalForm());


        Text title = new Text("Manage Clients");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        VBox layout = new VBox(20);
        HBox tabBox = new HBox(20);

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

        tabBox.getChildren().addAll(newClientTab, updateClientTab);
        tabBox.setAlignment(Pos.CENTER_LEFT);


        newClientTab.setOnAction(e -> {
            layout.getChildren().clear();
            layout.getChildren().addAll(title, tabBox, propertyForm(), addClient);
            updateFormClicked = false;

            prompt.setText("");
        });

        updateClientTab.setOnAction(e -> {
            layout.getChildren().clear();
            layout.getChildren().addAll(title, tabBox, propertyForm(), updateClient);
            updateFormClicked = true;

            prompt.setText("Click On A Client To Update");
        });


        addClient.setOnAction(e ->{

            if(!firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !phoneNumber.getText().isEmpty() &&
            !emailAddress.getText().isEmpty()){

                ClientPOJO newClient = new ClientPOJO(getUpdatableClientID(), firstName.getText(),
                        lastName.getText(), phoneNumber.getText(), emailAddress.getText());


                clientTable.createClient(newClient);

                AllClients.title.setText(AllClients.topClients.size() + 1 + " Total Clients");

                prompt.setText("New Client Added Successfully!");
                prompt.setStyle("-fx-fill: green; -fx-font-size: 14px; -fx-font-style: italic;");
            }
            else{
                prompt.setText("Can't Add New Client, Try Again, Fields Cannot Be Empty!");
                prompt.setStyle("-fx-fill: #860a0a; -fx-font-size: 14px; -fx-font-style: italic;");
            }
        });


        updateClient.setOnAction(e ->{

            if(!firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !phoneNumber.getText().isEmpty() &&
            !emailAddress.getText().isEmpty()){


                ClientPOJO newClient = new ClientPOJO(getUpdatableClientID(), firstName.getText(),
                        lastName.getText(), phoneNumber.getText(), emailAddress.getText());


                clientTable.updateClient(newClient);

                prompt.setText("Your Client Has Been Updated Successfully!");
                prompt.setStyle("-fx-fill: green; -fx-font-size: 14px; -fx-font-style: italic;");


                for(TopClients topClient : AllClients.getAllClients()){
                    if(topClient.getId() == newClient.getClient_id()){

                        ClientData.getClientDetails(topClient);

                        System.out.println("Client Updated");
                    }
                }

            }
            else{
                prompt.setText("Can't Update Client, Try Again, Fields Cannot Be Empty!");
                prompt.setStyle("-fx-fill: #860a0a; -fx-font-size: 14px; -fx-font-style: italic;");
            }

        });

        layout.getChildren().addAll(title, tabBox, propertyForm(), addClient);
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setStyle("-fx-padding: 50px 50px 50px 50px");

        this.setCenter(layout);
    }

    public static VBox propertyForm(){

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


        prompt = new Text();
        prompt.setStyle("-fx-fill: #202469; -fx-font-size: 14px; -fx-font-style: italic;");

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
        phoneNumber.setMaxWidth(200);;
        phoneNumber.setPromptText("Enter Phone Number");
        phoneNumber.setStyle("-fx-border-color: #202469; -fx-border-width: 1px; -fx-border-radius: 5px;" +
                " -fx-font-size: 14px;");

        Label emailLabel = new Label("Email Address");
        emailAddress.setMaxWidth(200);;
        emailAddress.setPromptText("Enter Email Address");
        emailAddress.setStyle("-fx-border-color: #202469; -fx-border-width: 1px; -fx-border-radius: 5px;" +
                " -fx-font-size: 14px;");

        fNameBox.getChildren().addAll(firstNameLabel, firstName);
        lNameBox.getChildren().addAll(lastNameLabel, lastName);
        phoneBox.getChildren().addAll(phoneLabel, phoneNumber);
        emailBox.getChildren().addAll(emailLabel, emailAddress);


        formBox.getChildren().addAll(prompt, nameBox, phoneAndEmailBox);

        containerBox.getChildren().addAll(formBox);
        containerBox.setAlignment(Pos.TOP_LEFT);

        return containerBox;
    }

    public static void getClientDetails(TopClients client){

        if (updateFormClicked){

            firstName.setText(client.getFirst_name());
            lastName.setText(client.getLast_name());
            phoneNumber.setText(client.getPhone_number());
            emailAddress.setText(client.getEmail());

            setUpdatableClientID(client.getId());
        }
    }

    public static int getUpdatableClientID() {
        return updatableClientID;
    }

    public static void setUpdatableClientID(int updatableClientID) {
        ClientForm.updatableClientID = updatableClientID;
    }
}
