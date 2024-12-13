package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    private ImageView playImage; // Corresponds to the Play button ImageView

    @FXML
    private ImageView exitImage; // Corresponds to the Exit button ImageView

    @FXML
    private ImageView helpImage; // Corresponds to the Help button ImageView

    private Main mainApp; // Reference to the Main application

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

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
                popupStage.setTitle("Help");
                popupStage.initModality(Modality.APPLICATION_MODAL); // Block interaction with the main menu
                popupStage.setScene(popupScene);
                popupStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
