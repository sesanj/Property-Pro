package com.example.propertypro;

import Overview.Summary;
import Overview.CashFlow;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

//import java.security.Timestamp;


public class Overview extends BorderPane {
     Overview() {

         GridPane layout = new GridPane();

         Summary summary = new Summary();
         summary.setPrefSize(500, 450);
         summary.setStyle("-fx-background-color: white;");

         CashFlow cashFlow = new CashFlow();
         cashFlow.setPrefSize(800, 450);
         cashFlow.setStyle("-fx-background-color: white;");

         Summary totalSectionThree = new Summary();
         totalSectionThree.setPrefSize(500, 450);
         totalSectionThree.setStyle("-fx-border-color: black; -fx-background-color: white;");

         Summary totalSectionFour = new Summary();
         totalSectionFour.setPrefSize(800, 450);
         totalSectionFour.setStyle("-fx-border-color: black; -fx-background-color: white;");

         layout.add(summary, 0, 0);
         layout.add(cashFlow, 1, 0);
         layout.add(totalSectionThree, 0, 1);
         layout.add(totalSectionFour, 1, 1);


         layout.setAlignment(Pos.CENTER);

         this.setCenter(layout);
    }
}
