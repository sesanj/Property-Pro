package Animations;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * A utility class for applying animations to JavaFX nodes.
 */
public class Animations {

    /**
     * Applies a translation and fade-in animation to the specified JavaFX node.
     * <p>
     * The node is translated from 50 pixels below its original position to its original position
     * and fades in from full transparency to full opacity. Both animations are played in parallel.
     * </p>
     *
     * @param node     the {@link Node} to which the animations will be applied
     * @param duration the duration of the translation animation in milliseconds
     */
    public static void translate(Node node, int duration) {
        // Create a TranslateTransition to move the node vertically.
        TranslateTransition translate = new TranslateTransition(Duration.millis(duration), node);
        translate.setFromY(50); // Start position 50 pixels below
        translate.setToY(0);    // End position at the original position
        translate.play();

        // Create a FadeTransition to fade the node in.
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(800), node);
        fadeTransition.setFromValue(0); // Start fully transparent
        fadeTransition.setToValue(1);   // End fully opaque
        fadeTransition.setCycleCount(1);
        fadeTransition.play();

        // Combine the translate and fade transitions into a ParallelTransition.
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(translate, fadeTransition);
        parallelTransition.play();
    }
}
