package com.example.demo;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;

/**
 * The {@code LevelThree} class represents the third level in the game.
 * It extends {@code LevelParent} and defines the behavior, mechanics, and progression
 * criteria for Level Three.
 *
 * <p>
 * In this level, the player must survive and defeat enemies to progress. The level ends
 * when the player either achieves the required number of kills or loses all health.
 * </p>
 */
public class LevelThree extends LevelParent {

    /** The file path for the background image of Level Three. */
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background3.jpg";

    /** The initial health of the player in Level Three. */
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /** The total number of enemy units allowed at a time. */
    private static final int TOTAL_ENEMIES = 10;

    /** The number of kills required to complete the level. */
    private static final int KILLS_TO_ADVANCE = 10;

    /** The probability of spawning an enemy on each spawn attempt. */
    private static final double ENEMY_SPAWN_PROBABILITY = 0.20;

    /** A flag to ensure the level transition occurs only once. */
    private boolean levelTransitionInProgress = false;

    /**
     * Constructs a {@code LevelThree} instance with the specified screen dimensions.
     *
     * @param screenHeight the height of the screen
     * @param screenWidth the width of the screen
     */
    public LevelThree(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
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
     * Creates and returns the {@code LevelView} instance for Level Three.
     *
     * @return a {@code LevelView} object specific to Level Three
     */
    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Checks if the player has reached the required number of kills to win the level.
     *
     * @return {@code true} if the player has met the kill target, {@code false} otherwise
     */
    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }

    /**
     * Checks if the game is over or if the player has met the criteria to win the level.
     *
     * <p>
     * If the player is destroyed, the game ends. If the kill target is reached and the level transition
     * has not already started, the game progresses to the win state.
     * </p>
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (userHasReachedKillTarget() && !levelTransitionInProgress) {
            levelTransitionInProgress = true; // Set the flag to true to prevent reentry
            winGame();
        }
    }
}
