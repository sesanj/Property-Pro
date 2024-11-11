package com.example.propertypro;

import Database.Database;
import Overview.Totals;
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
import javafx.scene.layout.GridPane;
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

         GridPane layout = new GridPane();

         Totals totalSectionOne = new Totals();
         totalSectionOne.setPrefSize(650, 450);
         totalSectionOne.setStyle("-fx-background-color: white;");

         Totals totalSectionTwo = new Totals();
         totalSectionTwo.setPrefSize(650, 450);
         totalSectionTwo.setStyle("-fx-border-color: black; -fx-background-color: white;");

         Totals totalSectionThree = new Totals();
         totalSectionThree.setPrefSize(650, 450);
         totalSectionThree.setStyle("-fx-border-color: black; -fx-background-color: white;");

         Totals totalSectionFour = new Totals();
         totalSectionFour.setPrefSize(650, 450);
         totalSectionFour.setStyle("-fx-border-color: black; -fx-background-color: white;");

         layout.add(totalSectionOne, 0, 0);
         layout.add(totalSectionTwo, 0, 1);
         layout.add(totalSectionThree, 1, 0);
         layout.add(totalSectionFour, 1, 1);


         layout.setAlignment(Pos.CENTER);

         this.setCenter(layout);
    }
}
