package ManageProperties;

import Database.Database;
import Overview.TopClients;
import TableQuery.PropertyTable;
import com.example.propertypro.Pojo.PropertyPOJORefined;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;
import static Database.DatabaseTableConstants.CLIENT_PHONE_NUMBER;

public class PropertyDisplay extends BorderPane {

    private static Text name;
    private static Text address;
    private static Text city;
    private static Text availability;
    private static Text revenue;
    private static Text title;
    private static Text bookings;

     public PropertyDisplay(){

        HBox contentBox = new HBox(30);
        VBox container = new VBox(30);

        HBox imageBox = new HBox();
        VBox detailsBox = new VBox(18);

        VBox nameBox = new VBox(6);
        VBox addressBox = new VBox(6);
        VBox availabilityBox = new VBox(6);
        VBox revenueBox = new VBox(6);
        VBox bookingBox = new VBox(6);
        VBox cityBox = new VBox(6);

        HBox revenueAndBooking = new HBox(40);
        revenueAndBooking.getChildren().addAll(revenueBox, bookingBox);

        HBox titleBox = new HBox();

        HBox bestPerformerBox = new HBox();
        Button bestPerformer = new Button("Best Performer");
        bestPerformer.getStyleClass().add("tabButton");
        bestPerformer.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        bestPerformerBox.getChildren().add(bestPerformer);
        bestPerformerBox.setAlignment(Pos.CENTER_RIGHT);


        Label nameLabel = new Label("Property Name");
        Label addressLabel = new Label("Address & Postal Code");
        Label cityProvinceLabel = new Label("City & Province");
        Label availabilityLabel = new Label("Availability");
        Label revenueLabel = new Label("Revenue");
        Label totalBookings = new Label("Total Bookings");

        title = new Text("Property Info");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        name = new Text();
        name.setStyle("-fx-fill: #1a1b2e; -fx-font-size: 16px; -fx-font-weight: bold;");

        city = new Text();
        city.setStyle("-fx-fill: #1a1b2e; -fx-font-size: 16px; -fx-font-weight: bold;");

        address = new Text();
        address.setStyle("-fx-fill: #1a1b2e; -fx-font-size: 16px; -fx-font-weight: bold;");

        availability = new Text();
        bookings = new Text();
        bookings.setStyle("-fx-fill: #1a1b2e; -fx-font-size: 20px; -fx-font-weight: bold;");

        revenue = new Text();
        revenue.setStyle("-fx-fill: green; -fx-font-size: 20px; -fx-font-weight: bold;");

        Image house = new Image(getClass().getResourceAsStream("/back2.jpg"));
        ImageView houseImage = new ImageView(house);
        houseImage.setFitHeight(150);
        houseImage.setFitWidth(150);

        nameBox.getChildren().addAll(nameLabel, name);
        addressBox.getChildren().addAll(addressLabel, address);
        availabilityBox.getChildren().addAll(availabilityLabel, availability);
        revenueBox.getChildren().addAll(revenueLabel, revenue);
        cityBox.getChildren().addAll(cityProvinceLabel, city);

        titleBox.getChildren().addAll(title, bestPerformerBox);
        HBox.setHgrow(bestPerformerBox, Priority.ALWAYS);

        bookingBox.getChildren().addAll(totalBookings, bookings);
        bookingBox.setAlignment(Pos.CENTER);


        imageBox.getChildren().add(houseImage);

        detailsBox.getChildren().addAll(nameBox, addressBox, cityBox, availabilityBox, revenueAndBooking);

        contentBox.getChildren().addAll(imageBox, detailsBox);

        container.getChildren().addAll(titleBox, contentBox);
        container.setAlignment(Pos.TOP_LEFT);
        container.setStyle("-fx-padding: 50px 50px 50px 50px");

        getBestPerformer();

        bestPerformer.setOnAction(e ->{
            getBestPerformer();
        });


        this.setCenter(container);
    }

    public static void getPropertyDetails(PropertyPOJORefined property){

        name.setText(property.getName() + " (" + property.getProperty_type() + ")");
        address.setText(property.getStreet() + " - " + property.getPostal_code());
        city.setText(property.getCity() + ", " + property.getProvince());

        if (property.getAvailability() == 1){
            availability.setText("Available");
            availability.setStyle("-fx-fill: green; -fx-font-size: 18px; -fx-font-weight: bold;");
        }else{
            availability.setText("Not Available");
            availability.setStyle("-fx-fill: red; -fx-font-size: 18px; -fx-font-weight: bold;");

        }

        revenue.setText("$" + String.format("%,.2f", getPropertyRevenue(property.getProperty_id())));
        title.setText("Property Info");
    }

    public static double getPropertyRevenue(int propertyID){

        Database db = Database.getNewDatabase();

        String query = "SELECT p." + PROPERTY_NAME + ", " +
                "SUM(t." + TRANSACTION_AMOUNT + ") AS revenue, " +
                "COUNT(t." + TRANSACTION_ID + ") AS totalTransactions " +
                "FROM " + PROPERTY_TABLE + " p " +
                "JOIN " + TRANSACTION_TABLE + " t ON p." + PROPERTY_ID + " = t." + PROPERTY_ID + " "+
                "WHERE p." + PROPERTY_ID + " = " + propertyID;

        double revenue = 0;
        int totalTransactions = 0;

        try{
            Statement getClients = db.getConnection().createStatement();
            ResultSet data = getClients.executeQuery(query);

            while(data.next()){
                revenue += data.getDouble("revenue");
                totalTransactions = data.getInt("totalTransactions");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        bookings.setText(totalTransactions + "+");

        return revenue;
    }

    public static void getBestPerformer(){

        Database db = Database.getNewDatabase();

         ArrayList<Integer> propertyId = new ArrayList<>();
         ArrayList<Double> amount = new ArrayList<>();

         String query = "SELECT p." + PROPERTY_NAME + ", p." + PROPERTY_ID + ", " +
                 "SUM(t." + TRANSACTION_AMOUNT + ") AS revenue, " +
                 "COUNT(t." + TRANSACTION_ID + ") AS totalTransactions " +
                 "FROM " + PROPERTY_TABLE + " p " +
                 "JOIN " + TRANSACTION_TABLE + " t ON p." + PROPERTY_ID + " = t." + PROPERTY_ID + " " +
                 "GROUP BY p." + PROPERTY_ID + ", p." + PROPERTY_NAME + " " +
                 "ORDER BY revenue DESC";

        try{
            Statement getClients = db.getConnection().createStatement();
            ResultSet data = getClients.executeQuery(query);

            while(data.next()){
                propertyId.add(data.getInt(PROPERTY_ID));
                amount.add(data.getDouble("revenue"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        getPropertyDetails(new PropertyTable().getPropertyByID(propertyId.getFirst()));
        revenue.setText("$" + String.format("%,.2f", amount.getFirst()));

        title.setText("Best Performing Property");
    }
}
