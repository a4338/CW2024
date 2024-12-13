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

public class LevelViewLevelTwo extends LevelView {

	private final ShieldImage shieldImage;

	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.shieldImage = new ShieldImage(0, 0); // Initial position of the shield
		root.getChildren().add(shieldImage); // Ensure the shield image is added to the scene graph
	}

	public void showShield() {
		shieldImage.setVisible(true); // Make the shield visible
		root.getChildren().add(shieldImage); // Ensure it's added to the scene graph
		System.out.println("Shield is now visible."); // Debugging statement
	}

	public void hideShield() {
		shieldImage.setVisible(false); // Hide the shield
		System.out.println("Shield is now hidden."); // Debugging statement
	}

	public void updateShieldPosition(double x, double y) {
		shieldImage.setLayoutX(x);
		shieldImage.setLayoutY(y);
		System.out.println("Shield position updated to: (" + x + ", " + y + ")"); // Debugging statement
	}
}