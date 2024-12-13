package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The {@code GameOverImage} class represents a visual element displaying a "Game Over" message.
 * It extends {@code ImageView} and is used to position and display a predefined "Game Over" image
 * in the game.
 */
public class GameOverImage extends ImageView {

	/** The file path to the "Game Over" image resource. */
	private static final String IMAGE_NAME = "/com/example/demo/images/gameOver.png";

	/**
	 * Constructs a {@code GameOverImage} object with the specified position.
	 *
	 * @param xPosition the X coordinate where the image should be positioned
	 * @param yPosition the Y coordinate where the image should be positioned
	 */
	public GameOverImage(double xPosition, double yPosition) {
		// Load and set the "Game Over" image
		setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		setLayoutX(xPosition);
		setLayoutY(yPosition);
	}
}
