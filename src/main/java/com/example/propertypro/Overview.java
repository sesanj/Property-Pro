package com.example.propertypro;

import Database.Database;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static Database.DatabaseTableConstants.*;

public class Overview extends BorderPane {
     Overview() {

         Database db = Database.getNewDatabase();
         VBox provinceID = new VBox( 10);
         VBox province = new VBox(10);

         HBox dataBox = new HBox(20);

        Text ID = new Text();
        Text Province = new Text();
        ID.setStyle("-fx-font-size:22px;");

        String query = "SELECT * FROM client";
        Statement statement;

        try{
            statement = db.getConnection().createStatement();

            ResultSet data = statement.executeQuery(query);

            while(data.next()){
                ID = new Text(data.getInt(CLIENT_ID) + "");
                Province = new Text(data.getString(CLIENT_FIRST_NAME));

                provinceID.getChildren().add(ID);
                province.getChildren().add(Province);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        dataBox.getChildren().addAll(provinceID, province);
        dataBox.setAlignment(Pos.CENTER);




         this.setCenter(dataBox);
    }
}
