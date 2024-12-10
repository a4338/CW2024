package com.example.demo.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300; // Window width
	private static final int SCREEN_HEIGHT = 750; // Window height
	private static final String TITLE = "Mario's Sky Battle";

	@Override
	public void start(Stage stage) {
		try {
			// Load the MainMenu.fxml file created in Scene Builder
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/view/MainMenu.fxml"));
			Pane root = loader.load();

			// Create and set the scene
			Scene mainMenuScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
			stage.setTitle(TITLE);
			stage.setResizable(false);
			stage.setScene(mainMenuScene);

			// Show the stage
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch();
	}
}
