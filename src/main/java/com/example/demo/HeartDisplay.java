package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The {@code HeartDisplay} class represents a UI component that visually displays
 * a set of hearts to indicate a player's remaining lives or health.
 *
 * <p>
 * This class manages a container of heart images displayed horizontally and
 * provides functionality to remove a heart when a life is lost.
 * </p>
 */
public class HeartDisplay {

	/** The file path to the heart image resource. */
	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";

	/** The height of each heart image, with the width adjusted to preserve the aspect ratio. */
	private static final int HEART_HEIGHT = 50;

	/** The index of the first item in the container, used when removing hearts. */
	private static final int INDEX_OF_FIRST_ITEM = 0;

	/** The container (an {@code HBox}) holding the heart images. */
	private HBox container;

	/** The X coordinate of the heart display container. */
	private double containerXPosition;

	/** The Y coordinate of the heart display container. */
	private double containerYPosition;

	/** The number of hearts to display initially. */
	private int numberOfHeartsToDisplay;

	/**
	 * Constructs a {@code HeartDisplay} with the specified position and number of hearts.
	 *
	 * @param xPosition the X coordinate of the heart display
	 * @param yPosition the Y coordinate of the heart display
	 * @param heartsToDisplay the initial number of hearts to display
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}

	/**
	 * Initializes the {@code HBox} container for the heart display and sets its position.
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);
	}

	/**
	 * Initializes the heart images and adds them to the container.
	 *
	 * <p>
	 * Each heart image is created using the predefined heart image resource
	 * and is scaled to the specified height while maintaining the aspect ratio.
	 * </p>
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));
			heart.setFitHeight(HEART_HEIGHT);
			heart.setPreserveRatio(true);
			container.getChildren().add(heart);
		}
	}

	/**
	 * Removes one heart from the display, starting with the first heart in the container.
	 *
	 * <p>
	 * If the container is empty, this method does nothing.
	 * </p>
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty()) {
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
		}
	}

	/**
	 * Returns the {@code HBox} container holding the heart images.
	 *
	 * @return the container holding the heart images
	 */
	public HBox getContainer() {
		return container;
	}
}
