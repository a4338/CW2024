package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The {@code HelpPopupController} class manages the behavior of the help popup window
 * in the application. This controller allows users to close the help popup using
 * a "Close" button.
 */
public class HelpPopupController {

    /** The image view representing the "Close" button for the help popup. */
    @FXML
    private ImageView closeImage;

    /**
     * Initializes the help popup controller by setting up the event handler
     * for the "Close" button.
     *
     *
     * Clicking the "Close" button closes the popup window.
     *
     */
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
