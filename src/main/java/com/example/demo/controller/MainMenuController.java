package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    private ImageView playImage; // Corresponds to fx:id="playImage"

    @FXML
    private ImageView exitImage; // Corresponds to the quit button ImageView

    private Controller gameController; // Reference to your main Controller class

    public MainMenuController() {
        // Default constructor
    }

    @FXML
    private void initialize() {
        // Add event listener for playImage
        playImage.setOnMouseClicked(event -> {
            try {
                // Create a new Controller instance and launch the game
                Stage stage = (Stage) playImage.getScene().getWindow();
                gameController = new Controller(stage);
                gameController.launchGame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Add event listener for exitImage
        exitImage.setOnMouseClicked(event -> {
            // Close the application
            Stage stage = (Stage) exitImage.getScene().getWindow();
            stage.close();
        });
    }
}
