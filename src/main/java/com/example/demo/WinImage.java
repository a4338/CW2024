package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The {@code WinImage} class represents a visual element that displays a "You Win" message
 * in the game. It extends {@code ImageView} to provide positioning and visibility controls.
 */
public class WinImage extends ImageView {

	/** The file path to the "You Win" image resource. */
	private static final String IMAGE_NAME = "/com/example/demo/images/youWin.png";

	/**
	 * Constructs a {@code WinImage} object with the specified position.
	 *
	 * @param xPosition the X coordinate where the "You Win" image should be positioned
	 * @param yPosition the Y coordinate where the "You Win" image should be positioned
	 */
	public WinImage(double xPosition, double yPosition) {
		setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		setLayoutX(xPosition);
		setLayoutY(yPosition);
	}

	/**
	 * Makes the "You Win" image visible on the screen.
	 */
	public void showWinImage() {
		this.setVisible(true);
	}
}
