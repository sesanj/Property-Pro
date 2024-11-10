package com.example.propertypro;

import Database.Database;
import TableQuery.CityTable;
import TableQuery.PropertyTable;
import TableQuery.TransactionTable;
import com.example.propertypro.Pojo.CityPOJO;
import com.example.propertypro.Pojo.PropertyPOJO;
import com.example.propertypro.Pojo.PropertyPOJORefined;
import com.example.propertypro.Pojo.TransactionPOJORefined;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

//import java.security.Timestamp;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;

public class Overview extends BorderPane {
     Overview() {

         ScrollPane layout = new ScrollPane();

         Database db = Database.getNewDatabase();

         //CityTable city = new CityTable();
         TransactionTable transactionTable = new TransactionTable();

         //ArrayList<CityPOJO> allCities = city.getAllCities();

         ArrayList<TransactionPOJORefined> allTransactions = transactionTable.getAllTransactions();

         //CityPOJO cityById = city.getCityByID(5);


         HBox dataBox = new HBox(20);
         VBox allData = new VBox( 10);

        Text ID = new Text();
        Text Province = new Text();
         Text name = new Text();
         Text propertyCity = new Text();
         Text type;
         Text street = new Text();
         Text postalCode = new Text();
         Text Availability = new Text();
        //ID.setStyle("-fx-font-size:22px;");

         VBox propertyID = new VBox(20);
         VBox propertyName = new VBox(20);
         VBox cityBox = new VBox(20);
         VBox typeBox = new VBox(20);
         VBox streetBox = new VBox(20);
         VBox postalBox = new VBox(20);
         VBox availabilityBox = new VBox(20);
         VBox provinceBox = new VBox(20);

        for(TransactionPOJORefined transaction : allTransactions){

            ID = new Text(transaction.getId() + "");
            Province = new Text(transaction.getAmount() + "");
            name = new Text(transaction.getClient_id());
            propertyCity = new Text(transaction.getProperty_id());
            type = new Text(transaction.getTimestamp() + "");
//            street = new Text(property.getStreet());
//            postalCode = new Text(property.getPostal_code());
//            Availability = new Text(property.getAvailability() + "");

            propertyID.getChildren().add(ID);
            provinceBox.getChildren().add(Province);
            propertyName.getChildren().add(name);
            cityBox.getChildren().add(propertyCity);
            typeBox.getChildren().add(type);
//            streetBox.getChildren().add(street);
//            postalBox.getChildren().add(postalCode);
//            availabilityBox.getChildren().add(Availability);

        }

        dataBox.getChildren().addAll(propertyID, propertyName, cityBox, provinceBox, typeBox);

        dataBox.setAlignment(Pos.TOP_CENTER);




         layout.setContent(dataBox);

         this.setCenter(layout);
    }
}
