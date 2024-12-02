package Settings;

import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.ScrollPane;
import javafx.util.Duration;

public class Credits extends BorderPane {
    public Credits() {

        // StackPane to hold the content that will change when a button is clicked
        StackPane contentPane = new StackPane();
        contentPane.getStylesheets().add(getClass().getResource("/content.css").toExternalForm());
        contentPane.setId("credit-pane");

        VBox creditsBox = new VBox(20);
        creditsBox.setAlignment(Pos.CENTER);

        Text title = new Text("Credits");
        title.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");

        Text credit1 = new Text("Propery-Pro");
        credit1.setStyle("-fx-font-size: 24px; -fx-font-weight: bold");
        Text credit2 = new Text("Developers :-  ");
        credit2.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Text credit3 = new Text("Sesan Popoola");
        credit3.setStyle("-fx-font-size: 24px;");

        Text credit4 = new Text("Aarav Abraham");
        credit4.setStyle("-fx-font-size: 24px;");

        Text credit5 = new Text("Irene Eweka");
        credit5.setStyle("-fx-font-size: 24px;");

        Text credit6 = new Text("Emilin Syju");
        credit6.setStyle("-fx-font-size: 24px;");

        Text credit7 = new Text("Sources :-");
        credit7.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Text credit8 = new Text("gibrish");
        credit8.setStyle("-fx-font-size: 24px;");

        Text credit9 = new Text("gieww");
        credit9.setStyle("-fx-font-size: 24px;");

        Text credit10 = new Text("wweeq");
        credit10.setStyle("-fx-font-size: 24px;");

        Text credit11 = new Text("eewdsasww");
        credit11.setStyle("-fx-font-size: 24px;");

        Text credit12 = new Text("eewdsasww");
        credit12.setStyle("-fx-font-size: 24px;");

        Text credit13 = new Text("eqccfrew");
        credit13.setStyle("-fx-font-size: 24px;");

        Text credit14 = new Text("This project could not be completed without the supervision of our Professor :");
        credit14.setStyle("-fx-font-size: 24px;  -fx-font-weight: bold;");

        Text credit15 = new Text("Cai Filiaut");
        credit15.setStyle("-fx-font-size: 24px; ");


        creditsBox.getChildren().addAll(title, credit1, credit2, credit3, credit4 ,credit5,credit6,credit7,credit8,credit9,credit10,credit11,credit12,credit13,credit14,credit15);





       contentPane.getChildren().addAll(creditsBox);



        this.setCenter(contentPane);


        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(20), creditsBox);
        translateTransition.setFromY(800);
        translateTransition.setToY(-600);
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition.setInterpolator(javafx.animation.Interpolator.LINEAR);
        translateTransition.play();
    }
}
