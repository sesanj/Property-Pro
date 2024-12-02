package Settings;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Help_Support extends BorderPane {

    public Help_Support() {

        Text title = new Text("Help and Support");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        title.setTextAlignment(TextAlignment.CENTER);

        HBox bottomBox = new HBox(20);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setStyle("-fx-padding: 10px 0;");

        StackPane contentPane = new StackPane();
        contentPane.getStylesheets().add(getClass().getResource("/content.css").toExternalForm());
        contentPane.setId("content-pane");

        VBox contactSupport = new VBox(15);
        contactSupport.setAlignment(Pos.CENTER);

        TextFlow contactTextFlow = new TextFlow();
        Text contactTitle = new Text("Contact Support: ");
        contactTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Hyperlink emailText = new Hyperlink("\nEmail: aabrahamofficial@gmail.com\n");
        emailText.setOnAction(event -> {
            try {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://mail.google.com/mail/u/0/#inbox?compose=DmwnWstsBnPKZpZFjczxtLVDCsqWSBbnxqJflfVCJPTqDjQpQTJhPkcknqjnfBVVntFTtzHHwvQB"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        emailText.setStyle("-fx-font-size: 16px;");
        emailText.setTextAlignment(TextAlignment.CENTER);

        Text phoneText = new Text("Phone: 226-611-6917\n");
        phoneText.setStyle("-fx-font-size: 16px;");
        phoneText.setTextAlignment(TextAlignment.CENTER);

        Text urgentText = new Text("For urgent issues, please email us directly.\n");
        urgentText.setStyle("-fx-text-fill: #f0f0f0-fx-font-size: 16px; -fx-font-style: italic;");
        urgentText.setTextAlignment(TextAlignment.CENTER);

        Text versionText = new Text("\nVersion: 1.0.0");
        versionText.setStyle("-fx-text-fill: #f0f0f0 -fx-font-size: 16px;");
        versionText.setTextAlignment(TextAlignment.CENTER);


        contactSupport.getChildren().addAll(contactTitle, emailText, phoneText, urgentText, versionText);

        VBox feedbackSection = new VBox(15);
        feedbackSection.setAlignment(Pos.CENTER);
        feedbackSection.setMaxWidth(500);
        feedbackSection.setPrefWidth(500);

        TextFlow feedbackTextFlow = new TextFlow();
        Text feedbackTitle = new Text("Send Feedback: ");
        feedbackTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        feedbackTitle.setTextAlignment(TextAlignment.CENTER);

        Text feedbackInfo = new Text("\nWe appreciate your feedback! Please let us know how we can improve.\n");
        feedbackInfo.setStyle("-fx-font-size: 16px;");
        feedbackInfo.setTextAlignment(TextAlignment.CENTER);

        feedbackTextFlow.getChildren().addAll(feedbackTitle, feedbackInfo);
        feedbackSection.getChildren().add(feedbackTextFlow);

        TextField feedbackField = new TextField();
        feedbackField.setPromptText("Enter your feedback here...");

        Button sendFeedbackButton = new Button("Send Feedback");

        sendFeedbackButton.setOnAction(e -> {
            String feedback = feedbackField.getText();

            if (!feedback.trim().isEmpty()) {
                saveFeedbackToFile(feedback);
                feedbackField.clear();
                System.out.println("Feedback saved successfully.");
            }
        });

        sendFeedbackButton.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        feedbackSection.getChildren().addAll(feedbackField, sendFeedbackButton);

        contentPane.getChildren().add(feedbackSection);

        bottomBox.getChildren().addAll(contactSupport);

        this.setTop(title);
        this.setCenter(contentPane);
        this.setBottom(bottomBox);

        this.setStyle("-fx-padding: 20px;");
    }

    private void saveFeedbackToFile(String feedback) {

        File file = new File("feedback.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write("Feedback received: \n");
            writer.write(feedback + "\n");
            writer.write("====================================\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving feedback to file.");
        }
    }
}
