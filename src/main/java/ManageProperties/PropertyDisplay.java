package ManageProperties;

import Database.Database;
import Overview.TopClients;
import com.example.propertypro.Pojo.PropertyPOJORefined;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private static Text postalCode;
    private static Text availability;
    private static Text revenue;

     public PropertyDisplay(){

        HBox contentBox = new HBox(40);
        VBox container = new VBox(30);

        HBox imageBox = new HBox();
        VBox detailsBox = new VBox(30);

        VBox nameBox = new VBox(6);
        VBox addressBox = new VBox(6);
        VBox postalBox = new VBox(6);
        VBox availabilityBox = new VBox(6);
        VBox revenueBox = new VBox(6);
        VBox cityBox = new VBox(6);


        Label nameLabel = new Label("Property Name");
        Label addressLabel = new Label("Address");
        Label postalLabel = new Label("Postal Code");
        Label cityProvinceLabel = new Label("City & Province");
        Label availabilityLabel = new Label("Availability");
        Label revenueLabel = new Label("Revenue");

        Text title = new Text("Property Info");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        name = new Text();
        name.setStyle("-fx-fill: #1a1b2e; -fx-font-size: 18px; -fx-font-weight: bold;");

        city = new Text();
        city.setStyle("-fx-fill: #1a1b2e; -fx-font-size: 16px; -fx-font-weight: bold;");

        address = new Text();
        address.setStyle("-fx-fill: #1a1b2e; -fx-font-size: 16px; -fx-font-weight: bold;");

        postalCode = new Text();
        postalCode.setStyle("-fx-fill: #1a1b2e; -fx-font-size: 16px; -fx-font-weight: bold;");

        availability = new Text();

        revenue = new Text();
        revenue.setStyle("-fx-fill: #1a1b2e; -fx-font-size: 18px; -fx-font-weight: bold;");

        Image house = new Image(getClass().getResourceAsStream("/back2.jpg"));
        ImageView houseImage = new ImageView(house);
        houseImage.setFitHeight(200);
        houseImage.setFitWidth(200);

        nameBox.getChildren().addAll(nameLabel, name);
        addressBox.getChildren().addAll(addressLabel, address);
        postalBox.getChildren().addAll(postalLabel, postalCode);
        availabilityBox.getChildren().addAll(availabilityLabel, availability);
        revenueBox.getChildren().addAll(revenueLabel, revenue);
        cityBox.getChildren().addAll(cityProvinceLabel, city);



        imageBox.getChildren().add(houseImage);

        detailsBox.getChildren().addAll(title, nameBox, addressBox, cityBox, postalBox, availabilityBox, revenueBox);

        contentBox.getChildren().addAll(imageBox, detailsBox);

        container.getChildren().addAll(title, contentBox);
        container.setAlignment(Pos.TOP_LEFT);
        container.setStyle("-fx-padding: 30px 50px 50px 50px");


        this.setCenter(container);
    }

    public static void getPropertyDetails(PropertyPOJORefined property){

        System.out.println(property.getName());

        name.setText(property.getName());
        address.setText(property.getStreet());
        city.setText(property.getCity() + " " + property.getProvince());
        postalCode.setText(property.getPostal_code());

        if (property.getAvailability() == 1){
            availability.setText("Available");
            availability.setStyle("-fx-fill: green; -fx-font-size: 20px; -fx-font-weight: bold;");
        }else{
            availability.setText("Not Available");
            availability.setStyle("-fx-fill: red; -fx-font-size: 20px; -fx-font-weight: bold;");

        }

        revenue.setText("$" + String.format("%,.2f", getPropertyRevenue(property.getProperty_id())));
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

        try{
            Statement getClients = db.getConnection().createStatement();
            ResultSet data = getClients.executeQuery(query);

            while(data.next()){
                revenue += data.getDouble("revenue");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return revenue;
    }
}
