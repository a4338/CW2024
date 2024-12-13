package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The {@code ActiveActor} class represents a dynamic, interactive actor in the game.
 * It extends {@code ImageView} to include additional functionality for movement
 * and behavior customization. This class is abstract, requiring subclasses to
 * define how the actor's position updates.
 */
public abstract class ActiveActor extends ImageView {

	/** The base location for image resources used by actors. */
	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
	 * Constructs an {@code ActiveActor} with the specified image, size, and initial position.
	 *
	 * @param imageName the name of the image file (located in the {@code IMAGE_LOCATION} directory)
	 * @param imageHeight the height of the image, with the width adjusted to preserve the aspect ratio
	 * @param initialXPos the initial X position of the actor
	 * @param initialYPos the initial Y position of the actor
	 * @throws IllegalArgumentException if the specified image resource cannot be found
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		// Validate and load the image
		String imagePath = IMAGE_LOCATION + imageName;
		Image image = loadImage(imagePath);

		this.setImage(image);
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Updates the position of the actor. Subclasses must provide an implementation
	 * for how the position changes over time.
	 */
	public abstract void updatePosition();

	/**
	 * Moves the actor horizontally by a specified amount.
	 *
	 * @param horizontalMove the amount to move horizontally (positive for right, negative for left)
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Moves the actor vertically by a specified amount.
	 *
	 * @param verticalMove the amount to move vertically (positive for down, negative for up)
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}

	/**
	 * Loads an image from the specified path. If the image cannot be loaded,
	 * a fallback image is used.
	 *
	 * @param path the path to the image resource
	 * @return the loaded {@code Image} object
	 * @throws IllegalArgumentException if the image resource cannot be found
	 */
	private Image loadImage(String path) {
		try {
			if (getClass().getResource(path) == null) {
				throw new IllegalArgumentException("Resource not found: " + path);
			}
			return new Image(getClass().getResource(path).toExternalForm());
		} catch (Exception e) {
			System.err.println("Error loading image: " + path);
			e.printStackTrace();
			// Provide a fallback image or handle the error
			return new Image(getClass().getResource("/com/example/demo/images/fallback.png").toExternalForm());
		}
	}
}
