package ManageProperties;

import Animations.Animations;
import Database.Database;
import TableQuery.PropertyTable;
import com.example.propertypro.Pojo.PropertyPOJO;
import com.example.propertypro.Pojo.PropertyPOJORefined;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;

/**
 * PropertyDisplay class represents the UI component for displaying and managing property details.
 * It includes functionality for toggling availability, viewing revenue, and showing property info.
 */
public class PropertyDisplay extends BorderPane {

    private static Text name;
    private static Text address;
    private static Text city;
    private static Text availability;
    private static Text revenue;
    private static Text title;
    private static Text bookings;
    private static ToggleButton toggleButton;

    private static PropertyTable propertyTable = new PropertyTable();

    public static Image house;
    public static ImageView houseImage = new ImageView();
    public static VBox nameBox;
    public static VBox addressBox;
    public static VBox availabilityBox;
    public static VBox cityBox;
    public static HBox revenueAndBooking;

    /**
     * Constructor for initializing the PropertyDisplay UI with default settings.
     * This sets up the layout, components, and styling.
     */
     public PropertyDisplay(){

        HBox contentBox = new HBox(30);
        VBox container = new VBox(30);

        HBox imageBox = new HBox();
        VBox detailsBox = new VBox(18);

        toggleButton = new ToggleButton();
        toggleButton.getStyleClass().add("toggleButton");
        toggleButton.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());
        HBox toggleBox = new HBox(30);

        HBox toggleButtonBox = new HBox();
        toggleButtonBox.getChildren().add(toggleButton);
        toggleButtonBox.setAlignment(Pos.CENTER_RIGHT);

        nameBox = new VBox(6);
        addressBox = new VBox(6);
        availabilityBox = new VBox(6);
        VBox revenueBox = new VBox(6);
        VBox bookingBox = new VBox(6);
        cityBox = new VBox(6);

        revenueAndBooking = new HBox(40);
        revenueAndBooking.getChildren().addAll(revenueBox, bookingBox);

        HBox titleBox = new HBox();

        HBox bestPerformerBox = new HBox();
        Button bestPerformer = new Button("Best Performer");
        bestPerformer.getStyleClass().add("tabButton");
        bestPerformer.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        bestPerformerBox.getChildren().add(bestPerformer);
        bestPerformerBox.setAlignment(Pos.CENTER_RIGHT);


        // Display Labels
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


        toggleBox.getChildren().addAll(availability, toggleButtonBox);
        HBox.setHgrow(toggleButtonBox, Priority.ALWAYS);

        nameBox.getChildren().addAll(nameLabel, name);
        addressBox.getChildren().addAll(addressLabel, address);
        availabilityBox.getChildren().addAll(availabilityLabel, toggleBox);
        revenueBox.getChildren().addAll(revenueLabel, revenue);
        cityBox.getChildren().addAll(cityProvinceLabel, city);

        titleBox.getChildren().addAll(title, bestPerformerBox);
        HBox.setHgrow(bestPerformerBox, Priority.ALWAYS);

        bookingBox.getChildren().addAll(totalBookings, bookings);
        bookingBox.setAlignment(Pos.CENTER);

        detailsBox.getChildren().addAll(nameBox, addressBox, cityBox, availabilityBox, revenueAndBooking);

        contentBox.getChildren().addAll(imageBox, detailsBox);

        container.getChildren().addAll(titleBox, contentBox);
        container.setAlignment(Pos.TOP_LEFT);
        container.setStyle("-fx-padding: 50px 40px 50px 50px");

        getBestPerformer();

        bestPerformer.setOnAction(e ->{
            getBestPerformer();
        });

        imageBox.getChildren().add(houseImage);

        this.setCenter(container);

        animate();
    }

    /**
     * Updates the property details in the UI based on the provided PropertyPOJORefined object.
     *
     * @param property The PropertyPOJORefined object containing property details.
     */
    public static void getPropertyDetails(PropertyPOJORefined property){

        name.setText(property.getName() + " (" + property.getProperty_type() + ")");
        address.setText(property.getStreet() + " - " + property.getPostal_code());
        city.setText(property.getCity() + ", " + property.getProvince());


        if (property.getAvailability() == 1){
            availability.setText("Available");
            availability.setStyle("-fx-fill: green; -fx-font-size: 18px; -fx-font-weight: bold;");

            toggleButton.setText("Turn Off");
            toggleButton.setSelected(true);

        }else{
            availability.setText("Not Available");
            availability.setStyle("-fx-fill: red; -fx-font-size: 18px; -fx-font-weight: bold;");

            toggleButton.setText("Turn On");
            toggleButton.setSelected(false);
        }

        revenue.setText("$" + String.format("%,.2f", getPropertyRevenue(property.getProperty_id())));
        title.setText("Property Info");

        toggleButton.setOnAction(e ->{
            int available;

            PropertyPOJO selectedProperty = propertyTable.getPropertyRaw(property.getProperty_id());

            if (selectedProperty.getAvailability() == 1){

                toggleButton.setText("Turn On");
                available = 0;
                availability.setText("Not Available");
                availability.setStyle("-fx-fill: red; -fx-font-size: 18px; -fx-font-weight: bold;");

            }else{
                availability.setText("Available");
                toggleButton.setText("Turn Off");
                availability.setStyle("-fx-fill: green; -fx-font-size: 18px; -fx-font-weight: bold;");
                available = 1;
            }

            propertyTable.updateAvailability(available, property.getProperty_id());

            AllProperties.refreshPropertyTable();

        });
        houseImage.setImage(null);

        house = new Image(PropertyDisplay.class.getResourceAsStream("/" + property.getProperty_type() + ".jpg"));
        houseImage.setImage(house);
        houseImage.setFitHeight(180);
        houseImage.setFitWidth(180);
    }

    /**
     * Fetches the revenue for a given property using the property ID.
     *
     * @param propertyID The ID of the property.
     * @return The revenue generated by the property.
     */
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

    /**
     * Retrieves and displays the best-performing property based on revenue.
     */
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

    /**
     * Animates the UI components for smoother transitions.
     */
    public static void animate(){

        Animations.translate(nameBox, 500);
        Animations.translate(addressBox, 700);
        Animations.translate(cityBox, 900);
        Animations.translate(availabilityBox, 1100);
        Animations.translate(revenueAndBooking, 1300);
        Animations.translate(houseImage, 800);
    }
}
