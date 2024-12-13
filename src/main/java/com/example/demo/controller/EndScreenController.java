package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The {@code EndScreenController} class manages the behavior of the end screen
 * in the application. This screen provides options to replay the game or exit
 * the application.
 */
public class EndScreenController {

    /** The image view representing the "Play Again" button. */
    @FXML
    private ImageView playAgainImage;

    /** The image view representing the "Exit" button. */
    @FXML
    private ImageView exitImage;

    /**
     * Initializes the end screen controller by setting up event handlers for
     * the "Play Again" and "Exit" buttons.
     *
     * <p>
     * The "Play Again" button restarts the game, while the "Exit" button
     * closes the application.
     * </p>
     */
    @FXML
    private void initialize() {
        // Event handler for "Play Again" button
        playAgainImage.setOnMouseClicked(event -> {
            try {
                Stage stage = (Stage) playAgainImage.getScene().getWindow();
                Controller gameController = new Controller(stage);
                gameController.launchGame(); // Restart the game
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Event handler for "Exit" button
        exitImage.setOnMouseClicked(event -> {
            Stage stage = (Stage) exitImage.getScene().getWindow();
            stage.close(); // Exit the application
        });
    }
}
