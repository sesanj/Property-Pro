package ManageClient;

import Animations.Animations;
import Database.Database;
import Overview.TopClients;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static Database.DatabaseTableConstants.*;

public class ClientData extends BorderPane {

    private static Text name;
    private static Text phone;
    private static Text email;
    private static Text totalRevenueText;
    private static Text title;
    private static VBox NameBox;
    private static VBox PhoneBox;
    private static VBox EmailBox;
    private static VBox ExpenseBox;

    public ClientData() {


        VBox allContents = new VBox(40);
        NameBox = new VBox(6);
        PhoneBox = new VBox(6);
        EmailBox = new VBox(6);
        ExpenseBox = new VBox(6);
        //VBox RankBox = new VBox(6);

        Label total_revenue_Label = new Label("Revenue");
        Label name_Label = new Label("Name");
        Label phone_Label = new Label("Phone Number");
        Label email_Label = new Label("Email Address");


        // Styling for the text
        String style = "-fx-fill: #1a1b2e; -fx-font-size: 18px; -fx-font-weight: bold;";

        // Summary text
        title = new Text("Client Information");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");


        name = new Text();
        name.setStyle(style);

         phone = new Text();
        phone.setStyle(style);

        email = new Text();
        email.setStyle(style);

        totalRevenueText = new Text();
        totalRevenueText.setStyle("-fx-font-size: 25px; -fx-fill: green; -fx-font-weight: bold;");


        ExpenseBox.getChildren().addAll(total_revenue_Label,totalRevenueText);
        NameBox.getChildren().addAll(name_Label, name);
        PhoneBox.getChildren().addAll(phone_Label, phone);
        EmailBox.getChildren().addAll(email_Label, email);

        allContents.getChildren().addAll(title,NameBox, PhoneBox, EmailBox, ExpenseBox);


        allContents.setAlignment(Pos.TOP_LEFT);
        allContents.setStyle("-fx-padding: 50px");

        this.setCenter(allContents);

        getClientDetails(AllClients.topClients.getFirst());

        animate();
    }



    public static double getClientRevenue(int client_id){
        Database db =Database.getNewDatabase();
        String query = "SELECT c." +CLIENT_FIRST_NAME + ", " +
                "SUM(t. "+ TRANSACTION_AMOUNT +") AS revenue " +
                "FROM " + CLIENT_TABLE + " c " +
                "JOIN " + TRANSACTION_TABLE + " t ON c." +CLIENT_ID + " = t." + CLIENT_ID +
                " WHERE c." + CLIENT_ID + " = " + client_id;

        double revenue =0;
        try{
            Statement getClients = db.getConnection().createStatement();
            ResultSet data = getClients.executeQuery(query);

            while (data.next()){
                revenue += data.getDouble("revenue");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return revenue;
    }


    public static void getClientDetails(TopClients clientDetails){

        name.setText(clientDetails.getFirst_name() + " " + clientDetails.getLast_name());
        phone.setText(clientDetails.getPhone_number());
        email.setText(clientDetails.getEmail());
        totalRevenueText.setText("$" + String.format("%,.2f", getClientRevenue(clientDetails.getId())));
    }

    public static void animate(){

        Animations.translate(NameBox, 600);
        Animations.translate(EmailBox, 800);
        Animations.translate(PhoneBox, 1000);
        Animations.translate(ExpenseBox, 1200);
    }
}
