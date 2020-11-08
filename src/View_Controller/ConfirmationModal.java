package View_Controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This Confirmation Modal class creates a new modal that displays a question and asks for confirmation on the question.
 * It returns the answer in the form of a bool
 */
public class ConfirmationModal {
    static boolean answer;

    /**
     *
     * @param title Displayed as the title of a new modal window
     * @param message Displayed as the message in a new modal window
     * @return The confirmation of question asked through the message.
     */
    public static boolean display(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(300);

        Label label = new Label(message);
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(yesButton, noButton);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, hBox);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
