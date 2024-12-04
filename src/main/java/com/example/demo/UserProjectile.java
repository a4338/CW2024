package com.example.demo;

public class UserProjectile extends Projectile {

	private static final String IMAGE_NAME = "userfire.png";
	private static final int IMAGE_HEIGHT = 250;
	private static final int HORIZONTAL_VELOCITY = 15;
	//private static final int VERTICAL_VELOCITY = 10; // if i wanna make the projectile drop from below the plane


	// private UserPlane userPlane; // referencing the user plan
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
		// this.userPlane = userPlane; //link to the user plane
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
		// moveVertically(VERTICAL_VELOCITY);
	}
	
	@Override
	public void updateActor() {
		updatePosition();
	}


}
