package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The {@code ShieldImage} class represents a visual element for displaying a shield in the game.
 * It extends {@code ImageView} and provides functionality to control the visibility
 * and positioning of the shield image.
 */
public class ShieldImage extends ImageView {

	/** The file name of the shield image. */
	private static final String IMAGE_NAME = "shield.png";

	/** The size (height and width) of the shield image. */
	private static final int SHIELD_SIZE = 10;

	/**
	 * Constructs a {@code ShieldImage} object at the specified position.
	 *
	 * @param xPosition the X coordinate of the shield's position
	 * @param yPosition the Y coordinate of the shield's position
	 */
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.setImage(new Image(getClass().getResource("/com/example/demo/images/" + IMAGE_NAME).toExternalForm()));
		this.setVisible(true);
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
		System.out.println("Shield initialized with size: " + SHIELD_SIZE);
	}

	/**
	 * Makes the shield image visible on the screen.
	 */
	public void showShield() {
		this.setVisible(true);
		System.out.println("Shield is now visible");
	}

	/**
	 * Hides the shield image from the screen.
	 */
	public void hideShield() {
		this.setVisible(false);
		System.out.println("Shield is now hidden");
	}
}
