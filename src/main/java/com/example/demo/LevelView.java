package com.example.demo;

import javafx.scene.Group;

/**
 * The {@code LevelView} class manages the visual elements of a game level,
 * including the display of the player's health, win/lose images, and other
 * UI components.
 *
 * <p>
 * This class provides methods to update and control the visual state of the level,
 * such as adding or removing hearts from the health display, and showing win or game-over images.
 * </p>
 */
public class LevelView {

	/** The X position for the heart display. */
	private static final double HEART_DISPLAY_X_POSITION = 5;

	/** The Y position for the heart display. */
	private static final double HEART_DISPLAY_Y_POSITION = 25;

	/** The X position for the win image. */
	private static final int WIN_IMAGE_X_POSITION = 355;

	/** The Y position for the win image. */
	private static final int WIN_IMAGE_Y_POSITION = 175;

	/** The X position for the game-over image. */
	private static final int LOSS_SCREEN_X_POSITION = 300;

	/** The Y position for the game-over image. */
	private static final int LOSS_SCREEN_Y_POSITION = 300;

	/** The root node of the level's scene graph. */
	public final Group root;

	/** The image displayed when the player wins the level. */
	private final WinImage winImage;

	/** The image displayed when the player loses the level. */
	private final GameOverImage gameOverImage;

	/** The heart display that shows the player's remaining health. */
	private final HeartDisplay heartDisplay;

	/**
	 * Constructs a {@code LevelView} instance with the specified root node and
	 * initial number of hearts to display.
	 *
	 * @param root the root node of the level's scene graph
	 * @param heartsToDisplay the initial number of hearts to display
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION);
	}

	/**
	 * Adds the heart display to the root node, making it visible in the level.
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Displays the win image on the screen by adding it to the root node and making it visible.
	 */
	public void showWinImage() {
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}

	/**
	 * Displays the game-over image on the screen by adding it to the root node.
	 */
	public void showGameOverImage() {
		root.getChildren().add(gameOverImage);
	}

	/**
	 * Updates the heart display to reflect the player's remaining health.
	 *
	 * <p>
	 * This method removes hearts from the display until the number of visible
	 * hearts matches the player's remaining health.
	 * </p>
	 *
	 * @param heartsRemaining the number of hearts that should remain visible
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}
}
