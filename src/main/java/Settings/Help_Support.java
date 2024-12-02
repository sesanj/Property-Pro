package Settings;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Help_Support extends BorderPane {

    public Help_Support() {

        // Title for the Help and Support page
        Text title = new Text("Help and Support");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        title.setTextAlignment(TextAlignment.CENTER);  // Center the title

        // VBox to hold the menu options (text labels)
        HBox bottomBox = new HBox(20);
        bottomBox.setAlignment(Pos.CENTER);  // Center the labels horizontally
        bottomBox.setStyle("-fx-padding: 10px 0;");



       // contactSupportButton.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());
    //    sendFeedbackButtonAction.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());
      //  aboutButton.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        // Add buttons to the HBox (menuOptions)


        // StackPane to hold the content that will change when a button is clicked
        StackPane contentPane = new StackPane();
        contentPane.getStylesheets().add(getClass().getResource("/content.css").toExternalForm());  // Apply contentPane styles
        contentPane.setId("content-pane");  // ID to apply specific contentPane styles

        // Contact Support Section
        VBox contactSupport = new VBox(15);
        contactSupport.setAlignment(Pos.CENTER);
        contactSupport.getChildren().addAll(
                new Text("Contact Support:"),
                new Text("Email: aabrahamofficial@gmail.com"),
                new Text("Phone: 226-611-6917"),
                new Text("For urgent issues, please email us directly."),
                //new commit
                new Text("Version: 1.0.0")

        );
      ;

        // Feedback Section
        VBox feedbackSection = new VBox(15);
        feedbackSection.setAlignment(Pos.CENTER);
        feedbackSection.setMaxWidth(500);
        feedbackSection.setPrefWidth(500);

        feedbackSection.getChildren().addAll(
                new Text("Send Feedback:"),
                new Text("We appreciate your feedback! Please let us know how we can improve."),
                new Text("Email: feedback@yourapp.com")

        );

        // Create a TextField to capture user feedback
        TextField feedbackField = new TextField();
        feedbackField.setPromptText("Enter your feedback here...");
        Button sendFeedbackButton = new Button("Send Feedback");

        sendFeedbackButton.setOnAction(e -> {
            // Get the feedback text
            String feedback = feedbackField.getText();

            // If feedback is not empty, save it to a file
            if (!feedback.trim().isEmpty()) {
                saveFeedbackToFile(feedback);
                feedbackField.clear();  // Clear the feedback field after saving
                // Optionally, show a confirmation to the user (e.g., a popup or label)
                System.out.println("Feedback saved successfully.");
            }
        });

        sendFeedbackButton.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        feedbackSection.getChildren().addAll(feedbackField, sendFeedbackButton);
        feedbackSection.setId("content-section");

       contentPane.getChildren().addAll(feedbackSection);

        bottomBox.getChildren().addAll(contactSupport);
        this.setTop(title);
        this.setCenter(contentPane);
        this.setBottom(bottomBox);

        // Add some padding to the content section
        this.setStyle("-fx-padding: 20px;");
    }


    private void saveFeedbackToFile(String feedback) {
        // Define the file path (you can change the path or filename as needed)
        File file = new File("feedback.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            // Write the feedback to the file
            writer.write("Feedback received: \n");
            writer.write(feedback + "\n");
            writer.write("====================================\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving feedback to file.");
        }
    }
}
