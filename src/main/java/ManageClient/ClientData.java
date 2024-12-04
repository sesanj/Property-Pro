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

/**
 * This class provides the UI for displaying client data including their name, phone number, email, and revenue.
 * It also includes methods to retrieve and display client data from the database.
 */
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

    /**
     * Initializes the UI for displaying client data.
     * It sets up various labels and text elements to show the client's information.
     * Also, it fetches the initial client details.
     */
    public ClientData() {

        VBox allContents = new VBox(40);
        NameBox = new VBox(6);
        PhoneBox = new VBox(6);
        EmailBox = new VBox(6);
        ExpenseBox = new VBox(6);

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

        allContents.getChildren().addAll(title, NameBox, PhoneBox, EmailBox, ExpenseBox);

        allContents.setAlignment(Pos.TOP_LEFT);
        allContents.setStyle("-fx-padding: 50px");

        this.setCenter(allContents);

        // Fetching details for the first client in the list
        getClientDetails(AllClients.topClients.getFirst());

        animate();
    }

    /**
     * Retrieves the total revenue for a client based on their client ID.
     *
     * @param client_id The ID of the client whose revenue is to be fetched.
     * @return The total revenue for the client.
     */
    public static double getClientRevenue(int client_id){
        Database db = Database.getNewDatabase();
        String query = "SELECT c." + CLIENT_FIRST_NAME + ", " +
                "SUM(t." + TRANSACTION_AMOUNT + ") AS revenue " +
                "FROM " + CLIENT_TABLE + " c " +
                "JOIN " + TRANSACTION_TABLE + " t ON c." + CLIENT_ID + " = t." + CLIENT_ID +
                " WHERE c." + CLIENT_ID + " = " + client_id;

        double revenue = 0;
        try {
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

    /**
     * Sets the client details (name, phone number, email, and total revenue)
     * based on the provided {@link TopClients} object.
     *
     * @param clientDetails The client details to be displayed.
     */
    public static void getClientDetails(TopClients clientDetails){

        name.setText(clientDetails.getFirst_name() + " " + clientDetails.getLast_name());
        phone.setText(clientDetails.getPhone_number());
        email.setText(clientDetails.getEmail());
        totalRevenueText.setText("$" + String.format("%,.2f", getClientRevenue(clientDetails.getId())));
    }

    /**
     * Animates the display of the client data by translating various elements
     * of the UI to make them appear one after another.
     */
    public static void animate(){

        Animations.translate(NameBox, 600);
        Animations.translate(EmailBox, 800);
        Animations.translate(PhoneBox, 1000);
        Animations.translate(ExpenseBox, 1200);
    }
}
