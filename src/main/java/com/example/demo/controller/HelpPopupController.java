package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HelpPopupController {

    @FXML
    private ImageView closeImage;

    @FXML
    private void initialize() {
        // Event handler for the close button
        closeImage.setOnMouseClicked(event -> {
            // Close the popup window
            Stage stage = (Stage) closeImage.getScene().getWindow();
            stage.close();
        });
    }
}
