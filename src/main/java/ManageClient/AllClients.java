package ManageClient;

import Database.Database;
import Overview.TopClients;
import TableQuery.ClientTable;
import com.example.propertypro.Pojo.ClientPOJO;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;
import static Database.DatabaseTableConstants.CLIENT_PHONE_NUMBER;

public class AllClients extends BorderPane {

    private static Text name;



    public AllClients() {

        Database db = Database.getNewDatabase();

        String query = "SELECT c." + CLIENT_FIRST_NAME + ", c." + CLIENT_LAST_NAME + ", c." + CLIENT_PHONE_NUMBER + ", c." + CLIENT_EMAIL + ", "+
                "SUM(t." + TRANSACTION_AMOUNT + ") AS amountSpent, " +
                "COUNT(t." + TRANSACTION_ID + ") AS totalTransactions " +
                "FROM " + CLIENT_TABLE + " c " +
                "JOIN " + TRANSACTION_TABLE + " t ON c." + CLIENT_ID + " = t." + CLIENT_ID + " " +
                "GROUP BY c." + CLIENT_ID + ", c." + CLIENT_FIRST_NAME + " " +
                "ORDER BY amountSpent DESC LIMIT 20";


        VBox topClientsBox = new VBox(30);

        Text title = new Text("Top 20 Clients");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        TableView userTable = new TableView();
        userTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        ArrayList<TopClients> topClients = new ArrayList<>();

        try {
            Statement getClients = db.getConnection().createStatement();
            ResultSet data = getClients.executeQuery(query);

            while (data.next()) {

                topClients.add(new TopClients(data.getString(CLIENT_FIRST_NAME), data.getString(CLIENT_LAST_NAME), data.getInt("totalTransactions"), data.getString(CLIENT_PHONE_NUMBER), data.getDouble("amountSpent")));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        TableColumn<ClientPOJO, String> firstName = new TableColumn<>("First Name");
        firstName.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getFirst_name()));

        TableColumn<ClientPOJO, String> lastName = new TableColumn<>("Last Name");
        lastName.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getLast_name()));

        TableColumn<ClientPOJO, String> phoneNumber = new TableColumn<>("Phone Number");
        phoneNumber.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getPhone_number()));


//        TableColumn<ClientPOJO, String> email = new TableColumn<>("Email");
//        phoneNumber.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getEmail()));


        //TableColumn<ClientPOJO, String> totalTransactions = new TableColumn<>("Transactions");
       // totalTransactions.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getTotal_transactions() + ""));

       // TableColumn<ClientPOJO, String> amount = new TableColumn<>("Amount Spent");
        //amount.setCellValueFactory(e -> new SimpleStringProperty("$" + String.format("%,.2f", e.getValue().getAmount())));

        userTable.getColumns().addAll(firstName, lastName, phoneNumber);

        ClientTable clientTable = new ClientTable();

        userTable.getItems().addAll(clientTable.getAllClient());
        userTable.getStylesheets().add(getClass().getResource("/tableView.css").toExternalForm());

        userTable.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)  ->{
            if (newValue != null){
                ClientPOJO client = (ClientPOJO) userTable.getSelectionModel().getSelectedItem();
                ClientData.getClientDetails(client);
            }
        } );

        topClientsBox.getChildren().addAll(title, userTable);
        topClientsBox.setAlignment(Pos.CENTER_LEFT);
        topClientsBox.setStyle("-fx-padding: 30px 50px 50px 50px");

        this.setCenter(topClientsBox);
    }
    public static class client{
        private String first_name;
        private  String last_name;
        private String phone_number;
        private String email;


    }
}
