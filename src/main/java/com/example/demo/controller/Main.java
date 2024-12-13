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

/**
 * The {@code Main} class serves as the entry point for the application and manages
 * the transitions between the main menu and different game levels. It extends
 * {@code javafx.application.Application} to support JavaFX functionality.
 */
public class Main extends Application {

	/** The width of the application window. */
	private static final int SCREEN_WIDTH = 1300;

	/** The height of the application window. */
	private static final int SCREEN_HEIGHT = 750;

	/** The title of the application window. */
	private static final String TITLE = "Mario's Sky Battle";

	/** The file path for the background music. */
	private static final String BACKGROUND_MUSIC_PATH = "/com/example/demo/audio/background.wav";

	/** The primary stage of the application. */
	private Stage primaryStage;

	/**
	 * The main entry point for the JavaFX application.
	 *
	 * @param stage the primary stage for this application
	 */
	@Override
	public void start(Stage stage) {
		this.primaryStage = stage;
		showMainMenu();
	}

	/**
	 * Loads and displays the main menu of the application.
	 *
	 * <p>
	 * This method loads the {@code MainMenu.fxml} file, initializes the
	 * {@code MainMenuController}, and sets up the main menu scene. It also
	 * starts the background music.
	 * </p>
	 */
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

	/**
	 * Starts Level One of the game.
	 *
	 * <p>
	 * This method initializes {@code LevelOne}, sets up its scene, and transitions
	 * to Level Two upon completion.
	 * </p>
	 */
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

	/**
	 * Starts Level Two of the game.
	 *
	 * <p>
	 * This method initializes {@code LevelTwo}, sets up its scene, and transitions
	 * to Level Three upon completion.
	 * </p>
	 */
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

	/**
	 * Starts Level Three of the game.
	 *
	 * <p>
	 * This method initializes {@code LevelThree}, sets up its scene, and
	 * transitions back to the main menu upon completion.
	 * </p>
	 */
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

	/**
	 * Cleans up resources when the application stops.
	 *
	 * <p>
	 * This method stops the background music when the application is closed.
	 * </p>
	 */
	@Override
	public void stop() {
		AudioManager.stopBackgroundMusic();
	}

	/**
	 * The main method, which serves as the application's entry point.
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		launch();
	}
}
