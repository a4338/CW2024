package com.example.demo;

/**
 * The {@code BossProjectile} class represents a projectile fired by the boss enemy.
 * It extends the {@code Projectile} class and defines the behavior of the boss's fireball,
 * including its movement and initial setup.
 */
public class BossProjectile extends Projectile {

	/** The file name of the projectile's image. */
	private static final String IMAGE_NAME = "fireball.png";

	/** The height of the projectile's image, with the width adjusted to preserve the aspect ratio. */
	private static final int IMAGE_HEIGHT = 75;

	/** The horizontal velocity of the projectile, moving leftwards. */
	private static final int HORIZONTAL_VELOCITY = -15;

	/** The initial X position of the projectile. */
	private static final int INITIAL_X_POSITION = 950;

	/**
	 * Constructs a {@code BossProjectile} at the specified initial Y position.
	 *
	 * @param initialYPos the initial Y position of the projectile
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
	}

	/**
	 * Updates the position of the projectile by moving it horizontally
	 * at the predefined velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
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
