/* package com.example.demo;

import javafx.scene.Group;

public class LevelViewLevelTwo extends LevelView {

	//shield needs to dynamically follow boss. thus  it should not have a fixed position
	// private static final int SHIELD_X_POSITION = 1150;
	//private static final int SHIELD_Y_POSITION = 500;

	private final Group root;
	private final ShieldImage shieldImage;
	
	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(0,0); // new position to initialise the shield
		addImagesToRoot();
	}
	
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
	}
	
	public void showShield() {
		shieldImage.showShield();
	}

	public void hideShield() {
		shieldImage.hideShield();
	}
	public void updateShieldPosition(double x, double y) { //boss will update shield position here
		shieldImage.setLayoutX(x);
		shieldImage.setLayoutY(y);
	}
}

 */
package com.example.demo;

import javafx.scene.Group;

/**
 * The {@code LevelViewLevelTwo} class extends {@code LevelView} to include
 * additional functionality specific to Level Two, such as managing the shield
 * visual element for the boss.
 *
 * <p>
 * This class introduces methods for showing, hiding, and updating the position
 * of the shield image, which is a unique feature of Level Two.
 * </p>
 */
public class LevelViewLevelTwo extends LevelView {

	/** The shield image used to represent the boss's shield. */
	private final ShieldImage shieldImage;

	/**
	 * Constructs a {@code LevelViewLevelTwo} instance with the specified root node
	 * and initial number of hearts to display.
	 *
	 * @param root the root node of the level's scene graph
	 * @param heartsToDisplay the initial number of hearts to display
	 */
	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.shieldImage = new ShieldImage(0, 0); // Initial position of the shield
		root.getChildren().add(shieldImage); // Ensure the shield image is added to the scene graph
	}

	/**
	 * Makes the shield image visible on the screen and ensures it is added to the scene graph.
	 */
	public void showShield() {
		shieldImage.setVisible(true); // Make the shield visible
		root.getChildren().add(shieldImage); // Ensure it's added to the scene graph
		System.out.println("Shield is now visible."); // Debugging statement
	}

	/**
	 * Hides the shield image from the screen.
	 */
	public void hideShield() {
		shieldImage.setVisible(false); // Hide the shield
		System.out.println("Shield is now hidden."); // Debugging statement
	}

	/**
	 * Updates the position of the shield image to match the specified coordinates.
	 *
	 * @param x the new X position of the shield
	 * @param y the new Y position of the shield
	 */
	public void updateShieldPosition(double x, double y) {
		shieldImage.setLayoutX(x);
		shieldImage.setLayoutY(y);
		System.out.println("Shield position updated to: (" + x + ", " + y + ")"); // Debugging statement
	}
}
