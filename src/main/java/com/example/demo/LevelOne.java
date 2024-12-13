package com.example.demo;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;

/**
 * The {@code LevelOne} class represents the first level in the game.
 * It extends {@code LevelParent} and defines the behavior, mechanics, and
 * progression criteria for Level One.
 */
public class LevelOne extends LevelParent {

	/** The file path for the background image of Level One. */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";

	/** The fully qualified class name of the next level. */
	private static final String NEXT_LEVEL = "com.example.demo.LevelTwo";

	/** The total number of enemy units allowed at a time. */
	private static final int TOTAL_ENEMIES = 5;

	/** The number of kills required to advance to the next level. */
	private static final int KILLS_TO_ADVANCE = 10;

	/** The probability of spawning an enemy on each spawn attempt. */
	private static final double ENEMY_SPAWN_PROBABILITY = 0.20;

	/** The initial health of the player in Level One. */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/** A flag to ensure the level transition is executed only once. */
	private boolean levelTransitionInProgress = false;

	/**
	 * Constructs a {@code LevelOne} instance with the specified screen dimensions.
	 *
	 * @param screenHeight the height of the screen
	 * @param screenWidth the width of the screen
	 */
	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the game is over or if the player has met the criteria to advance to the next level.
	 *
	 * <p>
	 * If the player is destroyed, the game ends. If the kill target is reached and the level transition
	 * has not already started, the game progresses to the next level.
	 * </p>
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (userHasReachedKillTarget() && !levelTransitionInProgress) {
			levelTransitionInProgress = true; // Set the flag to true to prevent reentry
			goToNextLevel(NEXT_LEVEL);
		}
	}

	/**
	 * Initializes the friendly units (e.g., the player's plane) and adds them to the level's root node.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Spawns enemy units in the level based on the current number of enemies
	 * and the enemy spawn probability.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

	/**
	 * Creates and returns the {@code LevelView} instance for Level One.
	 *
	 * @return a {@code LevelView} object specific to Level One
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the player has reached the required number of kills to advance to the next level.
	 *
	 * @return {@code true} if the player has met the kill target, {@code false} otherwise
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}
}

