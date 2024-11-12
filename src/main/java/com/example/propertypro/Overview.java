package com.example.propertypro;

import Overview.Summary;
import Overview.CashFlow;
import Overview.PropertySum;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

//import java.security.Timestamp;


public class Overview extends BorderPane {
     Overview() {

         GridPane layout = new GridPane();

         Summary summary = new Summary();
         summary.setPrefSize(550, 450);
         summary.setStyle("-fx-background-color: white;");

         CashFlow cashFlow = new CashFlow();
         cashFlow.setPrefSize(750, 450);
         cashFlow.setStyle("-fx-background-color: white;");

         PropertySum propertySum = new PropertySum();
         propertySum.setPrefSize(550, 450);
         propertySum.setStyle("-fx-background-color: white;");

         Summary totalSectionFour = new Summary();
         totalSectionFour.setPrefSize(750, 450);
         totalSectionFour.setStyle("-fx-background-color: white;");

         layout.add(summary, 0, 0);
         layout.add(cashFlow, 1, 0);
         layout.add(propertySum, 0, 1);
         layout.add(totalSectionFour, 1, 1);


         layout.setAlignment(Pos.CENTER);

         this.setCenter(layout);
    }
}
