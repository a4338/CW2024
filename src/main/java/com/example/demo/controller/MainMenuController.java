package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The {@code MainMenuController} class manages the behavior of the main menu
 * in the application. It handles user interactions with the Play, Exit, and Help
 * buttons, allowing navigation to the game, closing the application, or opening
 * the help popup.
 */
public class MainMenuController {

    /** The image view for the Play button. */
    @FXML
    private ImageView playImage;

    /** The image view for the Exit button. */
    @FXML
    private ImageView exitImage;

    /** The image view for the Help button. */
    @FXML
    private ImageView helpImage;

    /** Reference to the main application instance. */
    private Main mainApp;

    /**
     * Sets the reference to the main application instance.
     *
     * @param mainApp the main application instance
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Initializes the main menu controller by setting up event listeners for the
     * Play, Exit, and Help buttons.
     *
     * <p>
     * - Clicking the Play button starts Level One of the game.
     * - Clicking the Exit button closes the application.
     * - Clicking the Help button opens a popup with help information.
     * </p>
     */
    @FXML
    private void initialize() {
        // Add event listener for playImage
        playImage.setOnMouseClicked(event -> {
            if (mainApp != null) {
                mainApp.startLevelOne(); // Start Level One when Play is clicked
            }
        });

        // Add event listener for exitImage
        exitImage.setOnMouseClicked(event -> {
            // Close the application
            Stage stage = (Stage) exitImage.getScene().getWindow();
            stage.close();
        });

        // Add event listener for helpImage
        helpImage.setOnMouseClicked(event -> {
            try {
                // Load the helpPopup.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/view/helpPopup.fxml"));
                Scene popupScene = new Scene(loader.load());

                // Create a new stage for the popup
                Stage popupStage = new Stage();
                popupStage.setTitle("How To Play?");
                popupStage.initModality(Modality.APPLICATION_MODAL); // Block interaction with the main menu
                popupStage.setScene(popupScene);
                popupStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
