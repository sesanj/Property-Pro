package Animations;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Animations {

    public static void translate(Node node, int duration){
        TranslateTransition translate = new TranslateTransition(Duration.millis(duration), node);
        translate.setFromY(50);
        translate.setToY(0);
        translate.play();

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(800), node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setCycleCount(1);
        fadeTransition.play();

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(translate, fadeTransition);
        parallelTransition.play();
    }
}
