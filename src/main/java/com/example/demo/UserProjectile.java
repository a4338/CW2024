/*package com.example.demo;

public class UserProjectile extends Projectile {

	private static final String IMAGE_NAME = "userfire.png";
	private static final int IMAGE_HEIGHT = 250;
	private static final int HORIZONTAL_VELOCITY = 15;
	private static final int DAMAGE = 1; // Default damage for regular projectiles

	private UserPlane userPlane; // referencing the user plan

	/* public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
		// this.userPlane = userPlane; //link to the user plane
	} */


	/* public UserProjectile(UserPlane userPlane) {
		super(IMAGE_NAME, IMAGE_HEIGHT, userPlane.getLayoutX() + userPlane.getTranslateX(),
				userPlane.getLayoutY() + userPlane.getTranslateY());
		this.userPlane = userPlane;
	}

	/* @Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
		// moveVertically(VERTICAL_VELOCITY);
	} */ /*
@Override
public void updatePosition() {
	// Synchronize X and Y position with the UserPlane
	setLayoutX(userPlane.getLayoutX() + userPlane.getTranslateX());
	setLayoutY(userPlane.getLayoutY() + userPlane.getTranslateY());
	moveHorizontally(HORIZONTAL_VELOCITY);
}

	public int getDamage() {
		return DAMAGE;
	}

	@Override
	public void updateActor() {
		updatePosition();
	}
}

*/
package com.example.demo;

/**
 * The {@code UserProjectile} class represents a projectile fired by the user plane.
 * It extends the {@code Projectile} class and defines the behavior for the user's projectiles,
 * including movement and initial setup.
 */
public class UserProjectile extends Projectile {

	/** The file name of the projectile's image. */
	private static final String IMAGE_NAME = "userfire.png";

	/** The height of the projectile's image, with the width adjusted to preserve the aspect ratio. */
	private static final int IMAGE_HEIGHT = 250;

	/** The horizontal velocity of the projectile, moving rightwards. */
	private static final int HORIZONTAL_VELOCITY = 15;

	/**
	 * Constructs a {@code UserProjectile} at the specified initial position.
	 *
	 * @param initialXPos the initial X position of the projectile
	 * @param initialYPos the initial Y position of the projectile
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the projectile by moving it horizontally
	 * at the predefined velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
		// Uncomment the following line if vertical movement is desired:
		// moveVertically(VERTICAL_VELOCITY);
	}

	/**
	 * Updates the state of the projectile. In this implementation, it only
	 * updates its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
