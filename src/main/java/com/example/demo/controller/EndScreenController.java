package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EndScreenController {

    @FXML
    private ImageView playAgainImage;

    @FXML
    private ImageView exitImage;

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
