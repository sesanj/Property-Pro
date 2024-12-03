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
        creditsBox.setAlignment(Pos.CENTER_LEFT);

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

        Text credit6 = new Text("Emilin Syju \n");
        credit6.setStyle("-fx-font-size: 24px;");

        Text credit7 = new Text("Sources :-\n");
        credit7.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Text credit8 = new Text("Condo Image by tikisada");
        credit8.setStyle("-fx-font-size: 24px;");
        Text url8 = new Text("https://pixabay.com/photos/bangkok-thailand-building-asia-682056/");

        Text credit9 = new Text("Apartment by PrompterMalaya ");
        credit9.setStyle("-fx-font-size: 24px;");
        Text url9 = new Text("https://pixabay.com/illustrations/interior-lifestyle-indoors-3531779/");

        Text credit10 = new Text("Duplex by Pexels");
        credit10.setStyle("-fx-font-size: 24px;");
        Text url10 = new Text("https://pixabay.com/photos/house-architecture-front-yard-1836070/");

        Text credit11 = new Text("Bungalow by PublicDomainPictures");
        credit11.setStyle("-fx-font-size: 24px;");
        Text url11 = new Text("https://pixabay.com/photos/bungalow-house-door-windows-roof-20544/ ");

        Text credit12 = new Text("Loft by mussellistefano");
        credit12.setStyle("-fx-font-size: 24px;");
        Text url12 = new Text("https://pixabay.com/photos/sitting-room-loft-sardinia-relax-2037945");

        Text credit13 = new Text("Family Home by 纹叶 ");
        credit13.setStyle("-fx-font-size: 24px;");
        Text url13 = new Text("https://pixabay.com/photos/villa-exterior-3dmax-night-view-5102551/");

        Text credit14 = new Text("Town House by psk1919 ");
        credit14.setStyle("-fx-font-size: 24px;");
        Text url14 = new Text("https://pixabay.com/photos/townhouse-architecture-3d-rendering-5976587/");

        Text credit15 = new Text("Family Home by 纹叶  ");
        credit15.setStyle("-fx-font-size: 24px;");
        Text url15 = new Text("https://pixabay.com/photos/villa-exterior-3dmax-night-view-5102551/");

        Text credit16 = new Text("Race Ranch by Bru-nO ");
        credit16.setStyle("-fx-font-size: 24px;");
        Text url16 = new Text("https://pixabay.com/photos/coast-beach-house-beach-house-4478424/");

        Text credit17 = new Text("User icon by Freepik ");
        credit17.setStyle("-fx-font-size: 24px;");
        Text url17 = new Text("https://www.flaticon.com/free-icon/team_476863?term=user&page=1&\nposition=16&origin=search&related_id=476863");

        Text credit18 = new Text("Window by Freepik ");
        credit18.setStyle("-fx-font-size: 24px;");
        Text url18 = new Text("https://www.flaticon.com/free-icon/windows_2374606?term=window&page=2\n&position=39&origin=search&related_id=2374606 ");

        Text credit19 = new Text("Chart by Freepik");
        credit19.setStyle("-fx-font-size: 24px;");
        Text url19 = new Text("https://www.flaticon.com/free-icon/growth_3281306?term=chart&page=1&posi\ntion=12&origin=search&related_id=3281306 ");


        Text credit20 = new Text("Property by Freepik");
        credit20.setStyle("-fx-font-size: 24px;");
        Text url20 = new Text("https://www.flaticon.com/free-icon/building_602182?term=property&page=1\n&position=28&origin=search&related_id=602182");

        Text credit21 = new Text("Settings by Freepik");
        credit21.setStyle("-fx-font-size: 24px;");
        Text url21 = new Text("https://www.flaticon.com/free-icon/setting_839374?term=settings&page=3&po\nsition=45&origin=search&related_id=839374");

        Text credit22 = new Text("Log Out by kmg design");
        credit22.setStyle("-fx-font-size: 24px;");
        Text url22 = new Text(" https://www.flaticon.com/free-icon/log-out_3177407?term=log+out&page=1\n&position=56&origin=search&related_id=3177407");

        Text credit23 = new Text("Background Image (LogIn Screen) by muhammad.abdullah");
        credit23.setStyle("-fx-font-size: 24px;");
        Text url23 = new Text("https://www.freepik.com/free-vector/mode\nrn-abstract-white-minimal-background_237150000\n.htm#fromView=search&page=3&position=46&uuid=e1573130-1d72-4d60-bfb0-2fda9ce2c568 ");

        Text credit24 = new Text("Chart up by Icon Hubs ");
        credit24.setStyle("-fx-font-size: 24px;");
        Text url24 = new Text("https://www.flaticon.com/free-icon/up_8438644?term=growth&page=1&positio\nn=42&origin=search&related_id=8438644");


        Text credit25= new Text("This project could not be completed without the supervision of our Professor : ");
        credit25.setStyle("-fx-font-size: 24px;  -fx-font-weight: bold; ");

        Text credit26 = new Text("Cai Filiaut\n");
        credit26.setStyle("-fx-font-size: 24px; ");


        creditsBox.getChildren().addAll(title, credit1, credit2, credit3, credit4 ,credit5,credit6,credit25,credit26 ,credit7,credit8,url8,credit9,url9,credit10,url10,credit11,url11,credit12,url12,credit13,url13,credit14,url14,credit15,url15,credit16,url16,credit17,url17,credit18,url18,credit19,url19,credit20,url20,credit21,url21,credit22,url22,credit23,url23,credit24,url24);





       contentPane.getChildren().addAll(creditsBox);



        this.setCenter(contentPane);


        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(15), creditsBox);
        translateTransition.setFromY(1200);
        translateTransition.setToY(-600);
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition.setInterpolator(javafx.animation.Interpolator.LINEAR);
        translateTransition.play();
    }
}
