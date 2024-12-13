package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ActiveActor extends ImageView {

	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

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

	// Abstract method for position updates
	public abstract void updatePosition();

	// Helper methods for movement
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}

	// Private helper method to load images safely
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
