package com.example.demo.controller;

import com.example.demo.AudioManager;
import com.example.demo.LevelOne;
import com.example.demo.LevelTwo;
import com.example.demo.LevelThree;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300; // Window width
	private static final int SCREEN_HEIGHT = 750; // Window height
	private static final String TITLE = "Mario's Sky Battle";
	private static final String BACKGROUND_MUSIC_PATH = "/com/example/demo/audio/background.wav"; // Path to background music

	private Stage primaryStage;

	@Override
	public void start(Stage stage) {
		this.primaryStage = stage;
		showMainMenu(); // Load MainMenu.fxml and show the main menu
	}

	private void showMainMenu() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/view/MainMenu.fxml"));
			Pane root = loader.load();

			// Link the Main class to MainMenuController
			MainMenuController controller = loader.getController();
			controller.setMainApp(this);

			// Set up the main menu scene
			Scene mainMenuScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
			primaryStage.setTitle(TITLE);
			primaryStage.setResizable(false);
			primaryStage.setScene(mainMenuScene);

			// Start playing background music
			AudioManager.startBackgroundMusic(BACKGROUND_MUSIC_PATH);

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startLevelOne() {
		try {
			LevelOne levelOne = new LevelOne(SCREEN_HEIGHT, SCREEN_WIDTH);
			Scene levelOneScene = levelOne.initializeScene();

			// Add observer for transition to LevelTwo
			levelOne.addObserver((observable, arg) -> {
				if (arg.equals("com.example.demo.LevelTwo")) {
					startLevelTwo();
				}
			});

			primaryStage.setScene(levelOneScene);
			levelOne.startGame();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startLevelTwo() {
		try {
			LevelTwo levelTwo = new LevelTwo(SCREEN_HEIGHT, SCREEN_WIDTH);
			Scene levelTwoScene = levelTwo.initializeScene();

			// Add observer for transition to LevelThree
			levelTwo.addObserver((observable, arg) -> {
				if (arg.equals("com.example.demo.LevelThree")) {
					startLevelThree();
				}
			});

			primaryStage.setScene(levelTwoScene);
			levelTwo.startGame();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startLevelThree() {
		try {
			LevelThree levelThree = new LevelThree(SCREEN_HEIGHT, SCREEN_WIDTH);
			Scene levelThreeScene = levelThree.initializeScene();

			// Add observer for end screen or additional logic
			levelThree.addObserver((observable, arg) -> {
				System.out.println("Game completed!");
				showMainMenu(); // Return to the main menu after completing the game
			});

			primaryStage.setScene(levelThreeScene);
			levelThree.startGame();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		// Stop the background music when the application closes
		AudioManager.stopBackgroundMusic();
	}

	public static void main(String[] args) {
		launch();
	}
}
