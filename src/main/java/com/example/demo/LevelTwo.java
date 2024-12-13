package com.example.demo;
import com.example.demo.controller.Controller;
import com.example.demo.Boss;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.scene.Group;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.lang.reflect.Constructor;

import com.example.demo.controller.Controller;


/**
 * The {@code LevelTwo} class represents the second level in the game.
 * It extends {@code LevelParent} and introduces a boss enemy as the primary challenge.
 *
 * <p>
 * In this level, the player must survive and defeat the boss enemy to progress to the next level.
 * The level transitions to the next stage upon defeating the boss and ends in failure if the player's
 * plane is destroyed.
 * </p>
 */
public class LevelTwo extends LevelParent {

	/** The file path for the background image of Level Two. */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";

	/** The fully qualified class name of the next level. */
	private static final String NEXT_LEVEL = "com.example.demo.LevelThree";

	/** The initial health of the player's plane in Level Two. */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/** The boss enemy for this level. */
	private final Boss boss;

	/** A flag to ensure the level transition occurs only once. */
	private boolean levelTransitionInProgress = false;

	/** The level view, which manages UI elements specific to Level Two. */
	private LevelViewLevelTwo levelView;

	/** A flag indicating whether the boss has been spawned. */
	private boolean isBossSpawned = false;

	/** The controller for managing level transitions. */
	private Controller controller;

	/** The stage in which the level is displayed. */
	private Stage stage;

	/**
	 * Constructs a {@code LevelTwo} instance with the specified screen dimensions.
	 *
	 * @param screenHeight the height of the game screen
	 * @param screenWidth the width of the game screen
	 */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss(levelView);
	}

	/**
	 * Initializes the friendly units for this level by adding the player's plane
	 * to the scene's root node.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Checks whether the game is over by determining if the player's plane has been destroyed
	 * or if the boss has been defeated.
	 *
	 * <p>
	 * If the boss is defeated, the level transitions to the next stage.
	 * </p>
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (bossIsDefeated() && !levelTransitionInProgress) {
			levelTransitionInProgress = true; // Set the flag to true to prevent reentry
			goToNextLevel(NEXT_LEVEL);
		}
	}

	/**
	 * Checks if the boss has been defeated.
	 *
	 * @return {@code true} if the boss is destroyed, {@code false} otherwise
	 */
	private boolean bossIsDefeated() {
		return boss.isDestroyed();
	}

	/**
	 * Spawns enemy units for this level. If no enemies are active, the boss is spawned.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0 && !isBossSpawned) {
			System.out.println("Spawning boss enemy");
			addEnemyUnit(boss);
			isBossSpawned = true;
		}
	}

	/**
	 * Instantiates and returns the level view specific to Level Two.
	 *
	 * @return the {@code LevelViewLevelTwo} instance for this level
	 */
	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}
}
